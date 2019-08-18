package com.example.tasks.committee10_2018.utils;

import android.content.Context;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.CommonResponse;
import com.example.tasks.committee10_2018.model.Error;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MobilyAPI {

    Context context = App.getContext();
    private String userName = "";
    private String password = "";
    public static final String HOST = "http://mobily.ws/api/json/index.php";

    public MobilyAPI(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }

    public void sendMessage(String sender, String msg, String numbers,
                            String dateSend, String timeSend, String deleteKey, String alias,
                            final OnDataReceiveListner listener) {

        RequestParams AuthenticationData = new RequestParams();
        try {

            MyConnectionType.post(context, HOST, AuthenticationData,
                    API_Helper.msgSend(userName, password, sender,
                            Utils.convertUnicode(msg), numbers, dateSend,
                            timeSend, deleteKey, "0", "56", alias),
                    new AsyncHttpResponseHandler() {

                        @Override
                        public void onFailure(int arg0, Header[] arg1,
                                              byte[] arg2, Throwable arg3) {
                            listener.onFailure(new Error(
                                    context.getString(R.string.service_not_available_ar),
                                    context.getString(R.string.service_not_available_en),
                                    0));
                        }

                        @Override
                        public void onSuccess(int arg0, Header[] headers,
                                              byte[] arg2) {
                            // TODO Auto-generated method stub
                            try {
                                String responseString = new String(arg2,
                                        "UTF-8");
                                Gson gson = new Gson();
                                CommonResponse commonResponse = gson.fromJson(
                                        responseString, CommonResponse.class);
                                if (commonResponse.getResponseStatus()
                                        .equalsIgnoreCase("Success")) {
                                    listener.onSuccess(commonResponse.getData());

                                } else {

                                    listener.onFailure(commonResponse
                                            .getError());

                                }

                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                listener.onFailure(new Error(
                                        context.getString(R.string.service_not_available_ar),
                                        context.getString(R.string.service_not_available_en),
                                        0));

                            }
                        }

                        @Override
                        public void onFinish() {
                            // TODO Auto-generated method stub
                            super.onFinish();

                        }

                    });

        } catch (Exception ex) {
            listener.onFailure(new Error(context
                    .getString(R.string.service_not_available_ar), context
                    .getString(R.string.service_not_available_en), 0));

            ex.printStackTrace();
        }

    }// end Send message Service


}
