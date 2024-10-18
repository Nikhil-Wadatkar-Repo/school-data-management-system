import React, { createContext } from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import CreateUser from "./components/CreateUser";
import Dashboard from "./components/Dashboard";
import { useState } from "react";
import TeachersList from "./components/TeachersList";
import ModalDemo from "./components/ModalDemo";
import HorizontalNavbar1 from "./components/HorizontalNavbar1";
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
import RoughCompo from "./components/RoughCompo";
import SectionList from "./components/SectionList";
import ExamAssignment from "./components/ExamAssignment";
import MarksDistribution from "./components/MarksDistribution";
import ExamDetails from "./components/ExamDetails";
import { schoolName } from "./Constants";
import VerticleNavbar1 from "./components/VerticleNavbar1";
import VerticleNavbar2 from "./components/VerticleNavbar2";
import VerticleNavbar3 from "./components/VerticleNavbar3";

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
        <HorizontalNavbar1></HorizontalNavbar1>

        <div className="container-fluid">
          <div className="row">
            <div className="col-sm-11">
              <Routes>
                <Route
                  path="/createClass/:classId?"
                  element={<CreateClass />}
                ></Route>
                <Route path="/examDetails" element={<ExamDetails />}></Route>
                <Route
                  path="/addExistedStudentToClass"
                  element={<AddExistedStudentToClass />}
                />
                <Route path="/classList" element={<ClassList />} />
                <Route
                  path="/studMarks/:id?"
                  element={<SubjectByStdUNID />}
                ></Route>
                <Route
                  path="/marksDistribution"
                  element={<MarksDistribution />}
                />
                <Route path="/createSection" element={<CreateSection />} />
                <Route path="/examAsignment" element={<ExamAssignment />} />
                <Route path="/createTeacher" element={<CreateTeacher />} />
                <Route path="/createStudent" element={<CreateStudent />} />
                <Route
                  path="/studentList/:section?/:year?/:std?"
                  element={<SectionList />}
                />

                <Route path="/create" element={<CreateUser />} />
                <Route path="/studentList" element={<StudentList />} />
                <Route path="/modal" element={<ModalDemo />} />
                <Route
                  path="/teacherList"
                  element={<TeachersList></TeachersList>}
                />
                <Route path="/dash" element={<Dashboard />} />
              </Routes>
            </div>
            <div className="col-sm-1">
              <div className="d-flex" style={{ height: "200px;" }}>
                <div className="vr">
                Notifications
                </div>
              </div>
              
            </div>
          </div>
        </div>
        <div className="footer fixed-bottom">
          <b>2 days ago</b>
        </div>
      </MyContext.Provider>
    </div>
  );
}

export default App;
