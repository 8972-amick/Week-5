<%@ page import="com.user.model.User" %>
<%
    User u = (com.user.model.User) session.getAttribute("user");

    if (u == null) {
        response.sendRedirect("../views/login.jsp");
    }
%>

<%
    String email = "";

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("userEmail".equals(c.getName())) {
                email = c.getValue();
            }
        }
    }
%>

<h1>Welcome Email: <%= email %></h1><br>

<h1>Welcome User: <%= u.getName() %></h1><br>
<form action="../user-auth" method="post">
    <input type="hidden" name="action" value="logout"/>
    <button>Logout</button>
</form>
<% if(session.getAttribute("success") != null) { %>
    <p style = "color:green;"><%= session.getAttribute("error")%></p>
<% } %>

<form action = "../addTopic" method="post">
    <input type = "text" name = "name" placeholder="New Topic" required/>
    <button>Add</button>
</form><br><br>

<form action="../addTopic" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="New Topic" required/>
    <input type="file" name="topic_image" required>
    <button>Add</button>
</form><br>

<a href="../ViewTopics">View topics</a>

<jsp:include page="footer.jsp" />