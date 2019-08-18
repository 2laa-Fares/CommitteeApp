package com.example.tasks.committee10_2018.repositories;

public interface AddActionRepository {
    void taken(OnRequestCompletedListener listener);
    void add(OnRequestCompletedListener listener, String caseNo, String taken, String note);
}
