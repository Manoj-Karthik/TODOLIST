import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './DeleteTask.css'; // Importing the CSS file

const DeleteTask = () => {
    const [userId, setUserId] = useState('');
    const [taskId, setTaskId] = useState('');
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

    const handleDeleteTask = async () => {
        if (!userId || !taskId) {
            setError('Please provide both User ID and Task ID.');
            return;
        }

        try {
            // Make DELETE request using axios
            const response = await axios.delete(`http://localhost:9000/api/${userId}/deleteTask/${taskId}`);
            setSuccessMessage('Task deleted successfully!');
            setUserId('');
            setTaskId('');
        } catch (err) {
            setError('Error deleting task. Please try again.');
        }
    };

    return (
        <div className="container">
            <div className="container__items">
                {error && <p className="error">{error}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                
                <label htmlFor="userId">USER ID </label>
                <input 
                    type="text" 
                    name="userId" 
                    id="userId" 
                    placeholder="ENTER USER ID"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                /><br /><br />
                
                <label htmlFor="taskId">TASK ID </label>
                <input 
                    type="text" 
                    name="taskId" 
                    id="taskId" 
                    placeholder="ENTER TASK ID"
                    value={taskId}
                    onChange={(e) => setTaskId(e.target.value)}
                /><br /><br />
                
                <button type="button" onClick={handleDeleteTask}>DELETE TASK</button>
            </div>
        </div>
    );
};

export default DeleteTask;
