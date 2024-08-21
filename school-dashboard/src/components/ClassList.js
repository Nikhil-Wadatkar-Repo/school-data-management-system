import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { callAllClasses, callAllSections, getDistinctStandardAPI, getDistinctYearsAPI } from '../ApiCalls';
import { useParams } from 'react-router-dom';

function ClassList() {
    const [classList, setClassList] = useState([]);
    const [sectionList, setSectionList] = useState([]);
    const [stdList, setStdList] = useState([
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    ]);
    const [yearList, setYearList] = useState([
        2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
    ]);

    let initialValue = {
        std: 0,
        section: 0,
        year: 0
    }
    const [req, setReq] = useState(initialValue);
    const { params } = useParams();

    const getAllSections = () => {
        callAllSections().then(resp => {
            setSectionList(resp.data);
        })
    }
    const getAllYears = () => {
        getDistinctYearsAPI().then(resp => {
            setYearList(resp.data)
        })
    }
    const getAllStandards = () => {
        getDistinctStandardAPI().then(resp => {
            setStdList(resp.data)
        })
    }

    const getClassDetails = () => {
        callAllClasses().then(
            resp => {
                setClassList(resp.data)
            }
        );
    }
    const handleChange = (key, val) => {
        setReq({ ...req, [key]: val });
    }

    useEffect(() => {
        // getClassDetails();
        getAllSections();
        getAllYears();
        getAllStandards();
    }, []);
    return (
        <><h1>Class List</h1>


            <div className='row'>
                <div className='col'>
                    <label>Class</label>
                    <select
                        id="inputState"
                        className="form-select"
                        onChange={(e) => handleChange("std", e.target.value)}
                        value={req.std}
                    >
                        <option selected>Choose...</option>
                        {stdList.map((item, index) => (
                            <option key={index} value={item}>
                                {item}
                            </option>
                        ))}
                    </select>
                </div>
                <div className='col'>
                    <label>Section</label>
                    <select
                        id="inputState"
                        className="form-select"
                        onChange={(e) => handleChange("section", e.target.value)}
                        value={req.section}
                    >
                        <option selected>Choose...</option>
                        {sectionList.map((item, index) => (
                            <option key={index} value={item}>
                                {item}
                            </option>
                        ))}
                    </select>
                </div>
                <div className='col'>
                    <label>Year</label>
                    <select
                        id="inputState"
                        className="form-select"
                        onChange={(e) => handleChange("year", e.target.value)}
                        value={req.year}
                    >
                        <option selected>Choose...</option>
                        {yearList.map((item, index) => (
                            <option key={index} value={item}>
                                {item}
                            </option>
                        ))}
                    </select>
                </div>
                <div className='col'>
                    <button className='btn btn-primary' style={{ "margin-top": "25px" }}
                    onClick={e=>{
                        console.log(req);
                        
                    }}
                    >Click</button>
                </div>
            </div>

            <br></br>


            {
                classList.length > 0 ? <>
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
                </> : ""
            }

        </>

    )
}

export default ClassList
