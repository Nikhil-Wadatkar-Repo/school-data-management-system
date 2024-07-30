import React, { useState } from 'react'
import CreateUser from './CreateUser';

function Dashboard() {
    const [itemList, setItems] = useState(["Class", "Students", "Subject", "Class Routine", "Parent", "Student Section"]);
    const [createUserPage, setCreateUserPage] = useState(false);
    const showForm = (val) => {
        console.log("Val::",val.item);
        if ("Class" === val.item) {
            setCreateUserPage(true);
        }
        else
        setCreateUserPage(false);
    }
    return (
        <div className='container-fluid'>
            <h1>Dashboard</h1>
            <div className='container-fluid'>
                <div className='row'>
                    <div className='col-2' >
                        <div>
                            <ul className="list-group">
                                {
                                    itemList.map((item, index) => (
                                        <li key={index} className="list-group-item btn" onClick={e=>showForm({ item })}>{item}</li>
                                    ))
                                }
                                <li className="list-group-item">An item</li>
                                <li className="list-group-item">A second item</li>
                                <li className="list-group-item">A third item</li>
                                <li className="list-group-item">A fourth item</li>
                                <li className="list-group-item">And a fifth one</li>
                            </ul>
                        </div>

                    </div>
                    <div className='col-8' ><p>main section</p>
                        {createUserPage ? <CreateUser></CreateUser> : <></>}

                    </div>
                    <div className='col-2'>Notifications</div>
                </div>
            </div>
            <div className="footer fixed-bottom">
                2 days ago
            </div>
        </div>
    )
}

export default Dashboard
