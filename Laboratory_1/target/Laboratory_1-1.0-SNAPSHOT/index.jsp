<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Laboratory 1</title>
</head>
<body>
<h1><%= "Laboratory 1" %>
</h1>
<br/>

<form action="DesktopServlet" method="post">
    <label for="key">Key:</label>
    <input id="key" name="key" type="text">
    <br/>
    <br/>

    <label for="value">Value:</label>
    <input id="value" name="value" type="number">
    <br/>
    <br/>

    <label for="mock">Mock:</label>
    <input id="mock" name="mock" type="checkbox">
    <br/>
    <br/>

    <label for="sync">Sync:</label>
    <input id="sync" name="sync" type="checkbox">
    <br/>
    <br/>

    <button type="submit" name="submit-btn" value="Submit">Submit</button>
</form>

</body>
</html>