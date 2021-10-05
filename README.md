# Java-Technologies-Master
Laboratories for Java Technologies class within the Master

# Laboratory 1
- Created the HelloServlet which receives those 4 params, read params from request;
- Check if it's a mock and responds with a confirmation message if true;
- If it's not a mock, check if it's sync:
  - sync = true -> calls sync function which writes in the file in a synchronized way
  - sync = false -> calls async function which writes in the file in an asynchronized way - I create an AsynchronousFileChannel which will write in file in an asynchronized way
- After those actions, I read from the file and respond with the html text;
- At the same time, I write in the log the necessary information using the HttpServletRequest parameter;
- For the desktop app I created another Servlet which behaves the same as previous, but instead, it responds with a text.
