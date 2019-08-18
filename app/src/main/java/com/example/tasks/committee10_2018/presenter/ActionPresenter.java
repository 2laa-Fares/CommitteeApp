package com.example.tasks.committee10_2018.presenter;

import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.repositories.ActionRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.view.ActionInterface;

public class ActionPresenter {
    ActionRepository repository;
    ActionInterface Interface;

    public ActionPresenter(ActionRepository repository, ActionInterface Interface){
    this.repository = repository;
    this.Interface = Interface;
    }

    public void getActions(String caseNo){
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

            }

            @Override
            public void onRequestComplete(syscod[] actions) {

            }

            @Override
            public void onRequestCompleted(String result) {

            }

            @Override
            public void onRequestComplete(ActionClass[] actions) {
                if (actions!= null){
                    Interface.actions(actions);
                }else {
                    Interface.actions(null);
                }
            }
        };
        repository.action(listener, caseNo);
    }
}
