import { getValue } from "@testing-library/user-event/dist/utils";
import React, { useState } from "react";

function Component1() {
  let submitButtonArray = {
    Monday: "",
    Tuesday: "",
    Wednesday: "",
    Thursday: "",
    Friday: "",
    Saturday: "",
  };
  const [submitFlag, setSubmitFlag] = useState(submitButtonArray);
  let aList = [
    {
      day: "01/01",
      status: "true",
      dayName: "Monday",
    },
    {
      day: "01/02",
      status: "true",
      dayName: "Tuesday",
    },
    {
      day: "01/03",
      status: "true",
      dayName: "Wednesday",
    },
    {
      day: "01/04",
      status: "true",
      dayName: "Thursday",
    },
    {
      day: "01/05",
      status: "true",
      dayName: "Friday",
    },
    {
      day: "01/06",
      status: "true",
      dayName: "Saturday",
    },
  ];
  const [list, setList] = useState(aList);
  const [disableSubmit, setDisableSubmit] = useState(false);
  const setClick = (dayName, values) => {
    console.log(dayName, " ", values);

    setSubmitFlag({ ...submitFlag, [dayName]: values });
  };
  const submitTimeSheet = () => {
    const keys = Object.keys(submitFlag);
    for (const key of keys) {
      console.log(key + ": " + submitFlag[key]);
    }
  };
  return (
    <div>
      <div className="row">
        <div className="col-sm-1"></div>
        <div className="col-sm-10">
          <h5>TimeSheet</h5>
        </div>
        <div className="col-sm-1"></div>
      </div>

      <br></br>
      <div className="row">
        <div className="col-sm-1"></div>
        <div className="col-sm-10">
          <div className="row row-cols-auto">
            <div className="col">
              <select className="form-control" name="cars" id="cars">
                <option value="saab" selected={true}>
                  -Select-
                </option>
                <option value="saab">Saab</option>
                <option value="saab">Saab</option>
                <option value="mercedes">Mercedes</option>
                <option value="audi">Audi</option>
              </select>
            </div>
            <div className="col">
              <input className="form-control" type="date" />
            </div>
            <div className="col">
              <button
                disabled={disableSubmit}
                onClick={(e) => submitTimeSheet()}
                className="btn btn-primary"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
        <div className="col-sm-1"></div>
      </div>

      <br></br>
      <div className="row">
        <div className="col-sm-1"></div>
        <div className="col-sm-10">
          <table className="table table-bordered  table-rounded">
            <thead className="table-dark text-dark">
              <th style={{ backgroundColor: "goldenrod" }}>
                Days
                <br></br>
              </th>
              {list.map((item, index) => (
                <th key={index} style={{ backgroundColor: "#34ef89" }}>
                  {item.dayName}
                  <br></br>
                  {item.day}
                </th>
              ))}
            </thead>
            <tbody>
              <tr>
                <td>
                  Present <br></br>
                  Absent
                </td>
                {list.map((item, index) => (
                  <td key={index}>
                    <input
                      type="radio"
                      name={item.dayName}
                      value={"Present"}
                      onClick={(e) => setClick(item.dayName, e.target.value)}
                    />{" "}
                    <br></br>
                    <input
                      type="radio"
                      name={item.dayName}
                      value={"Absent"}
                      onClick={(e) => setClick(item.dayName, e.target.value)}
                    />
                  </td>
                ))}
              </tr>
            </tbody>
          </table>
        </div>
        <div className="col-sm-1"></div>
      </div>
    </div>
  );
}

export default Component1;
