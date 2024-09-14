import React, { useCallback, useContext, useEffect, useState } from 'react'
import { callAllSections, createSectionAPI } from '../ApiCalls';
import { MyContext } from './MyContext';
import { onlyAlphabetValidator } from '../util/Validator';
import AlertMessage from './AlertMessage';
import { updateDeleteSectionByIdAPI, updateDeleteStudentByIdAPI } from './ApiCalls';

function CreateSection() {
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
    const [sections, setSections] = useState([]);
    let initialValue = {
        name: "",
        username: "",
        age: "",
        contact: "",
        userType: "",
        email: "",
        city: "",
        pincode: "",
        password: "",
        address: "",
        sectionName: "",
    }
    const [reqDetails, setReqDetails] = useState(initialValue);
    const handleChange = (key, val) => {
        setReqDetails({ ...reqDetails, [key]: val });
    };

    useEffect(() => {
        gellAllSection();

    }, []);
    const callBackSection = useCallback(() => {
        gellAllSection();
      }, [sections]);

    const validateRequest = (reqDetails) => {

        let errorDetails = {
            error: false,
            message: "",
        };
        let message = "";
        if (
            reqDetails.sectionName === "" ||
            !onlyAlphabetValidator(reqDetails.sectionName)
        )
            message = "Section Name";

        if (message === "")
            return {
                error: true,
                message: "",
            };
        else
            return {
                error: false,
                message: message,
            };
    };
    const resetData = () => {
        setReqDetails(initialValue);
    }

    const saveData = (e) => {
        e.preventDefault();
        console.log("Save data method: ", e);
        // debugger;

        let isValid = validateRequest(reqDetails);
        if (isValid.error === false) {
            setAlert(true);
            setAlertTitle("Sorry!! Please provide valide inputs");
            setAlertMessage(isValid.message);
            setMessageType("alert-danger");
            setShowAlert(true);
        } else {
            createSectionAPI({
                sectionName: reqDetails.sectionName,
                year: new Date().getFullYear()
            }).then((resp) => {
                let response = resp.data;
                console.log("Respose: ", response);

                if (response.flag === true) {
                    setAlert(true);
                    setAlertTitle("Details are saved successfuly");
                    setAlertMessage("");
                    setMessageType("alert-success");
                    setShowAlert(true);
                    resetData();
                    fetchData();
                }
                else {
                    setAlert(true);
                    setAlertTitle("Sorry!! Please provide valide inputs");
                    setAlertMessage(response.message);
                    setMessageType("alert-danger");
                    setShowAlert(true);
                }
            }).catch((resp) => {
                let response1 = resp.response.data;
                console.log("Respose: ", response1);

                if (response1.flag === false) {
                    setAlert(true);
                    setAlertTitle("Sorry!! Please provide valide inputs");
                    setAlertMessage(response1.message);
                    setMessageType("alert-danger");
                    setShowAlert(true);
                }
            });
        }
    };
    const gellAllSection = () => {
        callAllSections().then((resp) => {
            console.log("Sections: ", resp.data);
            setSections(resp.data);
        })
    }
    const performAction=(url)=>{
        debugger;
        // updateSectionById/I/204
        updateDeleteSectionByIdAPI(url);
        fetchData();
    }

    const fetchData = async () => {
        try {
          const response = await callAllSections();
          setSections(response.data);
        } catch (error) {
        //   setError(error);
        } finally {
        //   setLoading(false);
        }
      };
    return (
        <>

            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Create Section</h2></div>
                <div className='col'></div>
            </div>
            <br></br>
            {showAlert ? (
                <>
                    <AlertMessage></AlertMessage>
                </>
            ) : (
                ""
            )}


            <br></br>
            <div className='row'>
                <div className='col-sm-3' >section:{reqDetails.sectionName}</div>
                <div className='col-sm-6' >

                    <table className='table'>
                        <thead>
                            <tr><th>Section name : </th><td><input type='text' className="form-control"
                                value={reqDetails.sectionName}
                                onChange={e => handleChange("sectionName", e.target.value)} ></input></td></tr>

                            {/* <tr><th>No. of Students :</th><td><input type='text' className="form-control" ></input></td></tr>
                            <tr><th>Section : </th><td> <select
                                // value={userDetails.userType}
                                id="inputState"
                                className="form-select"
                            // onChange={(e) => handleChange("userType", e.target.value)}
                            >
                                <option selected>Choose...</option>
                                {userTypeList.map((item, index) => (
                                    <option key={index} value={item}>
                                        {item}
                                    </option>
                                ))}
                            </select></td></tr>
                            <tr><th>Year : </th><td>
                                <select
                                    // value={userDetails.userType}
                                    id="inputState"
                                    className="form-select"
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
                                // value={userDetails.userType}
                                id="inputState"
                                className="form-select"
                            >
                                <option selected>Choose...</option>
                                {teacherList.map((item, index) => (
                                    <option key={index} value={item}>
                                        {item}
                                    </option>
                                ))}
                            </select></td></tr> */}
                            <tr><th>Submit </th><td><button className='btn btn-primary' onClick={(e) => saveData(e)}>Click Here</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Section List</h2></div>
                <div className='col'></div>
            </div>
            <div className='row'>
                <div className='col'></div>
                <div className='col'>
                    <table className='table'>
                        <thead>
                            <th>Id</th>
                            <th>Name</th>
                        </thead>
                        <tbody>
                            {
                                sections.map((item, index) => (
                                    <tr key={index}>
                                        <td>{item.sectionID}</td>
                                        <td>{item.sectionName}</td>

                                        {
                                            item.status==="Active"?<>
                                            <td><button className='btn' style={{backgroundColor:"#ffc107"}} 
                                         onClick={e=> performAction("/updateSectionById/"+"I/"+item.sectionID)}>In-Active</button></td>
                                            </>:""
                                        }
                                        {
                                          item.status==="In-active"?<> <td><button className='btn' style={{backgroundColor:"#dc3545"}}
                                          onClick={e=> performAction("/updateSectionById/"+"A/"+item.sectionID)}
                                          >Active</button></td></>:""  
                                        }
                                        
                                       
                                        <td><button className='btn' style={{backgroundColor:" rgb(35 225 137)"}}
                                        onClick={e=> performAction("/updateSectionById/"+"D/"+item.sectionID)} >Delete</button></td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>



                </div>
                <div className='col'></div>
            </div>
        </>
    )
}

export default CreateSection
