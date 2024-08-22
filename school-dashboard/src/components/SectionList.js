import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { callAllClasses, callSectionAPI, getStudentByIdAPI } from '../ApiCalls';
import { useNavigate, useParams } from 'react-router-dom';

function SectionList() {
    const nav = useNavigate();
    const [classList, setClassList] = useState([]);
    const { section,year,std } = useParams();
    const getStduentDetails = () => {
        callSectionAPI({
            "std":std,
            "year":year,
            "section":section
        }).then(
            resp => {
                setClassList(resp.data)
            }
        );
    }

    useEffect(() => {
        getStduentDetails();
    }, []);
    return (
        <><h1>Section Wise Students</h1>     section:{section}, year:{year}, std: {std}
            <div className='row'>
                <div id="table">
                    <table className='table'>
                    
                        <thead>
                            <th>studunid</th>
                            <th>name</th>
                            <th>status</th>
                            <th>year</th>
                            <th>email</th>
                            <th>contact</th>
                            <th>city</th>
                            <th>status</th>
                            <th>Action</th>
                            <th>View</th>
                        </thead>
                        <tbody>
                            {
                                classList.map((item, index) => (
                                    <tr>
                                        <td>{item.studUNID}</td>
                                        <td>{item.name}</td>
                                        <td>{item.status}</td>
                                        <td>{item.year}</td>
                                        <td>{item.email}</td>
                                        <td>{item.contact}</td>
                                        <td>{item.city}</td>
                                        <td>{item.status}</td>
                                        <td><button onClick={e=> nav("/updateStudent/"+item.studId)}>View Details</button></td>
                                        <td><button  onClick={e=> nav("/subjectByStdUNID/"+item.studId)}>Edit button</button></td>
                                    </tr>
                                ))

                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </>

    )
}

export default SectionList
