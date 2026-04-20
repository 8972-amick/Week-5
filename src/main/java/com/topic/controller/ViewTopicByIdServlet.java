package com.topic.controller;

import com.topic.model.dao.TopicDAO;
import com.topic.model.dto.TopicDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ViewTopicById")
public class ViewTopicByIdServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        TopicDAO topicDAO = new TopicDAO();
        int topic_id = Integer.parseInt(request.getParameter("id")); // typecast

        TopicDTO topic = topicDAO.viewTopicById(topic_id);
        request.setAttribute("topic", topic);
        request.getRequestDispatcher("views/ViewTopicById.jsp").forward(request, response); // dispatcher fast hunxa, server side and data transfer hunxa bbut send redirect slow hunxa, client side and data transfer gardaena
    }
}
