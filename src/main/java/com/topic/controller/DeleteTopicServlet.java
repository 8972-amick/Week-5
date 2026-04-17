package com.topic.controller;

import com.topic.model.dao.TopicDAO;
import com.user.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteTopic")
public class DeleteTopicServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        int topic_id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // object form m aauca data tei vara User type ma cast gereko

        if (user == null) {
            response.sendRedirect("views/login.jsp");
        }

        TopicDAO topicDAO = new TopicDAO();
        boolean result = topicDAO.deleteTopic(topic_id, user.getId());

        if (result) {
            session.setAttribute("success", "Topic Deleted Successfully");
        } else {
            session.setAttribute("error", "Topic Deletion Failed");
        }
        response.sendRedirect("ViewTopics");
    }
}
