package com.example.tasks.committee10_2018.repositories.imp;

import android.content.Context;

import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.network.ApiClient;
import com.example.tasks.committee10_2018.network.ApiService;
import com.example.tasks.committee10_2018.repositories.LoginRepository;
import com.example.tasks.committee10_2018.repositories.OnRequestCompletedListener;
import com.example.tasks.committee10_2018.utils.PrefUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.tasks.committee10_2018.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.tasks.committee10_2018.utils.StringToSubstring.StringToSubstring2;

public class LoginRepositoryImp implements LoginRepository {
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    stp_users user = null;

    public LoginRepositoryImp(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }

    @Override
    public void login(String userName, String pass, final OnRequestCompletedListener listener){
        disposable.add(
                apiService.login(userName, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String mUser) {
                        String response = StringToSubstring2(mUser);
                        JSONObject obj = null;
                        try {
                            Context context = App.getContext();
                            obj = new JSONObject(response);
                            ObjectMapper m = new ObjectMapper();
                            user = m.readValue(obj.toString(), stp_users.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        listener.onRequestCompleted(user);
                    }
                    @Override
                    public void onError(Throwable e) {
                        listener.onRequestCompleted(user);
                    }
                })
        );
    }

    public void dispose(){
        disposable.dispose();
    }
}
