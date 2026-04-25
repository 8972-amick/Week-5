package com.topic.controller;

import com.topic.model.Topic;
import com.topic.model.dao.TopicDAO;
import com.user.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
@WebServlet("/addTopic")
@MultipartConfig
public class AddTopicServlet  extends HttpServlet { // POJO class= plain old java object
    protected void services(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException

    {
           String name = request.getParameter("name");
           HttpSession session = request.getSession();
           User user = (User) session.getAttribute("user"); // type casting explicit session ko user object lae model ko user ma change gareko

        Part filePart = request.getPart("topic_image");
        if(filePart.getSize() > 3*1024*1024){
            session.setAttribute("error", "Cant add image greater than 3 MB");
            response.sendRedirect("views/dashboard.jsp");
            return;
        }
        String fileName = new File(filePart.getSubmittedFileName()).getName();
        String uploadPath = "C:\\Users\\amick Khada\\Documents\\WEEK_5\\src\\main\\webapp";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        filePart.write(uploadPath + File.separator + fileName);

           Topic topic = new Topic(name, user.getId(),fileName);
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

