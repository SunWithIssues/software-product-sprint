// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ["In the whole world no poor devil is lynched, "
        + "no wretch is tortured, in whom I too am not "
        + "degraded and murdered. -Aime Cesaire", 
      "Sometimes we are blessed with being able to choose "
        + "the time, and the arena, and the manner of our "
        + "revolution, but more usually we must do battle "
        + "where we are standing. -Audre Lorde", 
      "He who is reluctant to recognize me opposes me. "
        + "-Frantz Fanon"];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * Using fetch by using the async and await keywords. This
 * allows you to use the return values directly instead of going through
 * Promises. 
 */
async function getMessageFromDOM() {
  const response = await fetch('/_ah/admin').then(response => response.json()).then(
      (stats) => {
          document.getElementById('message-container').innerText = stats;
          
      });
}

/** Fetches tasks from the server and adds them to the DOM. */
function loadTasks() {
  fetch('/data').then(response => response.json()).then((messages) => {
    const messagesListEl = document.getElementById('task-list');
    messages.forEach((message) => {
      const messageEl = document.createElement('li');
      messageEl.className = "Message";
      messageEl.innerHTML ='';
      messageEl.appendChild(createListElement(message.comment));
      messageEl.appendChild(createListElement(message.timestamp));
      messageEl.appendChild(document.createElement('hr'));
      messagesListEl.appendChild(messageEl);
    })
  });
}


/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

