package com.example.tasks.committee10_2018.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.FileInfo;
import com.example.tasks.committee10_2018.model.stpv_case;


import butterknife.BindView;
import butterknife.ButterKnife;

public class AttachmentsAdapter extends RecyclerView.Adapter<AttachmentsAdapter.CaseAttachmentsViewHolder> {

    FileInfo[] fileInfos;
    stpv_case stpv_case;
    ProgressDialog progressDialog;
    WebView webView;

    public AttachmentsAdapter(FileInfo[] fileInfos, stpv_case stpv_case, WebView webView) {
        this.fileInfos = fileInfos;
        this.stpv_case = stpv_case;
        this.webView = webView;
    }

    @NonNull
    @Override
    public CaseAttachmentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.case_attachments_item, viewGroup, false);
        return new CaseAttachmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseAttachmentsViewHolder holder, final int i) {
        final String fileName = fileInfos[i].OriginalPath;
        holder.attachmentTV.setText(fileName);
        holder.attachmentTV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Toast.makeText(App.getContext(), "جارى التحميل...", Toast.LENGTH_LONG).show();
                webView.setVisibility(View.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.setBackgroundColor(android.R.color.transparent);
                webView.loadUrl("http://docs.google.com/viewer?url=AttachmentLink");
            }
        });
    }

    @Override
    public int getItemCount() {
        if (fileInfos != null)
            return fileInfos.length;
        else return 0;
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
