import React from 'react'

function Dashboard() {
    return (
        <div className='container'>
            <h1>Dashboard</h1>
            {/* <div>
                <nav className="nav nav-pills flex-column flex-sm-row">
                    <a className="flex-sm-fill text-sm-center nav-link active" aria-current="page" href="#">Active</a>
                    <a className="flex-sm-fill text-sm-center nav-link" href="#">Longer nav link</a>
                    <a className="flex-sm-fill text-sm-center nav-link" href="#">Link</a>
                    <a className="flex-sm-fill text-sm-center nav-link" href="#">Link</a>
                    <a className="flex-sm-fill text-sm-center nav-link" href="#">Link</a>
                    <a className="flex-sm-fill text-sm-center nav-link" href="#">Link</a>
                    <a className="flex-sm-fill text-sm-center nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </nav>
            </div> */}
<div className='container'>
            <div className='row'>
                <div className='col-4' style={{ backgroundColor: "gray" }}><p>Menus</p></div>
                <div className='col-8' style={{ backgroundColor: "green" }}><p>main section</p></div>
            </div>
            </div>
        </div>
    )
}

export default Dashboard
