// Demo on using Callback for Asynchronous Execution
// Changing background color every 3 seconds and displaying clock time

const appDiv = document.getElementById('app');
appDiv.innerHTML = '<h1>Good Morning</h1>';

//function calls setColor callback at interval specified
const changeColor = (setColor, interval)=>{
  setInterval(() => {    
    setColor(appDiv);
  }, interval);
}
// rgb(r,b,g);
//function that changes the background of the section passed with random dark colors
const setBgColor = section => {
  let color = `rgb(${Math.random() * 128 + 1},${Math.random() * 128 +
    1},${Math.random() * 128 + 1})`;
  section.style.backgroundColor = color;
};

//function that displays greetings
const greet = (name) => {
  appDiv.innerHTML += '<h2>Hello ' + name + '<h2>';
}

//function that displays clock time every second
const displayClock = () => {
  setInterval(() => {
    const clockDiv = document.getElementById('clock');
    clockDiv.innerHTML =
      "<h3>It's " +
      new Date().getHours() +
      ' hours ' +
      new Date().getMinutes() +
      ' mins and ' +
      new Date().getSeconds() +
      ' seconds</h3>';
  }, 1000);
}


//function that displays usage stats
const displayStats = () => {
  appDiv.innerHTML +=
    '<h3>Your site usage statistics will be displayed below this block...currently page under construction</h3>';
}

//calling greet() with username
greet('smith.rogers');
//calling changeColor() with setBgColor() as callback parameter and 3000 ms as interval value
changeColor(setBgColor, 3000);
//calling displayClock to display current time
displayClock();
//calling displayStats() to display usage statistics
displayStats();
