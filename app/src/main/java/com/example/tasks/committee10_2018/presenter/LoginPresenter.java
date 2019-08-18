package com.example.tasks.committee10_2018.presenter;

import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.repositories.LoginRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.view.LoginInterface;

public class LoginPresenter {
    LoginInterface loginInterface;
    LoginRepository loginRepository;

    public LoginPresenter(LoginInterface loginInterface, LoginRepository loginRepository){
        this.loginInterface = loginInterface;
        this.loginRepository = loginRepository;
    }

    public void loginUser(String userName, String pass){
        OnRequestCompletedListener listener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(stp_users user) {
                if(user == null){
                    loginInterface.NotUserFound("من فضلك ادخل اسم المستخدم الخاص بك والبسورد بشكل صحيح.");
                }else {
                    loginInterface.RetrieveUser(user);
                }
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

            }
        };
        loginRepository.login(userName, pass, listener);

    }
}
