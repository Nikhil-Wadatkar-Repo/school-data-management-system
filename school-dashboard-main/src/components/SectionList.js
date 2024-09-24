import axios from "axios";
import React, { useEffect, useState } from "react";
import { callSectionAPI } from "../ApiCalls";
import { useNavigate, useParams } from "react-router-dom";
import { updateDeleteSectionByIdAPI } from "./ApiCalls";

function SectionList({ sections, getAllSections, UpdateStateArray, setType }) {
  const nav = useNavigate();
  const [classList, setClassList] = useState([]);
  const { section, year, std } = useParams();
  const getStduentDetails = () => {
    callSectionAPI({
      std: std,
      year: year,
      section: section,
    }).then((resp) => {
      setClassList(resp.data);
    });
  };

  useEffect(() => {
    getStduentDetails();
  }, []);
  const action1 = (e, id) => {
    e.preventDefault();
    performAction("/updateSectionById/" + "I/" + id, e);
    setType("I");
  };

  const action2 = (e, id) => {
    e.preventDefault();
    performAction("/updateSectionById/" + "A/" + id, e);
    setType("A");
  };
  const action3 = (e, id) => {
    e.preventDefault();

    performAction("/updateSectionById/" + "D/" + id, e);
    setType("Initial");
  };

  const performAction = (url, e) => {
    e.preventDefault();
    // setTimeout(() => {
    //     updateDeleteSectionByIdAPI(url);
    // }, 500);
    updateDeleteSectionByIdAPI(url);
    getAllSections();
  };
  return (
    <>
      <h1>Section Wise Students</h1>
      <div className="row">
        <div id="table">
          <table className="table">
            <thead>
              <th>studunid</th>
              <th>name</th>
              <th>status</th>

              <th>status</th>
              <th>Action</th>
              <th>View</th>
            </thead>
            <tbody>
              {sections.map((item, index) => (
                <tr key={index}>
                  <td>{item.sectionID}</td>
                  <td>{item.sectionName}</td>
                  <td>{item.status}</td>

                  {item.status === "Active" ? (
                    <>
                      <td>
                        <button
                          className="btn btn-primary"
                          onClick={(e) => action1(e, item.sectionID)}
                        >
                          In-Active
                        </button>
                      </td>
                    </>
                  ) : (
                    ""
                  )}
                  {item.status === "In-active" ? (
                    <>
                      {" "}
                      <td>
                        <button
                          className="btn btn-alert"
                          onClick={(e) => action2(e, item.sectionID)}
                        >
                          Active
                        </button>
                      </td>
                    </>
                  ) : (
                    ""
                  )}

                  <td>
                    <button
                      className="btn btn-danger"
                      onClick={(e) => {
                        action3(e, item.sectionID);
                      }}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}

export default SectionList;
