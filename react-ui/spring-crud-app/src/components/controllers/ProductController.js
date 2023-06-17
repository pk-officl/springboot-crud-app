import React from 'react'

const ProductController = ({ products }) => {
    return (
        <div className='products-list-cont'>
            {products.map((product, index) => (
                <div className='product-cont' key={index}>
                    <div className='product' index = {index}>
                       {product.prod_name}
                    </div>
                </div>
            ))}
        </div>
    )
}

export default ProductController