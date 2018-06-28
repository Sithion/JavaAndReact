import React, { Component } from 'react';
import * as _ from 'lodash';

class ProductCatalog extends Component {
  constructor(...args) {
    super(...args);
    this.state = {};
  }

  async componentDidMount() {
    const url = 'http://localhost:8080/api/products';
    // eslint-disable-next-line no-undef
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    if (response.status === 200) {
      const products = await response.json();
      this.setState({
        products,
      });
    }
  }

  renderProductItem(product) {
    const { name, description, price } = product;
    console.log('product');
    return (
      <div className="product">
        <div className="product-header">
          <div className="product-name">
            {name}
          </div>
          <div className="product-price">
            {price}
          </div>
        </div>
        <div className="product-description">
          {description}
        </div>
      </div>
    );
  }

  render() {
    const { products } = this.state;
    return (
      <div className="products">
        {_.map(products, this.renderProductItem)}
      </div>
    );
  }
}


export default ProductCatalog;
