<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
        }
        h1 {
            color: #ff6347;
        }
        p {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error Page</h1>
        <p>We're sorry, but an error has occurred.</p>
        
        <%-- Displaying the error message --%>
        <% if (request.getAttribute("javax.servlet.error.message") != null) { %>
            <p>Error Message: <%= request.getAttribute("javax.servlet.error.message") %></p>
        <% } %>
        
        <%-- Displaying the exception details if available --%>
        <% if (request.getAttribute("javax.servlet.error.exception") != null) { %>
            <p>Exception Type: <%= request.getAttribute("javax.servlet.error.exception_type") %></p>
            <p>Exception Message: <%= request.getAttribute("javax.servlet.error.message") %></p>
            <p>Request URI: <%= request.getAttribute("javax.servlet.error.request_uri") %></p>
            <p>Exception Stacktrace:</p>
            <pre>
                <%= exceptionToString((Throwable)request.getAttribute("javax.servlet.error.exception")) %>
            </pre>
        <% } %>
    </div>

    <%-- Helper method to convert exception stack trace to string --%>
    <%!
        public String exceptionToString(Throwable e) {
            java.io.StringWriter sw = new java.io.StringWriter();
            java.io.PrintWriter pw = new java.io.PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        }
    %>
</body>
</html>
