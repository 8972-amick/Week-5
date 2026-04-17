package com.topic.controller;

import com.topic.model.dao.TopicDAO;
import com.topic.model.dto.TopicDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// server ma run class after extending
@WebServlet("/viewTopics")
public class ViewTopicServlet extends HttpServlet {
 protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
        {
            TopicDAO topicDAO = new TopicDAO();
            ArrayList<TopicDTO> topics = topicDAO.viewAllTopics();
            request.setAttribute("topics", topics);
            request.getRequestDispatcher("Views/viewTopics.jsp").forward(request, response);
        }
    }


