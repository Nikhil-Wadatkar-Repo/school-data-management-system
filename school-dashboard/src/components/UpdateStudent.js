import React, { useContext, useState } from 'react'
import AlertMessage from './AlertMessage';
import { MyContext } from './MyContext';

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
    const [userTypeList, setUserTypeList] = useState([
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    ]);
    const [yearList, setYearList] = useState([
        2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
    ]);
    const [teacherList, setTeacherList] = useState([
        "Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6"
    ]);
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
                <div className='col-sm-6' >

                    <table className='table table-hover'>
                        <thead>
                            <tr><th>Full Name : </th><td><input type='text' className="form-control" ></input></td></tr>

                            <tr><th>Age :</th><td><input type='text' className="form-control" ></input></td></tr>
                            <tr><th>Email :</th><td><input type='text' className="form-control" ></input></td></tr>
                            <tr><th>Contact :</th><td><input type='text' className="form-control" ></input></td></tr>
                            <tr><th>City :</th><td><input type='text' className="form-control" ></input></td></tr>
                            <tr><th>Pincode :</th><td><input type='text' className="form-control" ></input></td></tr>
                            
                            <tr><th>Class : </th><td><select
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
                            </select></td></tr>
                            <tr><th>Submit </th><td><button className='btn btn-primary'>Click Here</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default UpdateStudent
