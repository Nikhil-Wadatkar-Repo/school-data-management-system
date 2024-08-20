import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { callAllClasses } from '../ApiCalls';
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
    const { params } = useParams();
    const getClassDetails = () => {
        callAllClasses().then(
            resp => {
                setClassList(resp.data)
            }
        );
    }

    useEffect(() => {
        getClassDetails();
    }, []);
    return (
        <><h1>Section Wise Students</h1>
            <div className='row'>
                <div id="table">

                    <table className='table'>
                        <thead>
                            <th>Section</th>
                            <th>on Record</th>
                            <th>No Of Student</th>
                            <th>year</th>
                            <th>Class Teacher</th>
                            <th>Std</th>
                            <th>View Details</th>
                            <th>Action</th>
                        </thead>
                        <tbody>
                            {
                                classList.map((item, index) => (
                                    <tr>
                                        <td>{item.section}</td>
                                        <td>{item.noOfStudents}</td>
                                        <td>{item.presentStudents}</td>
                                        <td>{item.year}</td>
                                        <td>{item.classTeacherName}</td>
                                        <td>{item.standards}</td>
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
