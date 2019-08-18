package com.example.tasks.committee10_2018.view;

import com.example.tasks.committee10_2018.model.stp_users;

public interface LoginInterface {
    void RetrieveUser(stp_users user);
    void NotUserFound(String error);
}
