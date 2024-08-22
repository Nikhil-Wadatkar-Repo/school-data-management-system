import axios from "axios";
const uri = "http://localhost:9092/users/"
export const callGetUsersAPI = () => {
    return axios.get(uri + "getAllUsers");
    // .then(response => {
    //   setData(response.data);
    // })
    // .catch(error => {
    //   console.error('There was an error fetching the data!', error);
    // });

}
export const callCreateUserAPI = (details) => {
    return axios.post(uri + "createUser", details);
    // .then(response => {
    //   console.log('Post successful:', response.data);
    //   setNewData('');
    // })
    // .catch(error => {
    //   console.error('There was an error posting the data!', error);
    // });
};
export const callUpdateUserAPI = () => {
    return axios.put('https://jsonplaceholder.typicode.com/posts/1', {

    });
    // .then(response => {
    //   console.log('Put successful:', response.data);
    //   setUpdateData('');
    // })
    // .catch(error => {
    //   console.error('There was an error updating the data!', error);
    // });
};
export const callAllClasses = (req) => {
    return axios.post("http://localhost:9092/uni/getFilteredClass",req);
}
export const callAllSections = () => {
    return axios.get("http://localhost:9092/uni/getDistinctSections");
}
export const callAllTeachers = () => {
    return axios.get("http://localhost:9092/teachers/getAll");
}
export const saveClassAPI = (reqDetails) => {
        return axios.post("http://localhost:9092/classDetails/createClass", reqDetails);
}

export const getStudentByIdAPI = (studentID) => {
    return axios.get("http://localhost:9092/uni/getStudentById/"+studentID);
}

export const getDistinctYearsAPI = () => {
    return axios.get("http://localhost:9092/uni/getDistinctYears");
}
export const getSectionYearByStandardAPI = (std) => {
    return axios.get("http://localhost:9092/uni/getSectionYearByStandard/"+std);
}


export const getDistinctStandardAPI = () => {
    return axios.get("http://localhost:9092/uni/getDistinctStandards");
}

export const callSectionAPI = (req) => {
    return axios.post("http://localhost:9092/uni/getFilteredStudent",req);
}
export const callStudDetailsByUNID=(unid)=>{
    return axios.get("http://localhost:9092/uni/getByUnid/"+unid);
} 