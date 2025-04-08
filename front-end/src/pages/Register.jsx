import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import "../styles/Auth.css"

function Register(){
    const [fullName, setFullName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
        try{
            await axios.post("http://localhost:8080/api/auth/register", {
                fullName,
                username,
                email,
                password
            });
            alert("Registration successful! Please login.");
            navigate("/login");
        } catch(error){
            alert("Registration failed" + error.response?.data?.message || "Couldn't connect to server.");
        }
    };

    return(
        <div className="auth-container">
            <h2>Create Account</h2>
            <form className="auth-form" onSubmit={handleRegister}>
                <input type="text" placeholder="Full Name" value={fullName} onChange={(e) => setFullName(e.target.value)} required/>
                <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} required/>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
                <button type="submit">Register</button>
            </form>
            <div className="auth-links">
                <p>Already have an account? <Link to={"/login"}></Link></p>
            </div>
        </div>
    )
};

export default Register;