package com.example.tasks.committee10_2018.repositories.imp;

import android.content.Context;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.network.ApiClient;
import com.example.tasks.committee10_2018.network.ApiService;
import com.example.tasks.committee10_2018.repositories.AddReferralRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.utils.PrefUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.tasks.committee10_2018.utils.StringToSubstring.StringToSubstring2;

public class AddReferralsRepositoryImp implements AddReferralRepository {
    private ApiService apiService;
    private Context context= App.getContext();

    private CompositeDisposable disposable = new CompositeDisposable();

    public AddReferralsRepositoryImp(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }


    @Override
    public void result(final OnRequestCompletedListener listener , String cat){
        disposable.add(
                apiService.result(cat)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String[]>() {
                            @Override
                            public void onSuccess(String[] strings) {
                                String[] result = new String[strings.length];
                                for(int i = 0; i<strings.length; i++){
                                    String string = StringToSubstring2(strings[i]);
                                    result[i] = string;
                                }
                                JSONObject obj;
                                stpv_results[] results = new stpv_results[result.length];
                                for(int i=0; i<result.length; i++ ) {
                                    try {
                                        obj = new JSONObject(result[i]);
                                        ObjectMapper m = new ObjectMapper();
                                        results[i] = (m.readValue(obj.toString(), stpv_results.class));
                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                listener.onRequestComplete(results);
                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestComplete((stpv_results[])null);
                            }
                        })
        );
    }

    @Override
    public void to(final OnRequestCompletedListener listener, String cat) {
        disposable.add(
                apiService.referredTo(cat)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String[]>() {
                            @Override
                            public void onSuccess(String[] strings) {
                                String[] toUsers = new String[strings.length];
                                for(int i = 0; i<strings.length; i++){
                                    String string = StringToSubstring2(strings[i]);
                                    toUsers[i] = string;
                                }
                                JSONObject obj;
                                stp_users[] to = new stp_users[toUsers.length];
                                for(int i=0; i<toUsers.length; i++ ) {
                                    try {
                                        obj = new JSONObject(toUsers[i]);
                                        ObjectMapper m = new ObjectMapper();
                                        to[i] = (m.readValue(obj.toString(), stp_users.class));
                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                listener.onRequestComplete(to);
                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestComplete((stp_users[])null);
                            }
                        })
        );
    }

    @Override
    public void add(final OnRequestCompletedListener listener, String caseNo, String judgeNo, String caseType, String result, String notes) {
        String userKey = PrefUtils.getKeys(context, context.getString(R.string.user_id));
        disposable.add(
                apiService.addReferral(userKey, caseNo, judgeNo, caseType, result, notes)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String>() {
                            @Override
                            public void onSuccess(String cases) {
                                String json = null;
                                if(cases != null)
                                    json = StringToSubstring2(cases);
                                JSONObject obj;
                                stpv_case result = new stpv_case();
                                if(json != null){
                                    try {
                                        obj = new JSONObject(json);
                                        ObjectMapper m = new ObjectMapper();
                                        result = m.readValue(obj.toString(), stpv_case.class);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (JsonParseException e) {
                                        e.printStackTrace();
                                    } catch (JsonMappingException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                listener.onRequestCompleted(result);

                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestCompleted((stpv_case) null);
                            }
                        })
        );
    }


}
