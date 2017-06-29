<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Test</title>
</head>
<body>
	<p>Hello World !!!!</p>
	<p>
	<% 
	String attribut = (String) request.getAttribute("index");

    out.println( attribut );


    String parameter = request.getParameter( "author" );

    out.println( parameter );
	%>
	</p>
	
	<c:out value="test" />
</body>
</html>
