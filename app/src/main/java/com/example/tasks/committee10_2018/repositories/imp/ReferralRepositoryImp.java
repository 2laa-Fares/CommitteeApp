package com.example.tasks.committee10_2018.repositories.imp;

import android.content.Context;

import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.network.ApiClient;
import com.example.tasks.committee10_2018.network.ApiService;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.repositories.ReferralRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.tasks.committee10_2018.utils.StringToSubstring.StringToSubstring2;

public class ReferralRepositoryImp implements ReferralRepository {
    private ApiService apiService;
    private Context context= App.getContext();

    private CompositeDisposable disposable = new CompositeDisposable();

    public ReferralRepositoryImp(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }


    @Override
    public void referral(final OnRequestCompletedListener listener , String caseNo){
        disposable.add(
                apiService.getReferrals(caseNo)
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
                                Referral[] referrals = new Referral[result.length];
                                for(int i=0; i<result.length; i++ ) {
                                    try {
                                        obj = new JSONObject(result[i]);
                                        ObjectMapper m = new ObjectMapper();
                                        referrals[i] = (m.readValue(obj.toString(), Referral.class));
                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                listener.onRequestComplete(referrals);
                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestComplete((Referral[])null);
                            }
                        })
        );
    }

}
