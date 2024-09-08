import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { callAllClasses, callSectionAPI, getStudentByIdAPI } from '../ApiCalls';
import { useNavigate, useParams } from 'react-router-dom';
import { getAllStudentsAPI, updateDeleteStudentByIdAPI } from './ApiCalls';

function StudentList() {
    const nav = useNavigate();
    const [classList, setClassList] = useState([]);
    let initialValue = {
        section: "",
        year: "",
        standards: ""
    }
    const [reqDetails, setReqDetails] = useState(initialValue);
    const handleChange = (key, val) => {
        setReqDetails({ ...reqDetails, [key]: val });
    }
    const getStduentDetails = () => {
        getAllStudentsAPI().then(
            resp => {
                setClassList(resp.data)
            }
        );
    }
const performAction=(url)=>{
    debugger;
    updateDeleteStudentByIdAPI(url);
    getStduentDetails();
}
    useEffect(() => {
        getStduentDetails();
    }, []);
    return (
        <><h1>Section Wise Students List</h1>
                    <div className='row'>
                <div id="table">
                    <table className='table'>
                    
                        <thead>
                            <th>Section</th>
                            <th>Student Name</th>
                            <th>Standard</th>
                            <th>Update</th>
                           <th>Delete</th>
                           <th>Disable</th>
                           <th>Enable</th>
                        </thead>
                        <tbody>
                            {
                                classList.map((item, index) => (
                                    <tr>
                                        <td>{item.sectionName}</td>
                                        <td>{item.studentName}</td>
                                        <td>{item.standard}</td>
                                        <td><button className='btn' style={{backgroundColor:"#ffc107"}} onClick={e=> nav("/updateStudent/"+item.studId)}>Update </button></td>
                                        <td><button className='btn' style={{backgroundColor:"#dc3545"}} onClick={e=> performAction("/deleteOrInActiveStudentById/"+item.studId+"/R")}>Delete </button></td>
                                        <td><button className='btn' style={{backgroundColor:"#0d6efd"}} onClick={e=> performAction("/deleteOrInActiveStudentById/"+item.studId+"/D")}>Disable</button></td>
                                        <td><button className='btn' style={{backgroundColor:" rgb(35 225 137)"}} onClick={e=> performAction("/deleteOrInActiveStudentById/"+item.studId+"/E")}>Enable</button></td>
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

export default StudentList
