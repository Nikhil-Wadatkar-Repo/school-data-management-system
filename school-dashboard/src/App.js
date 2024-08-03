import React, { createContext } from "react";
import "./App.css";
import { Routes, Route, useNavigate } from "react-router-dom";
import CreateUser from "./components/CreateUser";
import Dashboard from "./components/Dashboard";
import { useState } from "react";
import { routes } from "./Constants";
import TeachersList from "./components/TeachersList";
import { Modal } from "bootstrap";
import ModalDemo from "./components/ModalDemo";
import Navbar from "./components/Navbar";
import HorizontalNavbar from "./components/HorizontalNavbar";
import { MyContext } from "./components/MyContext";
import AdminTeachersExport from "./components/Admin/AdminTeachersExport";

function App() {
  const [text, setText] = useState("Admin");
  const [alert, setAlert] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");
  const [alertTitle, setAlertTitle] = useState("");
  const [messageType, setMessageType] = useState("alert-success");

  return (
    <div>
      <MyContext.Provider
        value={{
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
        }}
      >
        <h1 style={{ backgroundColor: "#79d789", color: "white" }}>
          Dashboard
        </h1>
        <HorizontalNavbar></HorizontalNavbar>

        <div>
          <div className="row">
            <div className="col-2">{/* <Navbar></Navbar> */}</div>
            <div className="col-8">
              <p>main section</p>

              <Routes>
                <Route path="/create" element={<CreateUser />} />
                <Route path="/modal" element={<ModalDemo />} />
                <Route
                  path="/teacherList"
                  element={<TeachersList></TeachersList>}
                />
                <Route
                  path="/adminTeacherExcel"
                  element={<AdminTeachersExport />}
                />
                {/* <Route index element={<Home />} />
                  <Route path="blogs" element={<Blogs />} />
                  <Route path="contact" element={<Contact />} />
                  <Route path="*" element={<NoPage />} /> */}
                <Route path="/dash" element={<Dashboard />} />
              </Routes>
            </div>
            <div className="col-2">Notifications</div>
          </div>
        </div>
        <div className="footer fixed-bottom">2 days ago</div>
      </MyContext.Provider>
    </div>
  );
}

export default App;
