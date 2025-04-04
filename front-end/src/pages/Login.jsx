import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login(){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try{
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                username,
                password
            });

            localStorage.setItem("token", response.data.token);
            alert("Login Successful!");
            navigate("/todos")
        } catch(error){
            // optional chaining(?.) 
            console.error("Login error:", error);
            console.error("Error response:", error.response);
            alert("Login Failed: " + (error.response?.data?.message || "Could not connect to server"));
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} required/>
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;