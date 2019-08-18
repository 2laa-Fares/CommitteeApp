package com.example.tasks.committee10_2018.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.Referral;
import com.example.tasks.committee10_2018.model.stpv_case;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferralAdapter extends RecyclerView.Adapter<ReferralAdapter.referralViewHolder>{

    private stpv_case aCase;
    private Referral[] referrals;
    Context context = App.getContext();

    public ReferralAdapter(Referral[] referrals, stpv_case aCase){
        this.referrals = referrals;
        this.aCase = aCase;
    }

    @NonNull
    @Override
    public referralViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.referral_item, viewGroup, false);
        return new referralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull referralViewHolder referralsViewHolder, final int position) {

        if (referrals[position].REFERRAL_FROM_SIDE != null)
            referralsViewHolder.from.setText(referrals[position].REFERRAL_FROM_SIDE);

        if (referrals[position].REFERRAL_TO_SIDE != null)
            referralsViewHolder.to.setText(referrals[position].REFERRAL_TO_SIDE);

        if (referrals[position].NOTES != null)
            referralsViewHolder.note.setText(referrals[position].NOTES);

        if (referrals[position].CREATE_DATE_H != null)
            referralsViewHolder.date.setText(referrals[position].CREATE_DATE_H);
    }

    @Override
    public int getItemCount() {
        if (referrals == null){
            return 0;
        }
        return referrals.length;
    }

    class referralViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ReferralItemCardView)
        CardView referralItemCardView;
        @BindView(R.id.fromResultTV)
        TextView from;
        @BindView(R.id.toResultTV)
        TextView to;
        @BindView(R.id.noteResultTV)
        TextView note;
        @BindView(R.id.dateTV)
        TextView date;
        private referralViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
