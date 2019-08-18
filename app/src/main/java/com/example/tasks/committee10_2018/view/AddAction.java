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
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.presenter.AddActionPresenter;
import com.example.tasks.committee10_2018.presenter.AddReferralPresenter;
import com.example.tasks.committee10_2018.repositories.imp.AddActionRepositoryImp;
import com.example.tasks.committee10_2018.repositories.imp.AddReferralsRepositoryImp;
import com.example.tasks.committee10_2018.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAction extends AppCompatActivity implements AddActionInterface{

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.spinnerActionTokan)
    Spinner actionToken;
    @BindView(R.id.editText)
    EditText note;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;

    Context context = App.getContext();

    AddActionPresenter presenter;
    AddActionInterface addInterface;

    stpv_case aCase;

    String[] tokensValue;
    Integer[] tokensID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_actions);
        setSupportActionBar(toolbar);
        App.setContext(this);
        ButterKnife.bind(this);


        final Bundle bundle = getIntent().getExtras();
         aCase = (stpv_case) bundle.getSerializable("case");

        toolbar_t.setText("إجراء جديد");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = getIntent().getExtras();
                bundle.putString("tabType", "actions");
                Intent intent = new Intent(AddAction.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actionToken.getSelectedItemPosition() != 0){
                    String noteText = note.getText().equals(null)?"" : note.getText().toString();
                     if (aCase != null) {
                            presenter.addAction(String.valueOf(aCase.CASE_NO), String.valueOf(tokensID[actionToken.getSelectedItemPosition()]), noteText);
                     }

                }else {
                    Toast.makeText(App.getContext(), "من فضلك ادخل جميع البيانات...", Toast.LENGTH_LONG).show();
                }
            }
        });
        addInterface = this;

        presenter = new AddActionPresenter(new AddActionRepositoryImp(), addInterface);
        presenter.getActionToken();

    }

    @Override
    public void taken(syscod[] tokens) {
        if (tokens != null) {
            tokensValue = new String[tokens.length + 1];
            tokensID = new Integer[tokens.length + 1];
            tokensValue[0] = "";
            tokensID[0] = 0;
            for(int i=0 ; i<tokens.length ; i++){
                tokensValue[i+1] = tokens[i].LOCDESC;
                tokensID[i+1] = tokens[i].SUBCOD;
            }
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(App.getContext(), android.R.layout.simple_spinner_item, tokensValue);
            actionToken.setAdapter(spinnerArrayAdapter);
        }else {
            presenter.getActionToken();
        }
    }

    @Override
    public void add(String result) {
        final Bundle bundle = getIntent().getExtras();
        bundle.putString("tabType", "actions");
        Intent intent = new Intent(AddAction.this, CaseTabsDetialsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
