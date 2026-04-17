package com.topic.model.dto;

import java.time.LocalDateTime;

public class TopicDTO {
    private int id;
    private String name;
    private LocalDateTime created_at;
    private int user_id;
    private String user_name;
    private String user_email;
    private String user_role;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_role() {
        return user_role;
    }

    public TopicDTO(int id, String name, LocalDateTime created_at, int user_id, String user_name, String user_email, String user_role) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_role = user_role;
    }
}
