package com.example.tasks.committee10_2018.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tasks.committee10_2018.MainActivity;
import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.presenter.AttachmentsPresenter;
import com.example.tasks.committee10_2018.repositories.imp.AttachmentsRepositoryImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Attachments extends Fragment implements AttachmentsInterface{

    @BindView(R.id.imageView4)
    ImageView attachmentsImage;
    @BindView(R.id.attachmentsRecyclerView)
    android.support.v7.widget.RecyclerView attachmentsRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbar_t;
    @BindView(R.id.toolbar_back)
    Button back;
    @BindView(R.id.toolbar_save)
    Button save;
    @BindView(R.id.wview)
    WebView webView;
    stpv_case aCase;
    Context context = App.getContext();
    AttachmentsInterface Interface;
    AttachmentsPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.case_attachments_fragment, container, false);
        ButterKnife.bind(this, rootView);
        // Set the Text to try this out

        Interface = this;
        presenter = new AttachmentsPresenter(new AttachmentsRepositoryImp(), Interface);
        toolbar_t.setText("المرفقات");

        progressDialog= new ProgressDialog(context,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("يتم تحميل المرفقات...");
        progressDialog.show();

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
                presenter.getAttachments(String.valueOf(aCase.CASE_NO));
            }
        }

        return rootView;
    }

    @Override
    public void result(FileInfo[] files) {
        progressDialog.dismiss();
        if(files != null) {
            attachmentsRecyclerView.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            attachmentsRecyclerView.setLayoutManager(layoutManager);
            AttachmentsAdapter adapter = new AttachmentsAdapter(files, aCase, webView);
            attachmentsRecyclerView.setAdapter(adapter);
        }else{
            attachmentsImage.setVisibility(View.VISIBLE);
            attachmentsRecyclerView.setVisibility(View.GONE);
        }
    }

    public class CaseAttachmentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.attachmentTV)
        TextView attachmentTV;

        public CaseAttachmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
