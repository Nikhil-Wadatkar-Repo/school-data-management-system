import React, { useState } from 'react'

function Dashboard() {
    const [itemList,setItems]=useState(["Class","Students","Subject","Class Routine","Parent","Student Section"]);
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
                                    <li className="list-group-item">{item}</li>
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
                    <div className='col-10' style={{ backgroundColor: "green" }}><p>main section</p></div>
                </div>
            </div>
            <div className="footer fixed-bottom">
    2 days ago
  </div>
        </div>
    )
}

export default Dashboard
