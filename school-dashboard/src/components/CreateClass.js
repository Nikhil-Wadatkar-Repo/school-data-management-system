import React, { useContext, useEffect, useState } from 'react'
import { callAllSections, callAllTeachers, saveClassAPI } from '../ApiCalls';
import AlertMessage from './AlertMessage';
import { MyContext } from './MyContext';
import { useNavigate } from 'react-router-dom';

function CreateClass() {
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
    let initialValue = {
        section: "",
        year: "",
        classTeacherName: "",
        noOfStudents: 0,
        standards: ""
    }
    const [reqDetails, setReqDetails] = useState(initialValue);
    const handleChange = (key, val) => {
        setReqDetails({ ...reqDetails, [key]: val });
    }
    const resetData = () => {
        setReqDetails(initialValue);
    }

    const [userTypeList, setUserTypeList] = useState([]);
    const [standard, setStandard] = useState([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
    const [yearList, setYearList] = useState([
        2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
    ]);
    const [teacherList, setTeacherList] = useState([]);
   
   
    const [showAlert, setShowAlert] = useState(false);
    

    const getAllTeachers = () => {
        callAllTeachers().then(
            resp => {
                setTeacherList(resp.data)
            }
        );
    }
    const getAllSections = () => {
        callAllSections().then(
            resp => {
                setUserTypeList(resp.data)
            }
        );
    }
   
    const saveDetails = () => {
        saveClassAPI(reqDetails).then(resp => {
            setAlert(true);
            setAlertTitle("Details are saved successfuly");
            setAlertMessage("");
            setMessageType("alert-success");
            setShowAlert(true);
            resetData();
            nav("/classList", { id: 120 });
        }).catch(resp => {
            setAlert(true);
            setAlertTitle("Sorry!! Please peovide valide inputs");
            setAlertMessage("");
            setMessageType("alert-danger");
            setShowAlert(true);
        })
    }
    useEffect(() => {
        getAllSections();
        getAllTeachers();
    }, []);
    return (
        <>
            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Create Class</h2>
                    {showAlert ? (
                        <>
                            <AlertMessage></AlertMessage>
                        </>
                    ) : (
                        ""
                    )}
                </div>
                <div className='col'></div>
            </div>
            <br></br>
            <br></br>
            <div className='row'>
                <div className='col-sm-3' ></div>
                <div className='col-sm-6' >
                    <table className='table'>
                        <thead>
                            {/* <tr><th>Class Name : </th><td><input type='text' className="form-control" ></input></td></tr> */}
                            <tr><th>No. of Students :</th>
                                <td><input type='text' className="form-control"
                                    value={reqDetails.noOfStudents}
                                    onChange={e => handleChange("noOfStudents", e.target.value)}
                                ></input></td></tr>
                            <tr><th>Standard :</th>
                                <td>
                                    <select
                                        id="inputState"
                                        className="form-select"
                                        onChange={(e) => handleChange("standards", e.target.value)}
                                        value={reqDetails.standards}>
                                        <option selected>Choose...</option>
                                        {standard.map((item, index) => (
                                            <option key={index} value={item}>
                                                {item}
                                            </option>
                                        ))}
                                    </select>

                                </td></tr>
                            <tr><th>Section : </th><td> <select
                                id="inputState"
                                className="form-select"
                                onChange={(e) => handleChange("section", e.target.value)}
                                value={reqDetails.section}>
                                <option selected>Choose...</option>
                                {userTypeList.map((item, index) => (
                                    <option key={index} value={item.sectionID}>
                                        {item.sectionName}
                                    </option>
                                ))}
                            </select></td></tr>
                            <tr><th>Year : </th><td>
                                <select
                                    id="inputState"
                                    className="form-select"
                                    onChange={e => handleChange("year", e.target.value)}
                                    value={reqDetails.year}
                                >
                                    <option selected>Choose...</option>
                                    {yearList.map((item, index) => (
                                        <option key={index} value={item}>
                                            {item}
                                        </option>
                                    ))}
                                </select>
                            </td>
                            </tr>
                            <tr><th>Class Teacher Name : </th><td><select
                                id="inputState"
                                className="form-select"
                                onChange={e => handleChange("classTeacherName", e.target.value)}
                                value={reqDetails.classTeacherId}
                            >
                                <option selected>Choose...</option>
                                {teacherList.map((item, index) => (
                                    <option key={index} value={item.teacherId}>
                                        {item.name}
                                    </option>
                                ))}
                            </select></td></tr>
                            <tr><th>Submit </th><td>
                                <button className='btn btn-primary'
                                    onClick={e => saveDetails()
                                    }
                                >Click Here</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default CreateClass