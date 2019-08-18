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
import com.example.tasks.committee10_2018.repositories.ReferralRepository;
import com.example.tasks.committee10_2018.view.AddReferralInterface;
import com.example.tasks.committee10_2018.view.ReferralInterface;

public class ReferralPresenter {
    ReferralRepository repository;
    ReferralInterface Interface;

    public ReferralPresenter(ReferralRepository repository, ReferralInterface Interface){
    this.repository = repository;
    this.Interface = Interface;
    }

    public void getReferrals(String caseNo){
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

            }

            @Override
            public void onRequestCompleted(syscod[] statue) {

            }

            @Override
            public void onRequestComplete(FileInfo[] fileInfos) {

            }

            @Override
            public void onRequestComplete(Referral[] referrals) {
                if ( referrals!= null){
                    Interface.referrals(referrals);
                }else {
                    Interface.referrals(null);
                }
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
        repository.referral(listener, caseNo);
    }
}
