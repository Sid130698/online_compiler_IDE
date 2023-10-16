<!DOCTYPE html>
<html>
<head>
<script>
	var contextPath = "${pageContext.request.contextPath}";
    var module = "/index";</script>
    <title>Hello World</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/myScript.js"></script>
</head>
<body>
    <p id="output"></p>
    <h2>Hello is it coming</h2>
    <p id="output">This is a sample text with styles applied from CSS.</p>
    
    <input type="number" id="num1" placeholder="Enter the first number">
    <input type="number" id="num2" placeholder="Enter the second number">
    
    <button id="calculateButton" onclick="addNumber()">Add Numbers</button>
    <p>Result: <span id="result">-</span></p>
</body>
</html>
