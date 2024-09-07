import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { MyContext } from './MyContext';
import { callAllClasses, callAllSections } from './ApiCalls';

function CreateStudent() {
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
    const resetData = () => {
        setReqDetails(initialValue);
    }
    const [sectionList, setsectionList] = useState([]);
    const [stdList, setStdList] = useState([]);
    useEffect(() => {
        // getClassList();
        // getSectionList();
    }, []);

    const getClassList = () => {
        callAllClasses().then((resp) => {
            setStdList(resp.data)
        })
    }
    const getSectionList = () => {
        callAllSections().then((resp) => {
            setsectionList(resp.data)
        })
    }
    const saveData = () => {
        console.log("Save Data: ", reqDetails);

    }
    return (
        <>

            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Create Student</h2></div>
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
                            <tr><th>Submit </th><td><button className='btn btn-primary' onClick={e => saveData()}>Create Student</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default CreateStudent
