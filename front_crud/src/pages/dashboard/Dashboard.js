import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
    
    const [animes, setAnimes] = useState([]);
    const navigate = useNavigate();

    useEffect( () => {
        const fetchAnimes = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/MostrarAnimes");
                const data = await response.json();

                setAnimes(data);
            } catch(error) {
                console.error("Error fetching animes:", error);
            }   
        }
        fetchAnimes();
    }, []);

    const handleDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/animes/${id}`,
                {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
            });

            if(response.ok) {
                setAnimes((previousAnimes) => 
                    previousAnimes.filter((anime)=> anime.id !== id)
            );
            }
 
            console.log("Anime Eliminado:", response);
        } catch(error) {
            console.error("Error al borrar el anime:", error);
        }
    }

    const handleUpdate = (id) => {
        navigate(`/animes/${id}`);
    }

    return (
        <>
           <Container className="mt-5">
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
                                <Button variant="outline-danger" onClick={() => handleDelete()}>Eliminar</Button>
                            </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
            </Container>
        </>
    );
}

export default Dashboard;