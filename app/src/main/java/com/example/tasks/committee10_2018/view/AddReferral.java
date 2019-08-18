package com.example.tasks.committee10_2018.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasks.committee10_2018.MainActivity;
import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.presenter.AddReferralPresenter;
import com.example.tasks.committee10_2018.repositories.imp.AddReferralsRepositoryImp;
import com.example.tasks.committee10_2018.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReferral extends AppCompatActivity implements AddReferralInterface{

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.spinnerType)
    Spinner type;
    @BindView(R.id.spinnerRef)
    Spinner referralTo;
    @BindView(R.id.spinnerRes)
    Spinner result;
    @BindView(R.id.editText)
    EditText note;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;
    String cat;
    Context context = App.getContext();
    AddReferralPresenter presenter;
    AddReferralInterface addInterface;
    ProgressDialog progressDialog;

    stpv_case aCase;

    String[] types = {"","الشؤون الإدارية", "الدائرة الأولى","الدائرة الثانية", "الدائرة الثالثة", "الدائرة الرابعة"}, toValue, resultValue;
    Integer[] typesID = {-1,2,1,0,4,5}, toID, resultID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_referrals);
        setSupportActionBar(toolbar);
        App.setContext(this);
        ButterKnife.bind(this);


        final Bundle bundle = getIntent().getExtras();
         aCase = (stpv_case) bundle.getSerializable("case");

        toolbar_t.setText("إحالة القضية");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = getIntent().getExtras();
                bundle.putString("tabType", "referrals");
                Intent intent = new Intent(AddReferral.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.getSelectedItemPosition() != 0 && result.getSelectedItemPosition() != 0 && referralTo.getSelectedItemPosition() != 0 ){
                    String noteText = note.getText().equals(null)?"" : note.getText().toString();
                     if (aCase != null) {
                            presenter.addReferral(String.valueOf(aCase.CASE_NO), String.valueOf(toID[referralTo.getSelectedItemPosition()]), String.valueOf(typesID[type.getSelectedItemPosition()]), String.valueOf(resultID[result.getSelectedItemPosition()]), noteText);
                     }

                }else {
                    Toast.makeText(App.getContext(), "من فضلك ادخل جميع البيانات...", Toast.LENGTH_LONG).show();
                }
            }
        });
        addInterface = this;

        cat = PrefUtils.getKeys(context, context.getString(R.string.category));

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(App.getContext(), android.R.layout.simple_spinner_item, types);
        type.setAdapter(spinnerArrayAdapter);

        presenter = new AddReferralPresenter(new AddReferralsRepositoryImp(), addInterface, cat);
        presenter.getResults();
        presenter.getToUsers();

    }

    @Override
    public void result(stpv_results[] results) {
        if (results != null) {
            resultValue = new String[results.length + 1];
            resultID = new Integer[results.length + 1];
            resultValue[0] = "";
            resultID[0] = 0;
            for(int i=0 ; i<results.length ; i++){
                resultValue[i+1] = results[i].LOCDESC;
                resultID[i+1] = results[i].SUBCOD;
            }
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(App.getContext(), android.R.layout.simple_spinner_item, resultValue);
            result.setAdapter(spinnerArrayAdapter);
        }else {
            presenter.getResults();
        }
    }

    @Override
    public void to(stp_users[] stp_users) {
        if (stp_users != null) {
            toValue = new String[stp_users.length + 1];
            toID = new Integer[stp_users.length + 1];
            toValue[0] = "";
            toID[0] = 0;
            for(int i=0 ; i<stp_users.length ; i++){
                toValue[i+1] = stp_users[i].USER_NAME;
                toID[i+1] = stp_users[i].USER_NO;
            }
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(App.getContext(), android.R.layout.simple_spinner_item, toValue);
            referralTo.setAdapter(spinnerArrayAdapter);
        }else {
            presenter.getToUsers();
        }
    }

    @Override
    public void add(stpv_case newCase) {
        Intent intent = new Intent(AddReferral.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
