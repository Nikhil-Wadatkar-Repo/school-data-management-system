import React, { useContext, useState } from "react";
import AlertMessage from "./AlertMessage";
import { MyContext } from "./MyContext";
import {
  onlyAlphabetValidator,
  phoneValiddator,
  zipCodeValidator,
} from "../util/Validator";

function CreateUser() {
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
  const [userTypeList, setUserTypeList] = useState([
    "Student",
    "Teacher",
    "Peon",
    "Parent",
  ]);
  const [stateList, setStateList] = useState(["MH", "AP", "TG"]);
  const [userDetails, setUserDetails] = useState({
    firstname: "",
    lastname: "",
    age: "",
    contact: "",
    userType: "",
    email: "",
    city: "",
    zipCode: "",
    password: "",
    address: "",
  });
  const [showAlert, setShowAlert] = useState(false);
  const handleChange = (key, val) => {
    console.log("key:", key, "   val:", val);
    setUserDetails({ ...userDetails, [key]: val });
  };
  const submitDetails = (e) => {
    e.preventDefault();
    console.log("Details::", userDetails);
    let isValid = validateRequest(userDetails);
    if (isValid.error === false) {
      setAlert(true);
      setAlertTitle("Sorry BHaiii!!");
      setAlertMessage(isValid.message);
      setMessageType("alert-danger");
      setShowAlert(true);
    } else {
      setAlert(false);
      setShowAlert(false);
    }
  };

  const validateRequest = (reqDetails) => {
    let errorDetails = {
      error: false,
      message: "",
    };
    let message = "";
    if (
      reqDetails.firstname === "" ||
      !onlyAlphabetValidator(reqDetails.firstname)
    )
      message = message + " First Name ";
    if (!onlyAlphabetValidator(reqDetails.lastname))
      message = message + " Last Name ";
    if (!zipCodeValidator(reqDetails.zipCode)) message = message + " Zip Code ";
    if (!phoneValiddator(reqDetails.contact)) message = message + " Zip Code ";
    if (!phoneValiddator(reqDetails.age)) message = message + " Age ";
    if (!onlyAlphabetValidator(reqDetails.address))
      message = message + " Address ";
    if (!onlyAlphabetValidator(reqDetails.city)) message = message + " City ";

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

  return (
    <div>
      <h1>User</h1>
      {showAlert ? (
        <>
          <AlertMessage></AlertMessage>
        </>
      ) : (
        ""
      )}
      <form className="row g-3">
        <div className="col-sm-5">
          <label for="inputAddress" className="form-label">
            First Name
          </label>
          <input
            type="text"
            className="form-control"
            id="inputAddress"
            placeholder="Name"
            onChange={(e) => handleChange("firstname", e.target.value)}
          />
        </div>
        <div className="col-sm-5">
          <label for="inputAddress" className="form-label">
            Last Name
          </label>
          <input
            type="text"
            className="form-control"
            id="inputAddress"
            placeholder="Last Name"
            onChange={(e) => handleChange("lastname", e.target.value)}
          />
        </div>
        <div className="col-sm-2">
          <label for="inputAddress" className="form-label">
            Age
          </label>
          <input
            type="text"
            className="form-control"
            id="inputAddress"
            onChange={(e) => handleChange("age", e.target.value)}
          />
        </div>
        <div className="col-md-6">
          <label for="inputEmail4" className="form-label">
            Email
          </label>
          <input
            type="email"
            className="form-control"
            id="inputEmail4"
            onChange={(e) => handleChange("email", e.target.value)}
          />
        </div>
        <div className="col-md-6">
          <label for="inputPassword4" className="form-label">
            Password
          </label>
          <input
            type="password"
            className="form-control"
            id="inputPassword4"
            onChange={(e) => handleChange("password", e.target.value)}
          />
        </div>
        <div className="col-6">
          <label for="inputAddress2" className="form-label">
            Address 2
          </label>
          <input
            type="text"
            className="form-control"
            id="inputAddress2"
            placeholder="Apartment, studio, or floor"
            onChange={(e) => handleChange("address", e.target.value)}
          />
        </div>
        <div className="col-6">
          <label for="inputAddress2" className="form-label">
            Contact
          </label>
          <input
            type="text"
            className="form-control"
            id="inputAddress2"
            placeholder="123465890"
            onChange={(e) => handleChange("contact", e.target.value)}
          />
        </div>
        <div className="col-md-6">
          <label for="inputCity" className="form-label">
            City
          </label>
          <input
            type="text"
            className="form-control"
            id="inputCity"
            onChange={(e) => handleChange("city", e.target.value)}
          />
        </div>
        <div className="col-md-4">
          <label for="inputState" className="form-label">
            Select Type:
          </label>
          <select
            id="inputState"
            className="form-select"
            onChange={(e) => handleChange("userType", e.target.value)}
          >
            <option selected>Choose...</option>
            {userTypeList.map((item, index) => (
              <option key={index} value={item}>
                {item}
              </option>
            ))}
          </select>
        </div>
        <div className="col-md-2">
          <label for="inputZip" className="form-label">
            Zip
          </label>
          <input
            type="text"
            className="form-control"
            id="inputZip"
            onChange={(e) => handleChange("zipCode", e.target.value)}
          />
        </div>

        {/* <div className='col-sm-1'>
                    {
                        userTypeList.map((item, index) => (
                            <div class="form-check">
                                <input class="form-check-input" type="radio" value={item} name="flexRadioDefault" id="flexRadioDefault1" onChange={(e) => handleChange("userType", e.target.value)} />
                                <label class="form-check-label" for="flexRadioDefault1">
                                    {item}
                                </label>
                            </div>


                        ))
                    }
                </div> */}

        <div className="col-12">
          <button
            type="submit"
            className="btn btn-primary"
            onClick={(e) => submitDetails(e)}
          >
            Sign in
          </button>
        </div>
      </form>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br> <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br> <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br> <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <p>ddd</p>
    </div>
  );
}

export default CreateUser;
