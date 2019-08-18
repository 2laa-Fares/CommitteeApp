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

public class IssueFirstParty extends Fragment {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.spinner_identification)
    TextView iden;
    @BindView(R.id.telephone)
    TextView tele;
    @BindView(R.id.phone2)
    TextView phonel;
    @BindView(R.id.spinner_gender)
    TextView gender;
    @BindView(R.id.spinner_nationality)
    TextView nationality;
    @BindView(R.id.title1)
    TextView title;
    @BindView(R.id.relevant)
    TextView relevant;
    @BindView(R.id.relevant_phone)
    TextView relevantno;
    @BindView(R.id.relevant_spinner)
    TextView relevanttype;
    @BindView(R.id.work_address)
    TextView jobAddress;
    @BindView(R.id.notes)
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
        View rootView = inflater.inflate(R.layout.activity_issue_first_party, container, false);
        ButterKnife.bind(this, rootView);
        // Set the Text to try this out

        toolbar_t.setText("الطرف الأول");

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
                if(aCase.COMPLAINANT_NAME != null)
                    name.setText(aCase.COMPLAINANT_NAME);

                if(aCase.COMPLAINANT_DOCUMENT_NO != null)
                    phone.setText(aCase.COMPLAINANT_DOCUMENT_NO );

                if(aCase.COMPLAINANT_DOCUMENT_TYPE != null)
                    iden.setText(aCase.COMPLAINANT_DOCUMENT_TYPE);

                if(aCase.COMPLAINANT_LAND_PHONE_NO != null)
                    tele.setText(aCase.COMPLAINANT_LAND_PHONE_NO);

                if(aCase.COMPLAINANT_MOBILE_NO != null)
                    phonel.setText(aCase.COMPLAINANT_MOBILE_NO);

                if(aCase.COMPLAINANT_GENDER != null)
                    gender.setText(aCase.COMPLAINANT_GENDER );

                if(aCase.COMPLAINANT_NATIONALITY != null)
                    nationality.setText(aCase.COMPLAINANT_NATIONALITY);

                if(aCase.COMPLAINANT_HOME_ADDRESS != null)
                    title.setText(aCase.COMPLAINANT_HOME_ADDRESS);

                if(aCase.COMPLAINANT_RELATIVE_NAME != null)
                    relevant.setText(aCase.COMPLAINANT_RELATIVE_NAME);

                if(aCase.COMPLAINANT_RELATIVE_PHONE_NO != null)
                    relevantno.setText(aCase.COMPLAINANT_RELATIVE_PHONE_NO);

                if(aCase.COMPLAINANT_RELATIVE_TYPE != null)
                    relevanttype.setText(aCase.COMPLAINANT_RELATIVE_TYPE);

                if(aCase.COMPLAINANT_JOB_ORG_ADDRESS != null)
                    jobAddress.setText(aCase.COMPLAINANT_JOB_ORG_ADDRESS);

                if(aCase.COMPLAINANT_REMARKS != null)
                    notes.setText(aCase.COMPLAINANT_REMARKS);
            }
        }

        return rootView;
    }
}
