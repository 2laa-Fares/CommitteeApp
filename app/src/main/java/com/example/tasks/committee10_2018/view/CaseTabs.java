package com.example.tasks.committee10_2018.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tasks.committee10_2018.MainActivity;
import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.utils.PrefUtils;
import com.example.tasks.committee10_2018.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CaseTabs extends AppCompatActivity {

    @BindView(R.id.issue_content)
    CardView caseInfo;
    @BindView(R.id.first_party)
    CardView caseFirstParty;
    @BindView(R.id.secound_party)
    CardView caseSecoundParty;
    @BindView(R.id.referrals)
    CardView referrals;
    @BindView(R.id.attachments)
    CardView CaseAttachment;
    @BindView(R.id.actions)
    CardView actions;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_tabs);
        App.setContext(this);
        ButterKnife.bind(this);

        final Bundle bundle = getIntent().getExtras();
        stpv_case aCase = (stpv_case) bundle.getSerializable("case");
        if(aCase.CASE_TITLE != null)
            toolbar_t.setText(aCase.CASE_TITLE);
        else
            toolbar_t.setText("قضية رقم: " + aCase.CASE_NO);
        save.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaseTabs.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        caseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tabType", "caseInfo");
                Intent intent = new Intent(CaseTabs.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        caseFirstParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tabType", "caseFirstParty");
                Intent intent = new Intent(CaseTabs.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        caseSecoundParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tabType", "caseSecoundParty");
                Intent intent = new Intent(CaseTabs.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        referrals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tabType", "referrals");
                Intent intent = new Intent(CaseTabs.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        CaseAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tabType", "CaseAttachment");
                Intent intent = new Intent(CaseTabs.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        actions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tabType", "actions");
                Intent intent = new Intent(CaseTabs.this, CaseTabsDetialsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CaseTabs.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
