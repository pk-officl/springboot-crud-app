import React, { useState } from 'react'
import axios from 'axios';
const LogInContoller = ({ isLogIn, setIsLogIn }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const LogInHandler = (event) => {
        event.preventDefault();
        try {
            const API = axios.create({ baseURL: "http://localhost:9001" });
            const data = {
                username : email,
                password : password
            }
            API.post("/auth/login",data)
                .then(response => {
                    console.log(response)
                    if(response.data!==null&&response?.data?.authToken!==(null||undefined)){
                        sessionStorage.setItem("authToken",response.data.authToken);
                    }
                })
                .catch(error => {
                    console.log(error);
                })
        } catch (error) {

        }
    }
    return (
        <div className="logIn-cont">
            <div className="logIn-popup">
                <h2>LogIn</h2>
                <form className='logIn-form'>
                    <div>
                        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder='Email' />
                    </div>
                    <div>
                        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder='Password' />
                    </div>
                    <div>
                        <button id='submit-btn' onClick={(event) => LogInHandler(event)}>Submit</button>
                        <button id='close-btn' onClick={() => setIsLogIn(prev => !prev)}>Close</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default LogInContoller