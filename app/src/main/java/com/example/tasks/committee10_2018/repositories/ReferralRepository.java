package com.example.tasks.committee10_2018.repositories;

public interface ReferralRepository {
    void referral(OnRequestCompletedListener listener, String case_no);
}
