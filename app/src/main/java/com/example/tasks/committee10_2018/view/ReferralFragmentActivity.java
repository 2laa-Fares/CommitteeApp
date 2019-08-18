package com.example.tasks.committee10_2018.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.presenter.ReferralPresenter;
import com.example.tasks.committee10_2018.repositories.imp.ReferralRepositoryImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferralFragmentActivity extends Fragment implements ReferralInterface {
    @BindView(R.id.ReferralsRecyclerView)
    RecyclerView referralsRecyclerView;
    @BindView(R.id.NoReferralsTV)
    TextView noReferralsTextView;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;

    ReferralAdapter adapter;
    ProgressDialog progressDialog;
    ReferralInterface referralInterface;
    ReferralPresenter presenter;

    Context context = App.getContext();
    stpv_case aCase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.activity_refferal_fragment, container, false);
        ButterKnife.bind(this, view);

        toolbar_t.setText("الإحالات");
        save.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.add, null));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), AddReferral.class);
                Bundle bundle = getActivity().getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), CaseTabs.class);
                Bundle bundle = getActivity().getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();
            }
        });

        progressDialog= new ProgressDialog(context,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("يتم تحميل الإحالات...");
        progressDialog.show();

        referralInterface = this;
        presenter = new ReferralPresenter(new ReferralRepositoryImp(), referralInterface);
        if (getArguments() != null) {
            aCase = (stpv_case) getArguments().getSerializable("case");
                presenter.getReferrals(String.valueOf(aCase.CASE_NO));
        }

        return view;
    }

    @Override
    public void referrals(Referral[] referrals) {
        if (referrals == null) {
            progressDialog.dismiss();
            noReferralsTextView.setVisibility(View.VISIBLE);
            referralsRecyclerView.setVisibility(View.GONE);
        } else {
            progressDialog.dismiss();
            noReferralsTextView.setVisibility(View.GONE);
            referralsRecyclerView.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            referralsRecyclerView.setLayoutManager(layoutManager);
            ReferralAdapter adapter = new ReferralAdapter(referrals, aCase);
            referralsRecyclerView.setAdapter(adapter);
        }
    }
}
