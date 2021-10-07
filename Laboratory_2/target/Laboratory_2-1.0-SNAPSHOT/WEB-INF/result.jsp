<%@ page import="java.util.List" %>
<%@ page import="com.fii.laboratory_2.models.Record" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>

  <table>
      <tr>
          <th>Category</th>
          <th>Key</th>
          <th>Value</th>
      </tr>
      <%
          List<Record> a= (List<Record>)request.getAttribute("records");
          for (Record record : a) {
      %>
      <tr>
          <td><%=record.getCategory()%></td>
          <td><%=record.getKey()%></td>
          <td><%=record.getValue()%></td>
      </tr>
      <%
          }
      %>
  </table>

  <%
      request.setAttribute("action", "categories");
  %>
  <a href="/Laboratory_2-1.0-SNAPSHOT">Back home</a>
</body>
</html>
