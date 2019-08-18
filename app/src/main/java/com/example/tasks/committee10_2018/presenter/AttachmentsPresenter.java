package com.example.tasks.committee10_2018.presenter;

import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.repositories.AttachmentsRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.view.AttachmentsInterface;

public class AttachmentsPresenter {
    AttachmentsRepository attachmentsRepository;
    AttachmentsInterface attachmentsInterface;

    public AttachmentsPresenter(AttachmentsRepository attachmentsRepository, AttachmentsInterface attachmentsInterface){
        this.attachmentsRepository = attachmentsRepository;
        this.attachmentsInterface = attachmentsInterface;
    }

    public void getAttachments(String caseNo){
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
                if (fileInfos != null){
                    attachmentsInterface.result(fileInfos);
                }else {
                    attachmentsInterface.result(null);
                }
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
        attachmentsRepository.getAttachments(listener, caseNo);
    }
}
