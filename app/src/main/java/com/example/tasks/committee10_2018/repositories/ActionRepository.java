package com.example.tasks.committee10_2018.repositories;

public interface ActionRepository {
    void action(OnRequestCompletedListener listener, String case_no);
}
