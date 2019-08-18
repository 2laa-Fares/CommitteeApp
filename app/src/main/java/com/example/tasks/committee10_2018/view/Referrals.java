package com.example.tasks.committee10_2018.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stpv_case;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Referrals extends Fragment {

    @BindView(R.id.fromResultTV)
    TextView from;
    @BindView(R.id.toResultTV)
    TextView to;
    @BindView(R.id.noteResultTV)
    TextView note;
    @BindView(R.id.typeDetialsTV)
    TextView type;
    @BindView(R.id.resultDetialsTV)
    TextView result;
    @BindView(R.id.add_referral)
    ImageButton add;
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
        View rootView1 = inflater.inflate(R.layout.activity_referral, container, false);
        ButterKnife.bind(this, rootView1);
        // Set the Text to try this out

        toolbar_t.setText("الإحالات");

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
                if(aCase.FromUser!= null) {
                    from.setText(aCase.FromUser);
                    if (aCase.ToUesr != null)
                        to.setText(aCase.ToUesr);
                    if (aCase.NOTES != null)
                        note.setText(aCase.NOTES);
                    if (aCase.CASE_TYPE != null)
                        type.setText(aCase.CASE_TYPE);
                    if (aCase.Statue != null)
                        result.setText(aCase.Statue);
                }
            }
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(App.getContext(), AddReferral.class);
                Bundle bundle = getActivity().getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView1;
    }
}
