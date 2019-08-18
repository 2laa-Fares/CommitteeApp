package com.example.tasks.committee10_2018.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stpv_case;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 2laa on 4/7/2018.
 */

public class IssueSecondParty extends Fragment {

    @BindView(R.id.name2)
    TextView name;
    @BindView(R.id.phone4)
    TextView phone;
    @BindView(R.id.spinner_identification2)
    TextView iden;
    @BindView(R.id.telephone2)
    TextView tele;
    @BindView(R.id.phone3)
    TextView phonel;
    @BindView(R.id.spinner_gender2)
    TextView gender;
    @BindView(R.id.spinner_nationality2)
    TextView nationality;
    @BindView(R.id.title3)
    TextView title;
    @BindView(R.id.relevant2)
    TextView relevant;
    @BindView(R.id.relevant_phone2)
    TextView relevantno;
    @BindView(R.id.relevant_spinner2)
    TextView relevanttype;
    @BindView(R.id.work_address2)
    TextView jobAddress;
    @BindView(R.id.notes2)
    TextView notes;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;
    stpv_case aCase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_issue_second_party, container, false);
        ButterKnife.bind(this, rootView);
        // Set the Text to try this out

        toolbar_t.setText("الطرف الثاني");

        save.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(App.getContext(), CaseTabs.class);
                Bundle bundle = getActivity().getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();
            }
        });

        if (getArguments() != null) {
            aCase = (stpv_case) getArguments().getSerializable("case");
            if (aCase != null) {
                if(aCase.RESPONDENT_NAME != null)
                    name.setText(aCase.RESPONDENT_NAME);

                if(aCase.RESPONDENT_DOCUMENT_NO != null)
                    phone.setText(aCase.RESPONDENT_DOCUMENT_NO );

                if(aCase.RESPONDENT_DOCUMENT_TYPE != null)
                    iden.setText(aCase.RESPONDENT_DOCUMENT_TYPE);

                if(aCase.RESPONDENT_LAND_PHONE_NO != null)
                    tele.setText(aCase.RESPONDENT_LAND_PHONE_NO);

                if(aCase.RESPONDENT_MOBILE_NO != null)
                    phonel.setText(aCase.RESPONDENT_MOBILE_NO);

                if(aCase.RESPONDENT_GENDER != null)
                    gender.setText(aCase.RESPONDENT_GENDER );

                if(aCase.RESPONDENT_NATIONALITY != null)
                    nationality.setText(aCase.RESPONDENT_NATIONALITY);

                if(aCase.RESPONDENT_HOME_ADDRESS != null)
                    title.setText(aCase.RESPONDENT_HOME_ADDRESS);

                if(aCase.RESPONDENT_RELATIVE_NAME != null)
                    relevant.setText(aCase.RESPONDENT_RELATIVE_NAME);

                if(aCase.RESPONDENT_RELATIVE_PHONE_NO != null)
                    relevantno.setText(aCase.RESPONDENT_RELATIVE_PHONE_NO);

                if(aCase.RESPONDENT_RELATIVE_TYPE != null)
                    relevanttype.setText(aCase.RESPONDENT_RELATIVE_TYPE);

                if(aCase.RESPONDENT_JOB_ORG_ADDRESS != null)
                    jobAddress.setText(aCase.RESPONDENT_JOB_ORG_ADDRESS);

                if(aCase.RESPONDENT_REMARKS != null)
                    notes.setText(aCase.RESPONDENT_REMARKS);
            }
        }
        return rootView;
    }
}
