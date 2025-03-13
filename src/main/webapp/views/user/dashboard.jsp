<%@ page import="com.sportflow.model.User, java.util.*" %>

<%
    User user = (User) session.getAttribute("user");
%>

<h1> Welcome back <%= user.getFirstName() %> </h1>



