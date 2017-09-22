package com.ramnivas.scrollbehavior;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ramnivasindani on 9/21/17.
 */

class ScrollingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Minion> minions = new Minion().getMyMinions();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            //render header view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
            return new HeaderViewHolder(view);
        } else {
            //render element view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.minions_view, parent, false);
            return new MinionViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MinionViewHolder) {
            ((MinionViewHolder) holder).minionName.setText(minions.get(position).getName());
            ((MinionViewHolder) holder).minionAge.setText(minions.get(position).getAge());
        }
    }

    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return minions.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MinionViewHolder extends RecyclerView.ViewHolder{
        TextView minionName;
        TextView minionAge;
        public MinionViewHolder(View itemView) {
            super(itemView);
            minionName = (TextView) itemView.findViewById(R.id.minion_name);
            minionAge = (TextView) itemView.findViewById(R.id.minion_age);
        }
    }
}
