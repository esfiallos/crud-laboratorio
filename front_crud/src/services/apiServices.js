// src/services/apiService.js
import { API_BASE_URL } from "../constantes/Constantes";

const handleResponse = async (response) => {
    if (!response.ok) {
        const error = await response.text(); 
        throw new Error(error || `HTTP error! status: ${response.status}`);
    }
    
    if (response.status !== 204 && response.headers.get('Content-Type')?.includes('application/json')) {
        return await response.json();
    }
    return null; 
};

const get = async (endpoint) => {
    const response = await fetch(`${API_BASE_URL}/${endpoint}`);
    return handleResponse(response);
};

const post = async (endpoint, data) => {
    const response = await fetch(`${API_BASE_URL}/${endpoint}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    return handleResponse(response);
};

const patch = async (endpoint, data) => {
    const response = await fetch(`${API_BASE_URL}/${endpoint}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    return handleResponse(response);
};

const del = async (endpoint) => {
    const response = await fetch(`${API_BASE_URL}/${endpoint}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return handleResponse(response);
};

const apiService = {
    get,
    post,
    patch,
    delete: del,
};

export default apiService;