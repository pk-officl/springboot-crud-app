import React, { useEffect, useState } from 'react'
import axios from 'axios';
import './styles/Products.css'
import ProductController from '../components/controllers/ProductController';

const Products = ({products, setProducts}) => {
    let componentMounted = true;
    useEffect(() => {
        console.log('Function automatically called');
        if(componentMounted){
            try {
                const API = axios.create({ baseURL: "http://localhost:9001" });
                API.get("/product/all/get")
                    .then(response => {
                        if(response?.data?.products !==undefined){
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
            return () => {
                componentMounted = false;
            }
        }
    },[]);
    return (
        <div className='body-cont'>
            <div className='filters-cont'>
                Filters
            </div>
            <div className='products-cont'>
                <div className='products-list'>
                    <ProductController products={products}/>
                </div>
            </div>
        </div>
    )
}

export default Products