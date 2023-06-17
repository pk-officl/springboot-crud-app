import React, { useState } from 'react';

const SignUpContoller = ({isSignUp, setIsSignUp }) => {
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');
    return (
        <div className="signUp-cont">
            <div className="signUp-popup">
                <h2>Signup</h2>
                <form className='signUp-form'>
                    <div>
                    <input type="text" value={firstname} onChange={(e) => setFirstname(e.target.value)} placeholder='First Name'/>
                    <input type="text" value={lastname} onChange={(e) => setLastname(e.target.value)} placeholder='Last Name'/>
                    </div>
                    <div>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder='Email'/>
                    </div>
                    <div>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder='Password'/>
                    <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} placeholder='Confirm Password'/>
                    </div>
                    <div>
                    <button id='submit-btn'>Submit</button>
                    <button id= 'close-btn' onClick={() => setIsSignUp(prev => !prev)}>Close</button>
                    </div> 
                </form>
            </div>
        </div>
    )
}

export default SignUpContoller