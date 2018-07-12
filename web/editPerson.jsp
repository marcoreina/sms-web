<%-- 
    Document   : editPerson
    Created on : 03/07/2018, 21:38:00
    Author     : marcoreina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Person</title>
    </head>
    <body>
        <h1>Modify Person</h1>
        
        <div>
            <form action="ServletController" method="post">
                <input type="hidden" name="action" value="modify"/>
                <input type="hidden" name="idPerson" value="${person.idPerson}"/>
                
                <label for="name">Name:</label>
                <input type="text" name="name" value="${person.name}" style="display: block"/>

                <label for="fatherName">Father Name:</label>
                <input type="text" name="fatherName" value="${person.fatherName}" style="display: block"/>

                <label for="email">Email:</label>
                <input type="text" name="email" value="${person.email}" style="display: block"/>
                
                <label for="phone">Phone:</label>
                <input type="text" name="phone" value="${person.phone}" style="display: block"/>

                <input type="submit" name="save" value="Save"/>
                <input type="submit" name="delete" value="Delete"/>
            </form>
        </div>
        <br/>
        <a href="index.jsp">Return Home</a>
    </body>
</html>
