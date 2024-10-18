import React, { useCallback, useContext, useEffect, useState } from "react";
import { callAllSections, createSectionAPI } from "../ApiCalls";
import { MyContext } from "./MyContext";
import { onlyAlphabetValidator } from "../util/Validator";
import AlertMessage from "./AlertMessage";
import {
  updateDeleteSectionByIdAPI,
  updateDeleteStudentByIdAPI,
} from "./ApiCalls";
import SectionList from "./SectionList";

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
  };
  const [reqDetails, setReqDetails] = useState(initialValue);
  const [reqDetails1, setReqDetails1] = useState(initialValue);
  const handleChange = (key, val) => {
    setReqDetails({ ...reqDetails, [key]: val });
  };
  const [type, setType] = useState("Initial");
  useEffect(() => {
    // if (type === "Initial"|| type==="I"
    // ) {
    //     console.log("Reload ::", type);
    //     getAllSections();
    //     if (type === "Initial")
    //         setType("ll")
    // }
    getAllSections();
  }, [type]);

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
  };

  const saveData = (e) => {
    e.preventDefault();
    console.log("Save data method: ", e);
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
        year: new Date().getFullYear(),
      })
        .then((resp) => {
          let response = resp.data;
          console.log("Respose: ", response);

          if (response.flag === true) {
            setAlert(true);
            setAlertTitle("Details are saved successfuly");
            setAlertMessage("");
            setMessageType("alert-success");
            setShowAlert(true);
            resetData();
            getAllSections();
          } else {
            setAlert(true);
            setAlertTitle("Sorry!! Please provide valide inputs");
            setAlertMessage(response.message);
            setMessageType("alert-danger");
            setShowAlert(true);
          }
        })
        .catch((resp) => {
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
  const getAllSections = () => {
    callAllSections().then((resp) => {
      console.log("Sections: ", resp.data);
      setSections(resp.data);
    });
  };
  const performAction = (url, e) => {
    // e.preventDefault();
    // setTimeout(() => {
    //     updateDeleteSectionByIdAPI(url);
    // }, 500);
    updateDeleteSectionByIdAPI(url);
    getAllSections();
  };
  const UpdateStateArray = (id, operation) => {
    console.log("UpdateStateArray: ", operation);
    const element = findElement(id);

    if (operation === "A") {
      setSections((prevItems) =>
        prevItems.map((item) =>
          item.sectionID === id ? { ...item, status: "Active" } : item
        )
      );
    } else if (operation === "I") {
      setSections((prevItems) =>
        prevItems.map((item) =>
          item.sectionID === id ? { ...item, status: "In-Active" } : item
        )
      );
    }
    getAllSections();
  };

  const findElement = (id, operation) => {
    return sections.find((item) => item.sectionID === id);
  };

  // const fetchData = async () => {
  //     try {
  //         const response = await callAllSections();
  //         setSections(response.data);
  //     } catch (error) {
  //         //   setError(error);
  //     } finally {
  //         //   setLoading(false);
  //     }
  // };
  return (
    <>
          {showAlert ? (
        <>
          <AlertMessage></AlertMessage>
        </>
      ) : (
        ""
      )}
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <h2>Create Section</h2>
        </div>
        <div className="col"></div>
      </div>
      <div className="row">
        <div className="col-sm-3"></div>
        <div className="col-sm-6">
          <table className="table">
            <thead>
              <tr>
                <th>Section name : </th>
                <td>
                  <input
                    type="text"
                    className="form-control"
                    value={reqDetails.sectionName}
                    onClick={(e) => setAlert(false)}
                    onChange={(e) =>
                      handleChange("sectionName", e.target.value)
                    }
                  ></input>
                </td>
              </tr>
              <tr>
                <th>Submit </th>
                <td>
                  <button
                    className="btn btn-primary"
                    onClick={(e) => {
                      saveData(e);
                    }}
                  >
                    Click Here
                  </button>
                </td>{" "}
              </tr>
            </thead>
          </table>
        </div>
        <div className="col-sm-3"></div>
      </div>

      <SectionList
        sections={sections}
        performAction={performAction}
        UpdateStateArray={UpdateStateArray}
        setType={setType}
        getAllSections={() => getAllSections()}
      ></SectionList>
    </>
  );
}

export default CreateSection;
