import { useState } from 'react'
import { useNavigate } from "react-router-dom"
import './App.css'

function App() {
  const navigate = useNavigate()

  return (
    <>
      <div>
        <h1>Let's add your todos!</h1>
        <p onClick={() => navigate("/Login")} style={{color: 'blue', textDecoration: 'underline', cursor: 'pointer'}}>Log in</p>
        <p>
          If you haven't registered yet, let's <span onClick={() => navigate("/Register")} style={{color: 'blue', textDecoration: 'underline', cursor: 'pointer'}}>sign up</span>
        </p>
      </div>
    </>
  )
}

export default App
