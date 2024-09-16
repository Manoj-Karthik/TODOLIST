import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AddUser.css'; // Importing the CSS file

const AddUser = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        // Clear messages after 3 seconds
        const timer = setTimeout(() => {
            setError('');
            setSuccessMessage('');
        }, 3000);
        return () => clearTimeout(timer);
    }, [error, successMessage]);

    const handleAddUser = async () => {
        if (!name || !email) {
            setError('Please fill in both name and email fields.');
            return;
        }

        try {
            const response = await axios.post('http://localhost:9000/api/create', { name, email });
            setSuccessMessage('User added successfully!');
            setName('');
            setEmail('');
        } catch (err) {
            setError('Error adding user. Please try again.');
        }
    };

    return (
        <div className="container">
            <div className="container__items">
                {error && <p className="error">{error}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                <label htmlFor="name">NAME </label>
                <input 
                    type="text" 
                    name="name" 
                    id="name" 
                    placeholder="ENTER NAME"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                /><br /><br />
                <label htmlFor="email">EMAIL </label>
                <input 
                    type="email" 
                    name="email" 
                    id="email" 
                    placeholder="ENTER EMAIL"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                /><br /><br />
                <button type="button" onClick={handleAddUser}>ADD USER</button>
            </div>
        </div>
    );
};

export default AddUser;
