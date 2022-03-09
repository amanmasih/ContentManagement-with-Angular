// Demo on using Proise for making multiple API calls
// Demo highlights overcoming Callback hell concerns by Chaining promises
// Fetching Boards' data for TaskBoard Renderer asynchronously using Promises


// Setup required for demo execution
// 1. json-server should be running to fetch data from db.json file
// 2. command to run json-server : `json-server -w db.json`
// 3. this is the only file to be modified for building solution

import { transform } from './transformer.js';
import boardRenderer from './board-renderer.js';


let taskData = {}; //this variable will hold complete boards' data


// function makes api call using axios to url provided

const makeAPICall = url => {    // 
    return axios(url);
};

makeAPICall('http://localhost:3000/boards') // promise object (ok with resp, error)
    .then(response => {
        taskData.boards = response.data;
        return makeAPICall('http://localhost:3000/lists'); // promise object
    })
    .then(response => {
        taskData.lists = response.data;
        return makeAPICall('http://localhost:3000/cards'); // promise object
    })
    .then(response => {
        taskData.cards = response.data;
        return makeAPICall('http://localhost:3000/comments');
    })
    .then(response => {
        taskData.comments = response.data;
        boardRenderer(transform(taskData)[0]);
    })
    .catch(err => {
        console.log(err);
    });



