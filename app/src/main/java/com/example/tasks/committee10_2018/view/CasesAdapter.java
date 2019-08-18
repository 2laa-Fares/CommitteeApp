package com.example.tasks.committee10_2018.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stpv_case;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.casesViewHolder>{

    private stpv_case[] cases;
    Context context = App.getContext();

    TextView caseNo, caseTitle, caseDate, caseType, caseStatus, caseSource, caseFrom, caseTo, caseTransDate, caseTrans;
    Button floatingActionButton;

    public CasesAdapter(stpv_case[] cases){
        this.cases = cases;
    }

    @NonNull
    @Override
    public casesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cases_item, viewGroup, false);
        return new casesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull casesViewHolder casesViewHolder, final int position) {

        if(cases[position].CASE_NO != 0)
        casesViewHolder.case_number.setText(cases[position].CASE_NO + "");

        String[] types = {"الشؤون الإدارية", "الدائرة الأولى", "الدائرة الثانية", "الدائرة الثالثة", "الدائرة الرابعة"};
        int[] typesID = {2, 1, 0, 4, 5};
        if (cases[position].CASE_TYPE != null) {
            for (int j = 0; j < 5; j++) {
                if (cases[position].CASE_TYPE == typesID[j]) {
                    casesViewHolder.case_type.setText(types[j]);
                }
            }
        } else
            casesViewHolder.case_type.setText(" ");

        if (cases[position].CASE_TITLE != null) {
            casesViewHolder.case_title.setText(cases[position].CASE_TITLE);
        }

        if (cases[position].CASE_DATE_H != null) {
            casesViewHolder.case_date.setText(cases[position].CASE_DATE_H);
        }

        if (cases[position].Statue != null) {
            if(cases[position].Statue.equals("معلقة"))
                casesViewHolder.result_image.setVisibility(View.VISIBLE);
            casesViewHolder.case_result.setText(cases[position].Statue);
        }

        casesViewHolder.casesItemCardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "RestrictedApi"})
            @Override
            public void onClick(View view) {
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.case_item_popup_window, null);
                //Get the devices screen density to calculate correct pixel sizes
                float density = context.getResources().getDisplayMetrics().density;
                // create a focusable PopupWindow with the given layout and correct size
                final PopupWindow pw = new PopupWindow(layout, (int) density * 300, ViewPager.LayoutParams.WRAP_CONTENT, true);
                pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pw.setTouchInterceptor(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            pw.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
                pw.setOutsideTouchable(true);
                // display the pop-up in the center
                caseNo = layout.findViewById(R.id.case_no);
                caseTitle = layout.findViewById(R.id.case_title);
                caseDate = layout.findViewById(R.id.incoming_date);
                caseType = layout.findViewById(R.id.case_type);
                caseStatus = layout.findViewById(R.id.case_status);
                caseSource = layout.findViewById(R.id.case_source);
                caseFrom = layout.findViewById(R.id.from_TV);
                caseTo = layout.findViewById(R.id.to_TV);
                caseTrans = layout.findViewById(R.id.lastTransformation);
                caseTransDate = layout.findViewById(R.id.lastTransformationDate);
                floatingActionButton = layout.findViewById(R.id.floatingActionButton);

                caseNo.setText(cases[position].CASE_NO + "");
                if (cases[position].CASE_TITLE != null)
                    caseTitle.setText(cases[position].CASE_TITLE);
                if (cases[position].CASE_DATE_H != null)
                    caseDate.setText(cases[position].CASE_DATE_H);
                String[] types = {"الشؤون الإدارية", "الدائرة الأولى", "الدائرة الثانية", "الدائرة الثالثة", "الدائرة الرابعة"};
                int[] typesID = {2, 1, 0, 4, 5};
                if (cases[position].CASE_TYPE != null) {
                    for (int j = 0; j < 5; j++) {
                        if (cases[position].CASE_TYPE == typesID[j]) {
                            caseType.setText(types[j]);
                        }
                    }
                }
                if (cases[position].Statue != null)
                    caseStatus.setText(cases[position].Statue);
                if (cases[position].SourceName != null)
                    caseSource.setText(cases[position].SourceName);
                if (cases[position].COMPLAINANT_NAME != null)
                    caseFrom.setText(cases[position].COMPLAINANT_NAME);
                if (cases[position].RESPONDENT_NAME != null)
                    caseTo.setText(cases[position].RESPONDENT_NAME);
                if (cases[position].USER_NAME != null)
                    caseTrans.setText(cases[position].USER_NAME);
                if (cases[position].JUDGEMENT_RECEIVE_DATE_H != null)
                    caseTransDate.setText(cases[position].JUDGEMENT_RECEIVE_DATE_H);

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pw.dismiss();
                        stpv_case caseT = cases[position];
                        Intent intent = new Intent(App.getContext(), CaseTabs.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("case", caseT);
                        intent.putExtras(bundle);
                        App.getContext().startActivity(intent);
                        ((Activity) context).finish();

                    }
                });

                try {
                    pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (cases == null){
            return 0;
        }
        return cases.length;
    }

    class casesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.casesItemCardView)
        CardView casesItemCardView;
        @BindView(R.id.casesNo)
        TextView case_number;
        @BindView(R.id.caseTitle)
        TextView case_title;
        @BindView(R.id.caseType)
        TextView case_type;
        @BindView(R.id.incomingDateTV)
        TextView case_date;
        @BindView(R.id.incomingResultTextView)
        TextView case_result;
        @BindView(R.id.incomingResultImageView)
        ImageView result_image;
        private casesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
