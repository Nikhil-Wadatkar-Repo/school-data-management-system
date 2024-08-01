import logo from './logo.svg';
import './App.css';
import { Routes, Route, useNavigate } from "react-router-dom";
import CreateUser from './components/CreateUser';
import Dashboard from './components/Dashboard';
import { useState } from 'react';
import { routes } from './Constants';
import TeachersList from './components/TeachersList';

function App() {
  const [itemList, setItems] = useState(routes);
  const [createUserPage, setCreateUserPage] = useState(false);
  const nav=useNavigate();
  const showForm = (val) => {
    console.log("Val::", val.item);
    if ("Class" === val.item) {
      setCreateUserPage(true);
    }
    else
      setCreateUserPage(false);
  }
  return (
    <div className='container-fluid'>
      <h1>Dashboard</h1>
      <div className='container-fluid'>
        <div className='row'>
          <div className='col-2' >
            <div>
              <ul className="list-group">
                {
                  routes.map((item, index) => (
                    
                    <li key={index} className="list-group-item btn" onClick={(e) => {
                      let path=item.path;
                      return nav('/',`{item.path}`)}}>{item.label} {item.path}</li>
                  ))
                }
                <li className="list-group-item" onClick={e=>nav("/create")}>CCC </li>
                <li className="list-group-item" onClick={e=>nav("/dash")}>Dashboard</li>
                <li className="list-group-item"onClick={e=>nav("/teacherList")}>Teachers</li>
                <li className="list-group-item">A fourth item</li>
                <li className="list-group-item">And a fifth one</li>
              </ul>
            </div>

          </div>
          <div className='col-8' ><p>main section</p>

              <Routes>
                <Route path="/create" element={<CreateUser />}/>
                <Route path="/teacherList" element={ <TeachersList></TeachersList>}/>
                  {/* <Route index element={<Home />} />
                  <Route path="blogs" element={<Blogs />} />
                  <Route path="contact" element={<Contact />} />
                  <Route path="*" element={<NoPage />} /> */}
                <Route path="/dash" element={<Dashboard />}/>
              </Routes>
          
              {/* npm install jquery --save */}
              {/* npm i --save-dev @types/jquery */}

          </div>
          <div className='col-2'>Notifications</div>
        </div>
      </div>
      <div className="footer fixed-bottom">
        2 days ago
      </div>
    </div>
  )
}

export default App;
