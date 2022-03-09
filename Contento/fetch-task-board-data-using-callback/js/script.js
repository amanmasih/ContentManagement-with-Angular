// Demo on using Callback for making multiple API calls
// Demo highlights the Callback hell concerns
// Fetching Boards' data for TaskBoard Renderer asynchronously using Callbacks


// Setup required for demo execution
// 1. json-server should be running to fetch data from db.json file
// 2. command to run json-server : `json-server -w db.json`
// 3. this is the only file to be modified for building solution

import { transform } from './transformer.js';
//import transform from './transformer.js';

import boardRenderer from './board-renderer.js';

let taskData = {}; //this variable will hold complete boards' data

//function takes url to make request, 
// success callback to execute on successful response and 
// failure callback to execute of failed response

const fetchData = (url, success, failure) => {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', url);
  xhr.send();
  xhr.onreadystatechange = () => {
      if (xhr.readyState === 4)
          if (xhr.status === 200) {
            //calling success callback with response data
            success(JSON.parse(xhr.response));
          } else {
              //calling failure callback with error message
              failure(
                  `${xhr.status} :: error occurred while fetching data from url ${url}`
              );
          }
  };
};


fetchData(
    'http://localhost:3000/boards',
    response => {
        taskData.boards = response;
        fetchData(
            'http://localhost:3000/lists',
            response => {
                taskData.lists = response;
                fetchData(
                    'http://localhost:3000/cards',
                    response => {
                        taskData.cards = response;
                        fetchData(
                            'http://localhost:3000/comments',
                            response => {
                                taskData.comments = response;
                                boardRenderer(transform(taskData)[0]); // re-arrange
                            },
                            error => {
                                console.log(error);
                            }
                        );
                    },
                    error => {
                        console.log(error);
                    }
                );
            },
            error => {
                console.log(error);
            }
        );
        return "hi";
    },
    error => {
        console.log(error);
    }
);


