<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Online Compiler and IDE</title>
    <script>
	var contextPath = "${pageContext.request.contextPath}";
    var module = "/ide";</script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.1/codemirror.css"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.1/codemirror.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.1/mode/javascript/javascript.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.1/mode/python/python.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.58.3/mode/clike/clike.js"></script>
    <script src="${pageContext.request.contextPath}/main.js"></script>
  </head>
  <body>
    <header>
      <h1>Online Compiler and IDE</h1>
    </header>
    <main>
      <div id="langDropDown">
        <label for="languageSelector">Select a programming language:</label>
        <select id="languageSelector">
          <option value="java">Java</option>
          <option value="python">Python</option>
          <!-- Add more language options as needed -->
        </select>
      </div>
      <div id="codeEditor">
        <!-- Your CodeMirror code editor will go here -->
      </div>
      <button id="compile-button">Compile/Execute</button>

      <!-- Output Area -->
      <div id="output-area">
        <!-- Output and error messages will appear here -->
      </div>
    </main>

    <footer></footer>
  </body>
</html>
