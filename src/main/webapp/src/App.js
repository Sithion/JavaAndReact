import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import logo from './logo.svg';
import routes from './routes';
import './App.css';

export default () => (
  <div className="App">
    <header className="App-header">
      <img src={logo} className="App-logo" alt="logo" />
      <h1 className="App-title">
        SHOP TEST
      </h1>
    </header>
    <Router>
      {routes}
    </Router>
  </div>
);
