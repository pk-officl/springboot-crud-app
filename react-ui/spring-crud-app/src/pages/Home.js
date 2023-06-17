import React, { useEffect, useState } from 'react'
import axios from 'axios';
import './styles/Home.css'
import SignUpContoller from '../components/controllers/SignUpContoller';
import LogInContoller from '../components/controllers/LogInContoller';
import Products from './Products';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

const Home = () => {
    const [isSignUp, setIsSignUp] = useState(false);
    const [isLogIn, setIsLogIn] = useState(false);
    const [inputValue, setInputValue] = useState('');
    const [products, setProducts] = useState([]);
    const [suggestions, setSuggestions] = useState([]);
    const [isTrigger, setIsTrigger] = useState(true);
    const getProductionBySuggestion = (event) => {
        setSuggestions([]);
        setInputValue(event.target.value);
        setIsTrigger(false);
        try {
            const API = axios.create({ baseURL: "http://localhost:9001" });
            API.get("/product/all/get?searchTerm=" + event.target.value)
                .then(response => {
                    if (response?.data?.products !== undefined) {
                        setProducts(response.data.products);
                    }
                    console.log(response);
                })
                .catch(error => {
                    console.log(error);
                })
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        if(!isTrigger){
            return;
        }
        if (inputValue === ("" || undefined)) {
            setSuggestions([]);
        } else {
            try {
                const API = axios.create({ baseURL: "http://localhost:9001" });
                API.get("/product/suggestions?searchTerm=" + inputValue)
                    .then(response => {
                        if (response?.data?.suggestions !== undefined) {
                            setSuggestions(response.data.suggestions);
                        } else {
                            setSuggestions([]);
                        }
                    })
                    .catch(error => {
                        console.log(error);
                    })
            } catch (error) {
                console.log(error);
            }
        }
    }, [inputValue]);

    const handleInputChange = (event)=>{
        setInputValue(event.target.value);
        setIsTrigger(true);
    }
    return (
        <div>
            <div className='navbar-cont'>
                <div className='page-section'>
                    <button>Home</button>
                    <button>Electronics</button>
                    <button>Accessories</button>
                    <button>Books</button>
                    <button>Dresses</button>
                </div>
                <div className='search-bar'>
                    <div className='search-bar-cont'>
                        <input className='search-input' type='text' value={inputValue} onChange={handleInputChange} ></input>
                        <div className='search-icon'>
                            <FontAwesomeIcon icon={faSearch} />
                        </div>
                    </div>
                    <div className='suggestion-cont' >
                        {suggestions.map((suggestion, index) => (
                            <button onClick={getProductionBySuggestion} key={index} value={suggestion}>{suggestion}</button>
                        ))}
                    </div>
                </div>
                <div className='user-section'>
                    <button onClick={() => setIsSignUp(prev => !prev)}>SignUp</button>
                    <button onClick={() => setIsLogIn(prev => !prev)}>Login</button>
                    <button>MyCart</button>
                </div>
            </div>
            {isSignUp && <SignUpContoller isSignUp={isSignUp} setIsSignUp={setIsSignUp} />}
            {isLogIn && <LogInContoller isLogIn={isLogIn} setIsLogIn={setIsLogIn} />}
            <Products products={products} setProducts={setProducts} />
        </div>
    )
}

export default Home