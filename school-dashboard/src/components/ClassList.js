import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import { callAllClasses, callAllSections, getDistinctStandardAPI, getDistinctYearsAPI, getSectionYearByStandardAPI } from '../ApiCalls';
import { useNavigate, useParams } from 'react-router-dom';
import { MyContext } from './MyContext';
import AlertMessage from './AlertMessage';

function ClassList() {
    const nav = useNavigate();
    const {
        text,
        setText,
        alert,
        setAlert,
        alertMessage,
        setAlertMessage,
        messageType,
        setMessageType,
        alertTitle,
        setAlertTitle,
    } = useContext(MyContext);
    const [showAlert, setShowAlert] = useState(false);
    const [classList, setClassList] = useState([]);
    const [sectionList, setSectionList] = useState([]);
    const [stdList, setStdList] = useState([]);
    const [yearList, setYearList] = useState([]);

    let initialValue = {
        std: 0,
        section: 0,
        year: 0
    }
    const [req, setReq] = useState(initialValue);
    const [paramsDetails, setParamsDetails] = useState(initialValue);

    const getAllStandards = () => {
        getDistinctStandardAPI().then(resp => {
            setStdList(resp.data)
        })
    }

    const getSectionYearByStandard = (standard) => {
        getSectionYearByStandardAPI(standard).then(
            resp => {
                console.log("resp.data::", resp.data);
                setYearList(resp.data.years);
                setSectionList(resp.data.sectionDetails);


            }
        );
    }
    const handleChange = (key, val) => {
        setReq({ ...req, [key]: val });
    }
    const getFilteredClassDetailsList = () => {
        setParamsDetails(req);
        callAllClasses(req).then(
            resp => {
                if (resp.data.length > 0) {

                    setReq(initialValue);
                    setClassList(resp.data);
                    setShowAlert(false);
                } else {
                    setAlert(true);
                    setAlertTitle("Sorry!!!!!");
                    setAlertMessage("Sorry We dont't have any record regarding selected fields");
                    setMessageType("alert-danger");
                    setShowAlert(true);
                }


            }
        )
    }
    const navigateToPage =(section,year,std)=>{
        console.log("navigateToPage: ",section,year,std);
        
        nav("/studentList/" + paramsDetails.section + "/" + paramsDetails.year + "/" + paramsDetails.std)
    }

    useEffect(() => {
        // getClassDetails();
        // getAllSections();
        // getAllYears();
        getAllStandards();
    }, []);
    return (
        <><h1>Class List</h1>
            {showAlert ? (
                <>
                    <AlertMessage></AlertMessage>
                </>
            ) : (
                ""
            )}

            <div className='row'>
                <div className='col'>
                    <label>Class</label>
                    <select
                        id="inputState"
                        className="form-select"
                        onChange={(e) => {
                            handleChange("std", e.target.value);
                            getSectionYearByStandard(e.target.value)
                        }}
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
                        {
                            sectionList.map((item, index) => (
                                <option key={index} value={item.id}>
                                    {item.name}
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
                        onClick={e => {
                            getFilteredClassDetailsList(req);

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

                                </thead>
                                <tbody>
                                    {
                                        classList.map((item, index) => (
                                            <tr key={index}>
                                                <td>{item.section}</td>
                                                <td>{item.noOfStudents}</td>
                                                <td>{item.presentStudents}</td>
                                                <td>{item.year}</td>
                                                <td>{item.classTeacherName}</td>
                                                <td>{item.standards}</td>
                                                <td><button onClick={e => 
                                                    navigateToPage(item.section,item.year,item.standards)}>View Details</button></td>

                                            </tr>
                                        ))

                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </> : ""
            }

            <button onClick={e => nav("/studentList/" + paramsDetails.section + "/" + paramsDetails.year + "/" + paramsDetails.std)}>Redirect</button>

        </>

    )
}

export default ClassList
