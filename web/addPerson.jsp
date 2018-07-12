<%-- 
    Document   : addPerson
    Created on : 03/07/2018, 21:38:00
    Author     : marcoreina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Person</title>
    </head>
    <body>
        <h1>Add Person</h1>
        
        <div>
            <form action="ServletController" method="post">
                <input type="hidden" name="action" value="add"/>
                
                <label for="name">Name:</label>
                <input type="text" name="name" style="display: block"/>
                
                <label for="fatherName">Father Name:</label>
                <input type="text" name="fatherName"  style="display: block"/>

                <label for="email">Email:</label>
                <input type="text" name="email"  style="display: block"/>
                
                <label for="phone">Phone:</label>
                <input type="text" name="phone"  style="display: block"/>

                <input type="submit" value="Send"/>
            </form>
        </div>
        <br/>
        <a href="index.jsp">Return Home</a>
    </body>
</html>
