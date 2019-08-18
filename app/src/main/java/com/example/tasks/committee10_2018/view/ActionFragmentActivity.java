package com.example.tasks.committee10_2018.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.tasks.committee10_2018.model.ActionClass;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.presenter.ActionPresenter;
import com.example.tasks.committee10_2018.repositories.imp.ActionRepositoryImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionFragmentActivity extends Fragment implements ActionInterface {
    @BindView(R.id.ActionsRecyclerView)
    RecyclerView actionsRecyclerView;
    @BindView(R.id.NoActionsTV)
    TextView noActionsTextView;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;

    ActionAdapter adapter;
    ProgressDialog progressDialog;
    ActionInterface Interface;
    ActionPresenter presenter;

    Context context = App.getContext();
    stpv_case aCase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.activity_action_fragment, container, false);
        ButterKnife.bind(this, view);

        toolbar_t.setText("الإجراءات المتبعة");
        save.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.add, null));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), AddAction.class);
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
        progressDialog.setMessage("يتم تحميل الإجراءات...");
        progressDialog.show();

        Interface = this;
        presenter = new ActionPresenter(new ActionRepositoryImp(), Interface);
        if (getArguments() != null) {
            aCase = (stpv_case) getArguments().getSerializable("case");
            presenter.getActions(String.valueOf(aCase.CASE_NO));
        }

        return view;
    }

    @Override
    public void actions(ActionClass[] actions) {
        if (actions == null) {
            progressDialog.dismiss();
            noActionsTextView.setVisibility(View.VISIBLE);
            actionsRecyclerView.setVisibility(View.GONE);
        } else {
            progressDialog.dismiss();
            noActionsTextView.setVisibility(View.GONE);
            actionsRecyclerView.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            actionsRecyclerView.setLayoutManager(layoutManager);
            ActionAdapter adapter = new ActionAdapter(actions);
            if(adapter == null)
                noActionsTextView.setVisibility(View.VISIBLE);
            actionsRecyclerView.setAdapter(adapter);
        }
    }
}
