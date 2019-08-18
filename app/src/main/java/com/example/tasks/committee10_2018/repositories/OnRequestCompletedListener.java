package com.example.tasks.committee10_2018.repositories;


import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;

public interface OnRequestCompletedListener {
    void onRequestCompleted(stp_users user);
    void onRequestCompleted(stpv_case[] cases);
    void onRequestComplete(stpv_results[] results);
    void onRequestComplete(stp_users[] stp_users);
    void onRequestCompleted(stpv_case stpv_case);
    void onRequestCompleted(syscod[] statue);
    void onRequestComplete(FileInfo[] fileInfos);
    void onRequestComplete(Referral[] referrals);
    void onRequestComplete(syscod[] actions);
    void onRequestCompleted(String result);
    void onRequestComplete(ActionClass[] actions);
}
