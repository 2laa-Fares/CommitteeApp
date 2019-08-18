package com.example.tasks.committee10_2018.repositories;


public interface LoginRepository {

    void login(String userName, String pass, OnRequestCompletedListener listener);
}
