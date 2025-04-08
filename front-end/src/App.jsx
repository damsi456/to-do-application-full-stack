import { useNavigate } from "react-router-dom"
import './index.css'
// import '../styles/App.css'

function App() {
  const navigate = useNavigate()

  return (
    <div className="welcome-container">
      <div className="welcome-content">
        <h1>Let's add your todos!</h1>
        <p>Organize your tasks, boost your productivity, and never miss a deadline.</p>
        <div className="welcome-links">
          <button 
            className="welcome-btn login-btn" 
            onClick={() => navigate("/login")}
          >
            Log In
          </button>
          <button 
            className="welcome-btn register-btn" 
            onClick={() => navigate("/register")}
          >
            Sign Up
          </button>
        </div>
      </div>
    </div>
  )
}

export default App
