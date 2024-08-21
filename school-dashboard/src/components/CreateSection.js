import React, { useState } from 'react'

function CreateSection() {
    const [userTypeList, setUserTypeList] = useState([
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    ]);
    const [yearList, setYearList] = useState([
        2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
    ]);
    const [teacherList, setTeacherList] = useState([
        "Teacher 1", "Teacher 2", "Teacher 3", "Teacher 4", "Teacher 5", "Teacher 6"
    ]);
    return (
        <>

            <div className='row'>
                <div className='col'></div>
                <div className='col'><h2>Create Section</h2></div>
                <div className='col'></div>
            </div>
            <br></br>
           

            
            <br></br>
            <div className='row'>
                <div className='col-sm-3' ></div>
                <div className='col-sm-6' >

                    <table className='table'>
                        <thead>
                            <tr><th>Section name : </th><td><input type='text' className="form-control" ></input></td></tr>

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
                            <tr><th>Submit </th><td><button className='btn btn-primary'>Click Here</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default CreateSection
