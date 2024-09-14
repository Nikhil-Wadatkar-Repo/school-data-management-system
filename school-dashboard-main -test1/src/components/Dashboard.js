import React, { useContext, useState } from 'react'
import CreateUser from './CreateUser';
import { MyContext } from './MyContext';

function Dashboard() {
    const {text,setText} = useContext(MyContext);
   
    
    return (
        <div className='container-fluid'>
            <h1>Dashboard</h1>
            text: {text}

            <button onClick={e=>setText("Mi")}>Click</button>
            
        </div>
    )
}

export default Dashboard
