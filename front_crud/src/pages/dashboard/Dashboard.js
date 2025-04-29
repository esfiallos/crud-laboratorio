import React, { useEffect, useState } from "react";
import { Table, Button, Col, Container, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import "./Dashboard.css";
import apiService from "../../services/apiServices";

const Dashboard = () => {
    const [animes, setAnimes] = useState([]);
    const [busqueda, setBusqueda] = useState("");
    const navigate = useNavigate();

    const fetchAnimes = async () => {
        try {
            const data = await apiService.get("MostrarAnimes");
            setAnimes(data);
        } catch(error) {
            console.error("Error fetching animes:", error);
        }
    }

    useEffect( () => {
        fetchAnimes();
    }, []);

    const handleDelete = async (id) => {
        try {
            await apiService.delete(`animes/${id}`);
            setAnimes((previousAnimes) =>
                previousAnimes.filter((anime)=> anime.id !== id)
            );
            console.log("Anime Eliminado:", `animes/${id}`);
        } catch(error) {
            console.error("Error al borrar el anime:", error);
        }
    }

    const handleUpdate = (id) => {
        navigate(`/animes/${id}`);
    }

    const handleInputChange = (event) => {
        setBusqueda(event.target.value);
        if (event.target.value === "") {
            fetchAnimes();
        }
    };

    const handleSearch = async (busqueda) => {
        try {
            const data = await apiService.get(`MostrarAnimes/${busqueda}`);
            setAnimes(data);
        } catch (error) {
            console.error("Error al buscar animes:", error);
        }
    };

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            handleSearch(busqueda);
        }
    };

    return (
        <Container className="mt-5">
            <div className="containerInput d-flex justify-content-start align-items-center mb-3">
            <input
                className="Form-Control input_buscar input-button-height"
                value={busqueda}
                placeholder="Busqueda por Nombre "
                onChange={handleInputChange}
                onKeyDown={handleKeyDown}
            />
            <Button className="btn btn-success"
            onClick={() => handleSearch(busqueda)}>
            <FontAwesomeIcon icon={faSearch} />
            </Button>
            </div>
            <Row>
                <Col>
                    <h1 className="text-center">
                        Animes
                    </h1>
                    <Table striped bordered hover className="mt-4">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Categoria</th>
                                <th>Capitulos</th>
                                <th>Estado</th>
                                <th>Valoracion</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {animes.map((anime) => (
                                <tr key={anime.id}>
                                <td>{anime.nombre}</td>
                                <td>{anime.descripcion}</td>
                                <td>{anime.categoria}</td>
                                <td>{anime.capitulos}</td>
                                <td>{anime.estado}</td>
                                <td>{anime.valoracion}</td>
                                <td>
                                    <Button variant="outline-secondary" onClick={() => handleUpdate(anime.id)}>Actualizar</Button>
                                    <Button variant="outline-danger" onClick={() => handleDelete(anime.id)}>Eliminar</Button>
                                </td>
                                </tr>
                            ))}
                            </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default Dashboard;