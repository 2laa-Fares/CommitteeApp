package com.example.tasks.committee10_2018.presenter;

import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.repositories.AddReferralRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.view.AddReferral;
import com.example.tasks.committee10_2018.view.AddReferralInterface;

public class AddReferralPresenter {
    AddReferralRepository repository;
    AddReferralInterface Interface;
    String category;

    public AddReferralPresenter(AddReferralRepository repository, AddReferralInterface Interface , String category){
    this.repository = repository;
    this.Interface = Interface;
    this.category = category;
    }

    public void getResults(){
        OnRequestCompletedListener listener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(stp_users user) {

            }

            @Override
            public void onRequestCompleted(stpv_case[] cases) {

            }

            @Override
            public void onRequestComplete(stpv_results[] results) {
                if ( results!= null){
                    Interface.result(results);
                }else {
                    Interface.result(null);
                }
            }

            @Override
            public void onRequestComplete(stp_users[] stp_users) {

            }

            @Override
            public void onRequestCompleted(stpv_case stpv_case) {

            }

            @Override
            public void onRequestCompleted(syscod[] statue) {

            }

            @Override
            public void onRequestComplete(FileInfo[] fileInfos) {

            }

            @Override
            public void onRequestComplete(Referral[] referrals) {

            }

            @Override
            public void onRequestComplete(syscod[] actions) {

            }

            @Override
            public void onRequestCompleted(String result) {

            }

            @Override
            public void onRequestComplete(ActionClass[] actions) {

            }
        };
        repository.result(listener, category);
    }

    public void getToUsers(){
        OnRequestCompletedListener listener = new OnRequestCompletedListener() {

            @Override
            public void onRequestCompleted(stp_users user) {

            }

            @Override
            public void onRequestCompleted(stpv_case[] cases) {

            }

            @Override
            public void onRequestComplete(stpv_results[] results) {

            }

            @Override
            public void onRequestComplete(stp_users[] stp_users) {
                if (stp_users != null){
                    Interface.to(stp_users);
                }else {
                    Interface.to(null);
                }
            }

            @Override
            public void onRequestCompleted(stpv_case stpv_case) {

            }

            @Override
            public void onRequestCompleted(syscod[] statue) {

            }

            @Override
            public void onRequestComplete(FileInfo[] fileInfos) {

            }

            @Override
            public void onRequestComplete(Referral[] referrals) {

            }

            @Override
            public void onRequestComplete(syscod[] actions) {

            }

            @Override
            public void onRequestCompleted(String result) {

            }

            @Override
            public void onRequestComplete(ActionClass[] actions) {

            }
        };
        repository.to(listener, category);
    }

    public void addReferral(String caseNo, String judgeNo, String caseType, String result, String notes){
        OnRequestCompletedListener listener = new OnRequestCompletedListener() {

            @Override
            public void onRequestCompleted(stp_users user) {

            }

            @Override
            public void onRequestCompleted(stpv_case[] cases) {

            }

            @Override
            public void onRequestComplete(stpv_results[] results) {

            }

            @Override
            public void onRequestComplete(stp_users[] stp_users) {
            }

            @Override
            public void onRequestCompleted(stpv_case stpv_case) {
                if(stpv_case != null) {
                    Interface.add(stpv_case);
                }
            }

            @Override
            public void onRequestCompleted(syscod[] statue) {

            }

            @Override
            public void onRequestComplete(FileInfo[] fileInfos) {

            }

            @Override
            public void onRequestComplete(Referral[] referrals) {

            }

            @Override
            public void onRequestComplete(syscod[] actions) {

            }

            @Override
            public void onRequestCompleted(String result) {

            }

            @Override
            public void onRequestComplete(ActionClass[] actions) {

            }
        };
        repository.add(listener, caseNo, judgeNo, caseType, result, notes);
    }
}
