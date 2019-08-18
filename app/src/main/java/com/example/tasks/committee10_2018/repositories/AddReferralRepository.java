package com.example.tasks.committee10_2018.repositories;

import retrofit2.http.Query;

public interface AddReferralRepository {
    void result(OnRequestCompletedListener listener , String cat );
    void to(OnRequestCompletedListener listener , String cat );
    void add(OnRequestCompletedListener listener , String caseNo, String judgeNo, String caseType, String result, String notes );
}
