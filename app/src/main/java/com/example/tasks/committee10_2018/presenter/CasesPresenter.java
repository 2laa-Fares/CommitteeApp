package com.example.tasks.committee10_2018.presenter;

import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.repositories.CasesRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.view.CaseInterface;

public class CasesPresenter {
    private CasesRepository casesRepository;
    private CaseInterface casesInterface;

    public CasesPresenter(CasesRepository casesRepository, CaseInterface casesInterface){
        this.casesInterface = casesInterface;
        this.casesRepository = casesRepository;
    }

    public void casesRequest(){
        OnRequestCompletedListener completedListener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(stp_users user) {}
            @Override
            public void onRequestCompleted(stpv_case[] cases) {
                if(cases != null) {
                    casesInterface.getCases(cases);
                }else {
                    casesInterface.noCases("لا يوجد قضايا متاحه الان...");
                }
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

            }
        };
        casesRepository.cases(completedListener);
    }

    public void caseRequest(String caseNo){
        OnRequestCompletedListener completedListener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(stp_users user) {}
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
                    casesInterface.getCase(stpv_case);
                }else {
                    casesInterface.noCase("لا يوجد قضية متاحه الان بهذا الرقم...");
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
        casesRepository.getCase(completedListener , caseNo);
    }

    public void getCasesRequest(String finished, String result){
        OnRequestCompletedListener completedListener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(stp_users user) {}
            @Override
            public void onRequestCompleted(stpv_case[] cases) {
                if(cases != null) {
                    casesInterface.getCases(cases);
                }else {
                    casesInterface.noCases("لا يوجد قضايا متاحه الان...");
                }
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

            }
        };
        casesRepository.getCases(completedListener, finished, result);
    }

    public void StatusRequest(){
        OnRequestCompletedListener completedListener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(stp_users user) {}
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
                if(statue != null) {
                    casesInterface.status(statue);
                }
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
        casesRepository.getStatus(completedListener);
    }
}
