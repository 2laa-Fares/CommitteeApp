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

public class IssueContent extends Fragment {

    @BindView(R.id.title2)
    TextView Title;
    @BindView(R.id.documents_no)
    TextView Attachment;
    @BindView(R.id.documents_type)
    TextView AttachmentType;
    @BindView(R.id.resource)
    TextView Source;
    @BindView(R.id.resource_no)
    TextView SourceNo;
    @BindView(R.id.resource_date)
    TextView SourceDate;
    @BindView(R.id.coming_date)
    TextView IncomingDate;
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
        View rootView1 = inflater.inflate(R.layout.activity_issue_content, container, false);
        ButterKnife.bind(this, rootView1);
        // Set the Text to try this out

        toolbar_t.setText("بيانات القضية");

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
                if(aCase.CASE_TITLE != null)
                    Title.setText(aCase.CASE_TITLE);
                if(aCase.NUMBER_OF_ATTACHMENTS != 0)
                    Attachment.setText(aCase.NUMBER_OF_ATTACHMENTS+"");
                if(aCase.attachment_Type != null)
                    AttachmentType.setText(aCase.attachment_Type);
                if(aCase.SourceName != null)
                    Source.setText(aCase.SourceName);
                if(aCase.SOURCE_NO != null)
                    SourceNo.setText(aCase.SOURCE_NO);
                if(aCase.SOURCE_DATE_H != null)
                    SourceDate.setText(aCase.SOURCE_DATE_H);
                if(aCase.INCOMING_DATE_H!= null)
                    IncomingDate.setText(aCase.INCOMING_DATE_H);
            }
        }

        return rootView1;
    }

}
