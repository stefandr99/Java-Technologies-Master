<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Laboratory 2</title>
</head>
<body>
<h1><%= "Laboratory 2" %>
</h1>
<br/>

<form action="RecordController" method="post">
    <label for="category">Category</label>

    <select name="category" id="category">
        <%
            List<String> a= (List<String>)request.getAttribute("categories");
            for (String s : a) {
        %>
        <option value="<%=s%>"><%=s%></option>
        <%
            }
        %>
    </select>
    <br/>
    <br/>

    <label for="key">Key:</label>
    <input id="key" name="key" type="text">
    <br/>
    <br/>

    <label for="value">Value:</label>
    <input id="value" name="value" type="text">
    <br/>
    <br/>

    <button type="submit" name="submit-btn" value="Submit">Submit</button>


</form>
</body>
</html>