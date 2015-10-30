<%-- 
    Document   : index
    Created on : 30/10/2015, 10:09:29
    Author     : 31240550
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
    <c:if test="${characters == null}">
        <c:redirect url="Controller?command=loadXML"></c:redirect>
    </c:if>
    
        <section>
            <c:forEach items="${characters}" var="mc">
                <article>
                    <h1>${mc.getId()} - ${mc.getName()}</h1>
                    <p>${mc.getDescription()}</p>
                    <p><small>${mc.getThumbnail()}</small></p>
                </article>
            </c:forEach>
        </section>
    </body>
</html>
