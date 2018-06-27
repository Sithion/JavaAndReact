import React from 'react';
import { Route } from 'react-router-dom';
import ProductCatalog from './product-catalog';
import ShopCart from './shop-cart';

export default (
  <div className="routes">
    <Route path="/" exact component={ProductCatalog} />
    <Route path="/shop-cart" exact component={ShopCart} />
  </div>
);
