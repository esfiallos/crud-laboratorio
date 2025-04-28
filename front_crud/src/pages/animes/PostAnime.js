import { useState } from "react";
import "./PostAnime.css";
import { Button, Form, ListGroup } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { categorias } from "../../constantes/Constantes";
import { estados } from "../../constantes/Constantes";

const DROPDOWN_CLOSE_DELAY = 200;

const PostAnime = () => {


    const [formData, setFormData] = useState({
        "nombre": "",    
        "descripcion": "", 
        "categoria": "", 
        "capitulos": "",
        "estado": "",    
        "valoracion": ""
    });

    const [filteredCategories, setFilteredCategories] = useState([]);
    const [filteredStates, setFilteredStates] = useState([]);
    const [isCategoryDropdownOpen, setIsCategoryDropdownOpen] = useState(false);
    const [isStateDropdownOpen, setIsStateDropdownOpen] = useState(false);


    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
 if (name === 'categoria') {
            const isCode = !isNaN(value);
            const filtered = isCode 
                ? categorias.filter(cat => cat.code.includes(value)) 
                : categorias.filter(cat => cat.name.toLowerCase().includes(value.toLowerCase()) || cat.code.includes(value));
            setFilteredCategories(filtered);
            setIsCategoryDropdownOpen(filtered.length > 0);
        }

        if (name === 'estado') {
            const isCode = !isNaN(value);
            const filtered = isCode 
                ? estados.filter(est => est.code.includes(value)) 
                : estados.filter(est => est.name.toLowerCase().includes(value.toLowerCase()) || est.code.includes(value));
            setFilteredStates(filtered);
            setIsStateDropdownOpen(filtered.length > 0);
        }
    };

    const handleCategorySelect = (category) => {
        setFormData({ ...formData, categoria: category.name });
        setIsCategoryDropdownOpen(false);
    };

    const handleStateSelect = (state) => {
        setFormData({ ...formData, estado: state.name });
        setIsStateDropdownOpen(false);
    };

    const handleCategoryBlur = () => {
        setTimeout(() => {
            setIsCategoryDropdownOpen(false);
        }, DROPDOWN_CLOSE_DELAY);
    };

    const handleStateBlur = () => {
        setTimeout(() => {
            setIsStateDropdownOpen(false);
        }, DROPDOWN_CLOSE_DELAY);
    };

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(formData);

        try {
            const response = await fetch("http://localhost:8080/api/animes", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData)
            });

            const data = await response.json();
            console.log("Anime creado", data);
            navigate("/");
        } catch (error) {
            console.error("Error al enviar los datos:", error);
        }
    };

    return (
        <div className="center-form">
            <h1>Formulario de Anime</h1>
            <Form onSubmit={handleSubmit}>  

                <Form.Group controlId="formBasicName">
                    <Form.Control
                        type="text"
                        name="nombre"
                        placeholder="Ingrese nombre"
                        value={formData.nombre}
                        onChange={handleInputChange}
                    />
                </Form.Group>

                <Form.Group controlId="formBasicDescripcion">
                    <Form.Control
                        type="text"
                        name="descripcion"
                        placeholder="Ingrese descripcion"
                        value={formData.descripcion}
                        onChange={handleInputChange}
                    />
                </Form.Group>

                <Form.Group controlId="formCategoria" className="position-relative">
                    <Form.Control
                        type="text"
                        name="categoria"
                        placeholder="Escriba una categoria"
                        value={formData.categoria}
                        onChange={handleInputChange}
                        onBlur={handleCategoryBlur}
                    />
                    {isCategoryDropdownOpen && filteredCategories.length > 0 && (
                        <ListGroup className="dropdown-menu fade-in">
                            {filteredCategories.map((category) => (
                                <ListGroup.Item
                                    key={category.code}
                                    onMouseDown={() => handleCategorySelect(category)}
                                    action
                                >
                                    {category.code} - {category.name}
                                </ListGroup.Item>
                            ))}
                        </ListGroup>
                    )}
                </Form.Group>

                <Form.Group controlId="formCapitulos">
                    <Form.Control
                        type="number"
                        name="capitulos"
                        placeholder="Ingrese capitulos"
                        value={formData.capitulos}
                        onChange={handleInputChange}
                    />
                </Form.Group>

                <Form.Group controlId="formEstado" className="position-relative">
                    <Form.Control
                        type="text"
                        name="estado"
                        placeholder="Escriba el estado"
                        value={formData.estado}
                        onChange={handleInputChange}
                        onBlur={handleStateBlur}
                    />
                    {isStateDropdownOpen && filteredStates.length > 0 && (
                        <ListGroup className="dropdown-menu fade-in">
                            {filteredStates.map((state) => (
                                <ListGroup.Item
                                    key={state.code}
                                    onMouseDown={() => handleStateSelect(state)}
                                    action
                                >
                                    {state.code} - {state.name}
                                </ListGroup.Item>
                            ))}
                        </ListGroup>
                    )}
                </Form.Group>

                <Form.Group controlId="formValoracion">
                    <Form.Control
                        type="number"
                        name="valoracion"
                        placeholder="Ingrese valoracion"
                        value={formData.valoracion}
                        onChange={handleInputChange}
                    />
                </Form.Group>

                <Button variant="primary" type="submit" className="w-100">
                    Guardar Anime
                </Button>

            </Form>
        </div>
    );
};

export default PostAnime;