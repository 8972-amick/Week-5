package com.topic.model.dao;

import com.topic.model.Topic;
import com.topic.model.dto.TopicDTO;

import java.util.ArrayList;

public interface TopicInterface {
    boolean insertTopic(Topic topic);

    ArrayList<TopicDTO> viewAllTopics();
    boolean updated_at(int topicId, String topicName, int userId);

    boolean deleteTopic(int topic_id, int userID);

}
