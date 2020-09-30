<%--
  Created by IntelliJ IDEA.
  User: abz
  Date: 14.09.20
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="servlets.Math" %>

<html>
<head>
    <title>File Upload</title>
</head>
<body>
Select file: <br/>

<form
        action="${pageContext.request.contextPath}/action_file_upload"
<%--        action="${pageContext.request.contextPath}/action_file_upload-apache"--%>
        method="post"
        enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>
${requestScope.message}
<%--${Math['pi']}--%>
${Math.getPi()}

<%

        %>
</body>
</html>
