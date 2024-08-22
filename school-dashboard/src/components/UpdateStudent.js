import React, { useContext, useEffect, useState } from 'react'
import AlertMessage from './AlertMessage';
import { MyContext } from './MyContext';
import { useParams } from 'react-router-dom';
import { getDistinctStandardAPI, getSectionYearByStandardAPI, getStudentByIdAPI } from '../ApiCalls';

function UpdateStudent() {
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
    const { id } = useParams();
    let initialValue = {
        studId: 0,
        contact: 0,
        pincode: 0,
        name: "",
        city: "",
        email: "",
        status: "",
        studUNID: "",
        dob: "",
        std: 0
    }
    const [studentDetails, setStudentDetails] = useState(initialValue);
    const [classList, setClassList] = useState([]);
    const [sectionList, setSectionList] = useState([]);
    const [stdList, setStdList] = useState([]);
    const [yearList, setYearList] = useState([]);
   
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
    const getStduentDetails = () => {
        getStudentByIdAPI(id).then(
            resp => {
                console.log("==>", resp.data);
                setStudentDetails(resp.data)
            }
        );
    }
    const handleChange = (key, val) => {
        setStudentDetails({ ...studentDetails, [key]: val });
    }
    useEffect(() => {
        getStduentDetails();
        getAllStandards();
    }, []);
    return (
        <>
            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Update Student</h2></div>
                <div className='col'></div>
            </div>
            {showAlert ? (
                <>
                    <AlertMessage></AlertMessage>
                </>
            ) : (
                ""
            )}
            <br></br>
            <br></br>
            <div className='row'>
                <div className='col-sm-3' ></div>
                <div className='col-sm-6 input-group-sm' >
                    <table className='table table-hover'>
                        <thead>
                            <tr><th>Name : </th><td><input type='text' value={studentDetails.name} onChange={e => handleChange("name", e.target.value)} className="form-control" ></input></td></tr>
                            <tr><th>DOB :</th><td><input type='text' value={studentDetails.dob} onChange={e => handleChange("dob", e.target.value)} className="form-control" ></input></td></tr>
                            <tr><th>Email :</th><td><input type='text' value={studentDetails.email} onChange={e => handleChange("email", e.target.value)} className="form-control" ></input></td></tr>
                            <tr><th>Contact :</th><td><input type='text' value={studentDetails.contact} onChange={e => handleChange("contact", e.target.value)} className="form-control" ></input></td></tr>
                            <tr><th>City :</th><td><input type='text' value={studentDetails.city} onChange={e => handleChange("city", e.target.value)} className="form-control" ></input></td></tr>
                            <tr><th>Pincode :</th><td><input type='text' value={studentDetails.pincode} onChange={e => handleChange("pincode", e.target.value)} className="form-control" ></input></td></tr>

                            {/* <tr><th>Class : </th><td><select
                                // value={userDetails.userType}
                                id="inputState"
                                className="form-select"
                                onChange={e => {
                                    handleChange("std", e.target.value);
                                    getSectionYearByStandard(e.target.value);

                                }}
                                value={studentDetails.standard}
                            >
                                <option selected>Choose...</option>
                                {stdList.map((item, index) => (
                                    <option key={index} value={item}>
                                        {item}
                                    </option>
                                ))}
                            </select></td></tr> */}
                            <tr><th>Update </th><td><button className='btn btn-primary'>Click Here</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default UpdateStudent
