import React, { useEffect, useState } from "react";

function AdminTeachersExport() {
  // call api for getting all fields of user
  const [list, setList] = useState([
    {
      label: "ID",
      checked: true,
      id: "checkbx1",
    },
    {
      label: "NAME",
      checked: false,
      id: "checkbx2",
    },
    {
      label: "AGE",
      checked: true,
      id: "checkbx3",
    },
  ]);

  const findIndexBasedOnKey = (key) => {
    return list.findIndex((obj) => obj.id === key);
  };
  const setListValue = (key, val) => {
    console.log(key, "  ", val, "index: ", findIndexBasedOnKey(key));
    let index = findIndexBasedOnKey(key);
    const newArray = [...list];
    const oldElement = list[index];
    newArray[index] = { ...oldElement, checked: val };
    setList(newArray);
  };

  const submitUserDetails = () => {
    console.log("User details:", list);
  };

  return (
    <div>
      <div className="row">
        <div className="col-sm">
          <h3>User Details</h3>
        </div>
        <div className="col">
          <div className="row">
            {list.map((item, index) => (
              <>
                <div key={index} className="col">
                  <input
                    type="checkbox"
                    checked={item.checked}
                    className="btn-check"
                    id={item.id}
                    autocomplete="off"
                    onClick={(e) => setListValue(e.target.id, e.target.checked)}
                  />
                  <label className="btn btn-outline-primary" for={item.id}>
                    {item.label}
                  </label>
                </div>
              </>
            ))}
          </div>
          <br></br>
          {/* <hr className="mt-1 mb-1"/> */}
          <br></br>
          <div> <button className="btn btn-danger" onClick={(e) => submitUserDetails()}>Submit</button></div>
        </div>
      </div>
      <br></br>
      <hr className="mt-1 mb-1"/>
      <br></br>
      <br></br>
      <div className="row">
        <div className="col-sm">
          
        </div>
        <div className="col">
         
        </div>
      </div>
      <br></br>
      <br></br>
    </div>
  );
}

export default AdminTeachersExport;
