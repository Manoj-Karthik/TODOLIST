import React from 'react';
import { Link } from 'react-router-dom';
import './NavBar.css'; // Importing the CSS file

const NavBar = () => {
    return (
        <nav className="navbar">
            <ul className="navbar__list">
                <li className="navbar__item">
                    <Link to="/adduser" className="navbar__link">ADDUSER</Link>
                </li>
                <li className="navbar__item">
                    <Link to="/addtask" className="navbar__link">ADDTASK</Link>
                </li>
                <li className="navbar__item">
                    <Link to="/deletetask" className="navbar__link">DELETETASK</Link>
                </li>
            </ul>
        </nav>
    );
};

export default NavBar;
