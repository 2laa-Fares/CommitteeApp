package com.example.tasks.committee10_2018.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.MainActivity;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.presenter.LoginPresenter;
import com.example.tasks.committee10_2018.repositories.imp.LoginRepositoryImp;
import com.example.tasks.committee10_2018.utils.MobilyAPI;
import com.example.tasks.committee10_2018.utils.OnDataReceiveListner;
import com.example.tasks.committee10_2018.utils.PrefUtils;
import com.example.tasks.committee10_2018.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginInterface{

    private static final String TAG = "LOGIN";
    @BindView(R.id.user)
    EditText userName;
    @BindView(R.id.pass)
    EditText password;
    @BindView(R.id.button)
    AppCompatButton login;
    @BindView(R.id.root_view)
    ScrollView rootView;
    @BindView(R.id.noInternetConnection)
    TextView noInternet;
    ProgressDialog progressDialog;
    LoginPresenter loginPresenter;
    LoginInterface loginInterface;
    stp_users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        App.setContext(this);


        if (!Utils.isOnline(this)){
            noInternet.setVisibility(View.VISIBLE);
            rootView.setVisibility(View.GONE);
        }else {
            noInternet.setVisibility(View.GONE);
            rootView.setVisibility(View.VISIBLE);
        }

        loginInterface = this;
        loginPresenter = new LoginPresenter(loginInterface, new LoginRepositoryImp());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        login.setEnabled(false);
        progressDialog= new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("يتم تسجيل الدخول...");
        progressDialog.show();
        String username = userName.getText().toString();
        String pass = password.getText().toString();
        loginPresenter.loginUser(username, pass);

    }

    public boolean validate() {
        boolean valid = true;
        String name = userName.getText().toString();
        String pass = password.getText().toString();
        if (name.isEmpty()) {
            userName.setError("من فضلك ادخل كود المستخدم");
            valid = false;
        } else {
            userName.setError(null);
        }
        if (pass.isEmpty()) {
            password.setError("من فضلك ادخل الباسورد");
            valid = false;
        } else {
            password.setError(null);
        }
        return valid;
    }

    public void onLoginSuccess() {
        login.setEnabled(true);
    }

    public void onLoginFailed() {
        if (!Utils.isOnline(this)){
            Toast.makeText(this, "الجهاز غير متصل بالانترنت لن يتم تسجيل الدخول برجاء الاتصال بالانترنت واعادة المحاوله...", Toast.LENGTH_LONG).show();
        }
        login.setEnabled(true);
    }

    @Override
    public void RetrieveUser(stp_users user) {
        progressDialog.dismiss();
        this.user = user;
        Context context = App.getContext();
        PrefUtils.storeKeys(context, context.getString(R.string.user_id), String.valueOf(user.USER_NO));
        PrefUtils.storeKeys(context, context.getString(R.string.category), String.valueOf(user.CATEGORY_NO));
        PrefUtils.storeKeys(context, context.getString(R.string.user_name), user.USER_NAME);

        //sendResult(1111);
        Random rnd = new Random();
        if (Utils.isOnline(this)) {
            final int answer = rnd.nextInt(999999);
            OnDataReceiveListner listerner = new OnDataReceiveListner() {
                @Override
                public void onSuccess(Object object) {
                    sendResult(answer);
                }

                @Override
                public void onFailure(Object object) {
                    Error error = (Error) object;
                    Toast.makeText(LoginActivity.this, "تعذر ارسال رسالة الإعدادات" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            };
            String mobil = user.Mobile_No.toString();
            if(mobil.charAt(0) == '0') {
                mobil = mobil.substring(1);
            }
            try {
                MobilyAPI mobilyAPI = new MobilyAPI("userName", "password");
                mobilyAPI.sendMessage("sender", "your code is: " + answer, "966" + mobil,
                        null, null, null, null, listerner);
            }catch (Exception E){
                Toast.makeText(LoginActivity.this,  E.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(LoginActivity.this, "لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
        }
    }

    private void sendResult(final int random) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.sms_code_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(mView);
        final EditText userInputDialogEditText = mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setNeutralButton("تأكيد", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if (userInputDialogEditText.getText()!=null){
                            int num = Integer.valueOf(userInputDialogEditText.getText().toString());
                            if (num == random){
                                Snackbar.make(userName.getRootView(),"تم تسجيل الدخول بنجاح", Snackbar.LENGTH_LONG).show();
                                PrefUtils.storeKeys(App.getContext(), App.getContext().getString(R.string.login_key), "true");
                                onLoginSuccess();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Snackbar.make(userName.getRootView(),"برجاء ادخال الكود الصحيح ليتم تسجيل الدخول بنجاح..", Snackbar.LENGTH_LONG).show();
                                login.setEnabled(true);

                            }
                        }
                    }
                });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    @Override
    public void NotUserFound(String error) {
        progressDialog.dismiss();
        onLoginFailed();
        Snackbar.make(rootView, error, Snackbar.LENGTH_LONG).show();
    }



}
