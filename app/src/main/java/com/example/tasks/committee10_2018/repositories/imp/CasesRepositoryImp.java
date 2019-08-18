package com.example.tasks.committee10_2018.repositories.imp;

import android.content.Context;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.network.ApiClient;
import com.example.tasks.committee10_2018.network.ApiService;
import com.example.tasks.committee10_2018.repositories.CasesRepository;
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

public class CasesRepositoryImp implements CasesRepository {
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    Context context;

    public CasesRepositoryImp(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
        context = App.getContext();
    }

    @Override
    public void cases(final OnRequestCompletedListener listener) {
        String userKey = PrefUtils.getKeys(context, context.getString(R.string.user_id));
        String cat = PrefUtils.getKeys(context, context.getString(R.string.category));
        disposable.add(
                apiService.issues(cat,userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String[]>() {
                    @Override
                    public void onSuccess(String[] cases) {
                        String[] casesJson = new String[cases.length];
                        for(int i = 0; i<cases.length; i++){
                            String string = StringToSubstring2(cases[i]);
                            casesJson[i] = string;
                        }
                        JSONObject obj;
                        stpv_case[] results = new stpv_case[casesJson.length];
                        for(int i=0; i<casesJson.length; i++ ) {
                            try {
                                obj = new JSONObject(casesJson[i]);
                                ObjectMapper m = new ObjectMapper();
                                results[i] = m.readValue(obj.toString(), stpv_case.class);
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
                        listener.onRequestCompleted(results);

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onRequestCompleted((stpv_case[]) null);
                    }
                })
        );
    }

    @Override
    public void getCase(final OnRequestCompletedListener listener, String caseNo) {
        String userKey = PrefUtils.getKeys(context, context.getString(R.string.user_id));
        String cat = PrefUtils.getKeys(context, context.getString(R.string.category));
        disposable.add(
                apiService.searchIssue(cat, userKey, caseNo)
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


    @Override
    public void getCases(final OnRequestCompletedListener listener, String finished, String result) {
        String userKey = PrefUtils.getKeys(context, context.getString(R.string.user_id));
        String cat = PrefUtils.getKeys(context, context.getString(R.string.category));
        disposable.add(
                apiService.searchIssues(cat,userKey,finished,result)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String[]>() {
                            @Override
                            public void onSuccess(String[] cases) {
                                String[] casesJson = new String[cases.length];
                                for(int i = 0; i<cases.length; i++){
                                    String string = StringToSubstring2(cases[i]);
                                    casesJson[i] = string;
                                }
                                JSONObject obj;
                                stpv_case[] results = new stpv_case[casesJson.length];
                                for(int i=0; i<casesJson.length; i++ ) {
                                    try {
                                        obj = new JSONObject(casesJson[i]);
                                        ObjectMapper m = new ObjectMapper();
                                        results[i] = m.readValue(obj.toString(), stpv_case.class);
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
                                listener.onRequestCompleted(results);

                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestCompleted((stpv_case[]) null);
                            }
                        })
        );
    }

    @Override
    public void getStatus(final OnRequestCompletedListener listener) {
        disposable.add(
                apiService.status()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String[]>() {
                            @Override
                            public void onSuccess(String[] cases) {
                                String[] casesJson = new String[cases.length];
                                for(int i = 0; i<cases.length; i++){
                                    String string = StringToSubstring2(cases[i]);
                                    casesJson[i] = string;
                                }
                                JSONObject obj;
                                syscod[] statue = new syscod[casesJson.length];
                                for(int i=0; i<casesJson.length; i++ ) {
                                    try {
                                        obj = new JSONObject(casesJson[i]);
                                        ObjectMapper m = new ObjectMapper();
                                        statue[i] = m.readValue(obj.toString(), syscod.class);
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
                                listener.onRequestCompleted(statue);

                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onRequestCompleted((syscod[]) null);
                            }
                        })
        );
    }
}
