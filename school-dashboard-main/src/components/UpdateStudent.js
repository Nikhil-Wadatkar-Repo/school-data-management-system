import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { MyContext } from './MyContext';

import { getStudentByIdAPI, updateStudentAPI } from '../ApiCalls';

function UpdateStudent() {
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
        name: "",
        contact: "",
        pincode: "",
        city: 0,
        email: "",
        std: "",
        section: "",
    }
    const [reqDetails, setReqDetails] = useState(initialValue);
    const handleChange = (key, val) => {
        setReqDetails({ ...reqDetails, [key]: val });
    }
    const {id}=useParams();
    const resetData = () => {
        setReqDetails(initialValue);
    }
    const [sectionList, setsectionList] = useState([]);
    const [stdList, setStdList] = useState([]);
    useEffect(() => {
        getDetailsById();
    }, []);

    const getDetailsById = () => {
        // debugger;
        getStudentByIdAPI(id).then((resp) => {
            setReqDetails(resp.data)
        })
    }

    const saveData = () => {
        console.log("Save Data: ", reqDetails);

        updateStudentAPI(reqDetails).then(resp=>{
            console.log("Response: ",resp.data);
            resetData();
            nav("/studentList");
            
        })

    }
    return (
        <>

            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Update Student:: {id}</h2></div>
                <div className='col'></div>
            </div>
            <br></br>



            <br></br>
            <div className='row'>
                <div className='col-sm-3' ></div>
                <div className='col-sm-6' >

                    <table className='table table-hover'>
                        <thead>
                            <tr><th>Full Name : </th><td><input type='text' className="form-control" value={reqDetails.name} onChange={e => handleChange("name", e.target.value)}></input></td></tr>


                            <tr><th>Email :</th><td><input type='text' className="form-control" value={reqDetails.email} onChange={e => handleChange("email", e.target.value)}></input></td></tr>
                            <tr><th>Contact :</th><td><input type='text' className="form-control" value={reqDetails.contact} onChange={e => handleChange("contact", e.target.value)}></input></td></tr>
                            <tr><th>City :</th><td><input type='text' className="form-control" value={reqDetails.city} onChange={e => handleChange("city", e.target.value)}></input></td></tr>
                            <tr><th>Pincode :</th><td><input type='text' className="form-control" value={reqDetails.pincode} onChange={e => handleChange("pincode", e.target.value)}></input></td>
                           
                            </tr>

                            {/* <tr><th>Class : </th><td><select
                                value={reqDetails.std}
                                id="inputState"
                                className="form-select"
                                onChange={e => handleChange("std", e.target.value)}
                            >
                                <option selected  >Choose...</option>
                                {stdList.map((item, index) => (
                                    <option key={index} value={item.classId}>
                                        {item.standard}
                                    </option>
                                ))}
                            </select></td></tr>
                            <tr><th>Section : </th><td><select
                                 value={reqDetails.section}
                                id="inputState"
                                className="form-select"
                                onChange={e => handleChange("section", e.target.value)}
                            >
                                <option selected  >Choose...</option>
                                {sectionList.map((item, index) => (
                                    <option key={index} value={item.sectionId}>
                                        {item.sectionName}
                                    </option>
                                ))}
                            </select></td></tr> */}
                            <tr><th>Submit </th><td><button className='btn btn-primary' onClick={e => saveData()}>Update Details</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default UpdateStudent
