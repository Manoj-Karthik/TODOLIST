import React, { useState, useEffect } from 'react';
import axios from 'axios';

import './AddTask.css'; // Importing the CSS file

const AddTask = () => {
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
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

    const handleAddTask = async () => {
        if (!id || !name || !description) {
            setError('Please fill in all fields: ID, name, and description.');
            return;
        }

        try {
            const response = await axios.post(`http://localhost:9000/api/${id}/task`, { id, name, description });
            setSuccessMessage('Task added successfully!');
            setId('');
            setName('');
            setDescription('');
        } catch (err) {
            setError('Error adding task. Please try again.');
        }
    };

    return (
        <div className="container">
            <div className="container__items">
                {error && <p className="error">{error}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                
                <label htmlFor="id">ID </label>
                <input 
                    type="text" 
                    name="id" 
                    id="id" 
                    placeholder="ENTER TASK ID"
                    value={id}
                    onChange={(e) => setId(e.target.value)}
                /><br /><br />
                
                <label htmlFor="name">NAME </label>
                <input 
                    type="text" 
                    name="name" 
                    id="name" 
                    placeholder="ENTER TASK NAME"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                /><br /><br />
                
                <label htmlFor="description">DESCRIPTION </label>
                <input 
                    type="text" 
                    name="description" 
                    id="description" 
                    placeholder="ENTER TASK DESCRIPTION"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                /><br /><br />
                
                <button type="button" onClick={handleAddTask}>ADD TASK</button>
            </div>
        </div>
    );
};

export default AddTask;
