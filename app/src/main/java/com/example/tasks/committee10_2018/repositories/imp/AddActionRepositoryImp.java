package com.example.tasks.committee10_2018.repositories.imp;

import android.content.Context;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.network.ApiClient;
import com.example.tasks.committee10_2018.network.ApiService;
import com.example.tasks.committee10_2018.repositories.AddActionRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.utils.PrefUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.tasks.committee10_2018.utils.StringToSubstring.StringToSubstring2;

public class AddActionRepositoryImp implements AddActionRepository {
    private ApiService apiService;
    private Context context= App.getContext();

    private CompositeDisposable disposable = new CompositeDisposable();

    public AddActionRepositoryImp(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }

    @Override
    public void taken(final OnRequestCompletedListener listener) {
        disposable.add(
                apiService.actionToken()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String[]>() {
                            @Override
                            public void onSuccess(String[] strings) {
                                String[] tokens = new String[strings.length];
                                for(int i = 0; i<strings.length; i++){
                                    String string = StringToSubstring2(strings[i]);
                                    tokens[i] = string;
                                }
                                JSONObject obj;
                                syscod[] actions = new syscod[tokens.length];
                                for(int i=0; i<tokens.length; i++ ) {
                                    try {
                                        obj = new JSONObject(tokens[i]);
                                        ObjectMapper m = new ObjectMapper();
                                        actions[i] = (m.readValue(obj.toString(), syscod.class));
                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                listener.onRequestComplete(actions);
                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestComplete((syscod[])null);
                            }
                        })
        );
    }

    @Override
    public void add(final OnRequestCompletedListener listener, String caseNo, String taken, String note) {
        String userKey = PrefUtils.getKeys(context, context.getString(R.string.user_id));
        disposable.add(
                apiService.addAction(caseNo, taken, note, userKey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String>() {
                            @Override
                            public void onSuccess(String result) {
                                listener.onRequestCompleted(result);
                            }
                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestCompleted((String) null);
                            }
                        })
        );
    }


}
