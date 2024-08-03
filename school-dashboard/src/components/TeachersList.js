import React, { useContext } from "react";
import { MyContext } from "./MyContext";

function TeachersList() {
  const { text, setText } = useContext(MyContext);
  return (
    <div className="container">
      <div className="row">
        <div>
          <div className="row">
            <div className="col-sm-3">
              <label for="inputAddress" className="form-label">
                First Name
              </label>
              <input
                type="text"
                className="form-control"
                id="inputAddress"
                placeholder="Name"
                // onChange={(e) => handleChange("firstname", e.target.value)}
              />
            </div>
            <div className="col-sm-3">
              <label for="inputAddress" className="form-label">
                First Name
              </label>
              <input
                type="text"
                className="form-control"
                id="inputAddress"
                placeholder="Name"
                // onChange={(e) => handleChange("firstname", e.target.value)}
              />
            </div>
            <div className="col-sm-3">
              <label for="inputAddress" className="form-label">
                First Name
              </label>
              <input
                type="text"
                className="form-control"
                id="inputAddress"
                placeholder="Name"
                // onChange={(e) => handleChange("firstname", e.target.value)}
              />
            </div>
            <div className="col-sm-3">
              <label for="inputAddress" className="form-label">
                Last Name
              </label>
              <input
                type="text"
                className="form-control"
                id="inputAddress"
                placeholder="Name"
                // onChange={(e) => handleChange("firstname", e.target.value)}
              />
            </div>
          </div>
          <div className="row">
            <button>Update</button>
          </div>
        </div>
      </div>
      <div className="row">
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">First</th>
              <th scope="col">Last</th>
              <th scope="col">Handle</th>
              {text === "Admin" ? <th scope="col">Modify</th> : ""}
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
              {text === "Admin" ? <td>DDDD</td> : ""}
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Larry</td>
              <td>the Bird</td>
              <td>@twitter</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default TeachersList;
