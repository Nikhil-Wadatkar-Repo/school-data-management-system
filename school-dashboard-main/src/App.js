import React, { createContext } from "react";
import "./App.css";
import { Routes, Route, useNavigate } from "react-router-dom";
import CreateUser from "./components/CreateUser";
import Dashboard from "./components/Dashboard";
import { useState } from "react";
import TeachersList from "./components/TeachersList";
import ModalDemo from "./components/ModalDemo";
import HorizontalNavbar from "./components/HorizontalNavbar";
import { MyContext } from "./components/MyContext";
import ClassList from "./components/ClassList";
import CreateTeacher from "./components/CreateTeacher";
import CreateClass from "./components/CreateClass";
import CreateSection from "./components/CreateSection";
import CreateStudent from "./components/CreateStudent";
import UpdateStudent from "./components/UpdateStudent";
import SubjectByStdUNID from "./components/SubjectByStdUNID";
import AddExistedStudentToClass from "./components/AddExistedStudentToClass";
import AddNewStudentToClass from "./components/AddNewStudentToClass";
import StudentList from "./components/StudentList";
import Parentcomp from "./components/Parentcomp";
import VerticleNavbar from "./components/VerticleNavbar";

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
            <div className="col-sm-2">

           <VerticleNavbar></VerticleNavbar>


            </div>
            <div className="col-sm-8">
              <Routes>
              <Route path="/" element={<Parentcomp />} />
                <Route
                  path="/addExistedStudentToClass"
                  element={<AddExistedStudentToClass />}
                />
                <Route
                  path="/addNewStudentToClass"
                  element={<AddNewStudentToClass />}
                />
                <Route path="/classList" element={<ClassList />} />
                
                <Route path="/createSection" element={<CreateSection />} />
                <Route path="/createTeacher" element={<CreateTeacher />} />
                <Route path="/createStudent" element={<CreateStudent />} />
                <Route path="/updateStudent/:id" element={<UpdateStudent />} />
                <Route
                  path="/subjectByStdUNID/:id"
                  element={<SubjectByStdUNID />}
                />
                <Route path="/studentList" element={<StudentList />} />
                <Route path="/createClass" element={<CreateClass />} />
                <Route path="/create" element={<CreateUser />} />
                <Route path="/modal" element={<ModalDemo />} />
                <Route
                  path="/teacherList"
                  element={<TeachersList></TeachersList>}
                />
                <Route path="/dash" element={<Dashboard />} />
              </Routes>
            </div>
            <div className="col-sm-2">Notifications</div>
          </div>
        </div>
        <div className="footer fixed-bottom"><b>2 days ago</b></div>
      </MyContext.Provider>
    </div>
  );
}

export default App;
