import React, { useState } from 'react'

function CreateUser() {
    const [userTypeList, setUserTypeList] = useState(["Student", "Teacher", "Peon", "Parent"]);
    const [stateList, setStateList] = useState(["MH", "AP", "TG"]);
    const [userDetails, setUserDetails] = useState({
        firstname: "",
        lastname: ""
    });
    const handleChange = (key, val) => {
        setUserDetails({ ...userDetails, key: [val] })
    }
    const submitDetails=()=>{
        console.log("Details::",userDetails.firstname,' ',userDetails.lastname);
    }
    return (
        <div>
            <h1>User</h1>
            <form className="row g-3">
                <div className="col-md-6">
                    <label for="inputEmail4" className="form-label">Email</label>
                    <input type="email" className="form-control" id="inputEmail4" />
                </div>
                <div className="col-md-6">
                    <label for="inputPassword4" className="form-label">Password</label>
                    <input type="password" className="form-control" id="inputPassword4" />
                </div>
                <div className="col-sm-5">
                    <label for="inputAddress" className="form-label">First Name</label>
                    <input type="text" className="form-control" id="inputAddress" placeholder="Name" onChange={(e)=>handleChange("firstname",e.target.value)}/>
                </div>
                <div className="col-sm-5">
                    <label for="inputAddress" className="form-label">Last Name</label>
                    <input type="text" className="form-control" id="inputAddress" placeholder="Last Name"onChange={(e)=>handleChange("lastname",e.target.value)} />
                </div>
                <div className="col-sm-2">
                    <label for="inputAddress" className="form-label">Age</label>
                    <input type="text" className="form-control" id="inputAddress" placeholder="1234 Main St" />
                </div>
                <div className="col-12">
                    <label for="inputAddress2" className="form-label">Address 2</label>
                    <input type="text" className="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" />
                </div>
                <div className="col-md-6">
                    <label for="inputCity" className="form-label">City</label>
                    <input type="text" className="form-control" id="inputCity" />
                </div>
                <div className="col-md-4">
                    <label for="inputState" className="form-label">State</label>
                    <select id="inputState" className="form-select">
                        <option selected>Choose...</option>
                        <option>...</option>
                    </select>
                </div>
                <div className="col-md-2">
                    <label for="inputZip" className="form-label">Zip</label>
                    <input type="text" className="form-control" id="inputZip" />
                </div>
                <div className="col-12">
                    <div className="form-check">
                        <input className="form-check-input" type="checkbox" id="gridCheck" />
                        <label className="form-check-label" for="gridCheck">
                            Check me out
                        </label>
                    </div>
                </div>
                <div className='col-sm-1'>
                    {
                        userTypeList.map((item, index) => (
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" />
                                <label class="form-check-label" for="flexRadioDefault1">
                                    {item}
                                </label>
                            </div>


                        ))
                    }
                </div>

                <div className="col-12">
                    <button type="submit" className="btn btn-primary">Sign in</button>
                </div>
            </form>
        </div>
    )
}

export default CreateUser
