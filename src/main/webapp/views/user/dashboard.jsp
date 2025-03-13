<%@ page import="com.sportflow.model.User, java.util.*" %>
<%
    User user = (User) session.getAttribute("user");
%>
    <jsp:include page="/views/parcials/sidebar.jsp" />
    <div class="flex flex-col flex-1 w-full">
        <jsp:include page="/views/parcials/navbar.jsp" />

        <main class="h-full overflow-y-auto">
            <div class="container px-6 mx-auto grid">
                <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
                  Dashboard <%= user.getFirstName() %>
                </h2>
            </div>
        </main>

    </div>

<%-- <h1> Welcome back <%= user.getFirstName() %> </h1> --%>



