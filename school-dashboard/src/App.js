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
import ClassList from "./components/ClassList";

import CreateTeacher from "./components/CreateTeacher";
import CreateClass from "./components/CreateClass";
import CreateSection from "./components/CreateSection";
import CreateStudent from "./components/CreateStudent";
// import StudentList from "./components/StudentList";
import SectionList from "./components/SectionList";
import UpdateStudent from "./components/UpdateStudent";
import SubjectByStdUNID from "./components/SubjectByStdUNID";

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
          <Routes>
              <Route path="/classList" element={<ClassList />} />
              <Route path="/createSection" element={<CreateSection />} />
              <Route path="/createTeacher" element={<CreateTeacher />} />
              <Route path="/createStudent" element={<CreateStudent />} />
              <Route path="/updateStudent/:id" element={<UpdateStudent />} />
              <Route path="/subjectByStdUNID/:id" element={<SubjectByStdUNID />} />
              <Route path="/studentList/:section/:year/:std" element={<SectionList />} />
              <Route path="/createClass" element={<CreateClass />} />
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
