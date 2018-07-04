<%-- 
    Document   : listPersons
    Created on : 03/07/2018, 21:38:00
    Author     : marcoreina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Persons</title>
    </head>
    <body>
        <h1>List Persons</h1>
        
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Father Name</td>
                <td>Email</td>
            </tr>
            
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td>${person.name}</td>
                    <td>${person.fatherName}</td>
                    <td>${person.email}</td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="index.jsp">Return Home</a>
    </body>
</html>
