package com.example.tasks.committee10_2018.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.ActionClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.actionViewHolder>{

    private ActionClass[] actions;
    Context context = App.getContext();

    public ActionAdapter(ActionClass[] actions){
        this.actions = actions;
    }

    @NonNull
    @Override
    public actionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.action_item, viewGroup, false);
        return new actionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull actionViewHolder actionsViewHolder, final int position) {

        if (actions[position].ACTION_TAKEN != null)
            actionsViewHolder.actionTakenTV.setText(actions[position].ACTION_TAKEN);

        if (actions[position].ACTION_NOTE != null)
            actionsViewHolder.noteResultTV.setText(actions[position].ACTION_NOTE);

        if (actions[position].ACTION_USER != null)
            actionsViewHolder.byTV.setText(actions[position].ACTION_USER);

        if (actions[position].ACTION_DATETIME_H != null)
            actionsViewHolder.date.setText(actions[position].ACTION_DATETIME_H);
    }

    @Override
    public int getItemCount() {
        if (actions == null){
            return 0;
        }
        return actions.length;
    }

    class actionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.actionTakenTV)
        TextView actionTakenTV;
        @BindView(R.id.noteResultTV)
        TextView noteResultTV;
        @BindView(R.id.byTV)
        TextView byTV;
        @BindView(R.id.dateTV)
        TextView date;
        private actionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
