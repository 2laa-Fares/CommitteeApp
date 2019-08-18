package com.example.tasks.committee10_2018.repositories;


public interface CasesRepository {
    void cases(OnRequestCompletedListener listener);
    void getCase(OnRequestCompletedListener listener , String caseNo);
    void getCases(OnRequestCompletedListener listener, String finished, String result);
    void getStatus(OnRequestCompletedListener listener);
}
