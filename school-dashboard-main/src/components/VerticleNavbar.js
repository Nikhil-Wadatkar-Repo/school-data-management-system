import React from "react";
import { useNavigate } from "react-router-dom";
import $ from 'jquery';

function VerticleNavbar() {
  const navigate = useNavigate();
  // const checkClickedOrNot=(id)=>{
  //   // debugger
  //   // $(id).click(function(){
  //     console.log ("Clicked on this button",id);
  //     $(id).click(function(){
  //     $(id).css('backgroundColor', '#21c67a')
  //     $("#btId").hide();
  //  });
  // }
 
  return (
    <div>
      <ul className="list-group ">
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/createStudent")}
          >
            Create Student
          </a>
        </li>
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/studentList")}
          >
            Student List
          </a>
        </li>
        
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/addExistedStudentToClass")}
          >
            Add Existed Student To Class
          </a>
        </li>
        <li className="list-group-item">
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/addNewStudentToClass")}
          >
            Add New Student To Class
          </a>
        </li>

       

        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/createTeacher")}
          >
            Create Teacher
          </a>
        </li>
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/teacherList")}
          >
            Teacher List
          </a>
        </li>
       
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/createSection")}
            style={{"overflowWrap": "break-word"}}
          >
            Create Section
          </a>
        </li>
        {/* <li className="list-group-item"
        aria-checked={"true"}
        >
          <a
            className="dropdown-item"
            onClick={(e) => navigate("/sectionList")}
           
          >
            Section List
          </a>
        </li> */}
       
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a
           id="id2" 
            className="dropdown-item"
            onClick={(e) => {navigate("/createClass")
              // checkClickedOrNot(e.target.id)
          
              
            }}
            style={{fontStyle:"bold"}}
          >
            Create Class
          </a>
        </li>
        <li className="list-group-item" style={{backgroundColor:"#21c67a"}}>
          <a  id="id1" className="dropdown-item" onClick={(e) => {navigate("/classList")
            // checkClickedOrNot(e.target.id)
          }}>
            Class List
          </a>
        </li>
      </ul>
      <br></br>
      <button id="btId">CLICKKKK</button>
    </div>
  );
}

export default VerticleNavbar;
