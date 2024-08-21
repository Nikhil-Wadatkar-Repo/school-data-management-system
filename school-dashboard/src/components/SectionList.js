import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { callAllClasses, callSectionAPI, getStudentByIdAPI } from '../ApiCalls';
import { useParams } from 'react-router-dom';

function SectionList() {
    const [classList, setClassList] = useState([]);
    const [userTypeList, setUserTypeList] = useState([
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    ]);
    const [yearList, setYearList] = useState([
        2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
    ]);
    const [teacherList, setTeacherList] = useState([
        "Teacher 1", "Teacher 2", "Teacher 3", "Teacher 4", "Teacher 5", "Teacher 6"
    ]);
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
        <><h1>Section Wise Students   section:{section}, year:{year}, std: {std}</h1>
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
                        </thead>
                        <tbody>
                            {
                                classList.map((item, index) => (
                                    <tr>
                                        <td>{item.studunid}</td>
                                        <td>{item.name}</td>
                                        <td>{item.status}</td>
                                        <td>{item.year}</td>
                                        <td>{item.email}</td>
                                        <td>{item.contact}</td>
                                        <td>{item.city}</td>
                                        <td>{item.status}</td>
                                        <td><button>View Details</button></td>
                                        <td><a href=''>Edit</a></td>
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
