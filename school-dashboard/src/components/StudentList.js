import React, { useState } from 'react'

function StudentList() {
    const [studentList, setStudentList] = useState([
        {
            firstName:"aa",
            lastName:"aa",
            age:1
        },
        {
            firstName:"bb",
            lastName:"bb",
            age:2
        },
        {
            firstName:"cc",
            lastName:"cc",
            age:3
        }
    ]);
    
    return (
        <div>

            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Student List</h2></div>
                <div className='col'></div>
            </div>
            <br></br>
            <div className='row'>


                <div className='col'>
                    <label for="inputAddress" className="form-label">
                        First Name
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="inputAddress"
                        placeholder="Name"
                    // onChange={(e) => handleChange("name", e.target.value)}
                    // value={userDetails.name}
                    />
                </div>
                <div className='col'>
                    <label for="inputAddress" className="form-label">
                        Last Name
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="inputAddress"
                        placeholder="Name"
                    // onChange={(e) => handleChange("name", e.target.value)}
                    // value={userDetails.name}
                    />
                </div>
                <div className='col'>
                    <label for="inputAddress" className="form-label">
                        Handle
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="inputAddress"
                        placeholder="Name"
                    // onChange={(e) => handleChange("name", e.target.value)}
                    // value={userDetails.name}
                    />
                </div>
            </div>

            <br></br>
            <div className='row'>
                <div className='col'>
                    <button className='btn btn-primary'>Click Here</button> &nbsp;
                    <button className='btn btn-danger'>Reset</button>&nbsp;
                    <button className='btn btn-dark '>Cancel</button>
                </div>
                <div className='col'></div>
                <div className='col'></div>
            </div>

            <br></br>
            <div className='container'>
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            studentList.map((stu,index)=>(
<tr>
                            <th scope="row">{index}</th>
                            <td>{stu.firstName}</td>
                            <td>{stu.lastName}</td>
                            <td>{stu.age}</td>
                            <td><button>Edit</button></td>
                        </tr>
                            ))
                        }
                        
                    </tbody>
                </table>
            </div>
        </div>

    )
}

export default StudentList
