import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../styles/Todos.css";

function Todos() {
    const [todos, setTodos] = useState([]);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try{
            const token = localStorage.getItem("token");
            const response = await axios.get("http://localhost:8080/api/todos", {
                headers: {Authorization: `Bearer ${token}`}
            })
            setTodos(response.data)
        } catch (error){
            alert("Unauthorized. Please login.");
            navigate("/login");
        }
    };

    const addTodo = async (e) => {
        e.preventDefault();
        try{
            const token = localStorage.getItem("token");
            await axios.post("http://localhost:8080/api/todos", 
                {
                    title: title,
                    description: description,
                    completed: false
                },
                {
                    headers: {Authorization: `Bearer ${token}`}
                }
            ); 
            setTitle("");
            setDescription("");
            fetchTodos();
        } catch (error){
            console.error("Error happened while adding a task: " + error);
            alert("Failed to add task.");
        }
    };

    const updateTodoStatus = async (id, completed) => {
        try{
            const token = localStorage.getItem("token");
            const todoToUpdate = todos.find(todo => todo.id === id);

            await axios.put(`http://localhost:8080/api/todos/${id}`,
                {
                    title: todoToUpdate.title,
                    description: todoToUpdate.description,
                    completed: completed
                },
                {
                    headers: {Authorization: `Bearer ${token}`}
                }
            );
            fetchTodos();
        } catch (error) {
            alert("Failed to update the task status.");
        }
    };

    const deleteTodo = async (id) => {
        try{
            const token = localStorage.getItem("token");
            await axios.delete(`http://localhost:8080/api/todos/${id}`, {
                headers: {Authorization: `Bearer ${token}`}
            });
            fetchTodos(); 
        } catch (error) {
            alert("Failed to delete task.");
        }
    };

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    }

    return(
        <div className="todos-container">
            <div className="todos-header">
                <h2>To-Do List</h2>
                <button className="logout-btn" onClick={handleLogout}>Logout</button>
            </div>
            <form onSubmit={addTodo}>
                <input type="text" placeholder="Enter Task" value={title} onChange={e => setTitle(e.target.value)} required/>
                <textarea placeholder="Enter Description (optional)" value={description} onChange={e => setDescription(e.target.value)} />
                <button type="submit">Add</button>
            </form>
            <ul>
                {todos.map(todo => (
                    <li key={todo.id}>
                        <input type="checkbox" checked={todo.completed} onChange={(e) => updateTodoStatus(todo.id, e.target.checked)}/>
                        <h3>{todo.title}</h3>
                        {todo.description && <p>{todo.description}</p>}
                        <button onClick={() => deleteTodo(todo.id)}>Delete</button>
                    </li>        
                ))}
            </ul>
        </div>
    )
};

export default Todos;
