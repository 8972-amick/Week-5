import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import learninglog.topic.model.dao.TopicDAO;
import learninglog.topic.model.dto.TopicDTO;

import java.io.IOException;

public class ViewTopicByIdServlet extends HttpServlet {

protected void service(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
TopicDAO topicDAO = new TopicDAO();
int topic_id = Integer.parseInt(request.getParameter("id"));

TopicDTO topic = topicDAO.viewTopicById(topic_id);
request.setAttribute("topic", topic);
request.getRequestDispatcher("views/viewTopicById.jsp").forward(request,response);
}
}
<%@ page import="com.topic.model.dto.TopicDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    TopicDTO topic = (TopicDTO) request.getAttribute("topic");
%>

<html>
<head>
    <title>View Topic</title>
</head>
<body>

<h2>Topic Details</h2>

<% if (topic != null) { %>
<p><strong>ID:</strong> <%= topic.getId() %></p>
<p><strong>Name:</strong> <%= topic.getName() %></p>
<p><strong>User Name:</strong> <%= topic.getUser_name() %></p>
<p><strong>Email:</strong> <%= topic.getUser_email() %></p>
<p><strong>Role:</strong> <%= topic.getUser_role() %></p>
<p><strong>Created At:</strong> <%= topic.getCreated_at() %></p>
<% } else { %>
<p>Topic not found.</p>
<% } %>

</body>
</html>