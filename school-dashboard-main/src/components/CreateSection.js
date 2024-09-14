import React, { useState } from 'react'
import { createSectionAPI } from '../ApiCalls';

function CreateSection() {
   const [sectionName,setSectionName]=useState("");
    const saveData = () => {
        if(sectionName!=""){
            createSectionAPI({
                sectionName:sectionName,
                year:new Date().getFullYear()
            }).then((resp) => {
                console.log("Response: ", resp.data);
                // resetData();
                // nav("/studentList");
              });
        }else{

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
           

            
            <br></br>
            <div className='row'>
                <div className='col-sm-3' ></div>
                <div className='col-sm-6' >

                    <table className='table'>
                        <thead>
                            <tr><th>Section name : </th><td><input type='text' className="form-control" onChange={e=>setSectionName(e.target.value)} ></input></td></tr>

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
                            <tr><th>Submit </th><td><button className='btn btn-primary' onClick={e=>saveData()}>Click Here</button></td> </tr>

                        </thead>

                    </table>
                </div>
                <div className='col-sm-3' ></div>
            </div>

        </>
    )
}

export default CreateSection
