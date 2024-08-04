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
    return axios.post(uri+"createUser", details);
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