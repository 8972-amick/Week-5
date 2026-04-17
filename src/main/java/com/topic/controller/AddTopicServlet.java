package com.topic.controller;

import com.topic.model.Topic;
import com.topic.model.dao.TopicDAO;
import com.user.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/addTopic")
public class AddTopicServlet  extends HttpServlet { // POJO class= plain old java object
    protected void services(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException

    {
           String name = request.getParameter("name");
           HttpSession session = request.getSession();
           User user = (User) session.getAttribute("user"); // type casting explicit session ko user object lae model ko user ma change gareko
           Topic topic = new Topic(name, user.getId());
           TopicDAO topicDAO = new TopicDAO();
           boolean result = topicDAO.insertTopic(topic);

           if (result) {
               session.setAttribute("success", "Topic added Successfully");
           }else {
               session.setAttribute("error", "Topic not added");
               }
           response.sendRedirect("views/dashboard.jsp");
        }
    }

