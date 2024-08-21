import React, { useContext, useState } from "react";
import AlertMessage from "./AlertMessage";
import { MyContext } from "./MyContext";

function TeacherSection() {
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
  const [subjectList, setSubjectList] = useState(["sub1", "sub2", "sub3"]);
  const [teacherList, setTeacherList] = useState([
    "teacher1",
    "teacher2",
    "teacher3",
  ]);
  let initialValue = {
    subject: "",
    teacher: "",
  };
  const [showAlert, setShowAlert] = useState(false);
  const [userDetails, setUserDetails] = useState(initialValue);
  const handleChange = (key, val) => {
    setUserDetails({ ...userDetails, [key]: val });
  };
  const submitReq = () => {
    if (
      userDetails.subject !== "" ||
      userDetails.subject !== "Select" ||
      userDetails.teacher !== "" ||
      userDetails.teacher !== "Select"
    ) {
      setAlert(true);
      setAlertTitle("Sorry!! Please peovide valide inputs");
      setAlertMessage("Check once");
      setMessageType("alert-danger");
      setShowAlert(true);
    } else {
      setAlert(true);
      setAlertTitle("Details are saved successfuly");
      setAlertMessage("");
      setMessageType("alert-success");
      setShowAlert(true);
      // resetData();
    }
  };
  return (
    <div>
      Teacher Section
      <div>
        {showAlert ? (
          <>
            <AlertMessage></AlertMessage>
          </>
        ) : (
          ""
        )}
      </div>
      <div className="row">
        <div className="col">
          <h5>Subject List</h5>
          <div>
            <label for="inputState" className="form-label">
              Select Type:
            </label>
            <select
              value={userDetails.subject}
              id="inputState"
              className="form-select"
              onChange={(e) => handleChange("subject", e.target.value)}
            >
              <option selected>Choose...</option>
              {subjectList.map((item, index) => (
                <option key={index} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </div>
        </div>
        <div className="col">
          <h5>Teacher List</h5>
          <div>
            <label for="inputState" className="form-label">
              Select Type:
            </label>
            <select
              value={userDetails.teacher}
              id="inputState"
              className="form-select"
              onChange={(e) => handleChange("teacher", e.target.value)}
            >
              <option selected>Choose...</option>
              {teacherList.map((item, index) => (
                <option key={index} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </div>
        </div>
        <div className="col">
          <button onClick={submitReq()}>Submit</button>
        </div>
      </div>
    </div>
  );
}

export default TeacherSection;
