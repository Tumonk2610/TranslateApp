package com.cardiomood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardiomood.hoanglong.R;
import com.cardiomood.model.Suggest;

import java.util.ArrayList;
import java.util.List;


public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>{

    private List<Suggest> suggests = new ArrayList<>();
    private Context context;
    private View.OnClickListener onClickListener;

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public SuggestAdapter(List<Suggest> suggests, Context context) {
        this.suggests = suggests;
        this.context = context;
    }

    @Override
    public SuggestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_suggest, viewGroup, false);
        view.setOnClickListener(onClickListener);

        return new SuggestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SuggestViewHolder suggestViewHolder, int position) {
        suggestViewHolder.setData(suggests.get(position));
    }

    @Override
    public int getItemCount() {
        return suggests.size();
    }

    public class SuggestViewHolder extends RecyclerView.ViewHolder{
        TextView tvSuggest;
        View view;

        public SuggestViewHolder(View itemView) {
            super(itemView);
            tvSuggest = itemView.findViewById(R.id.tv_suggest);

            view = itemView;
        }

        public void setData(Suggest suggest){
            tvSuggest.setText(suggest.getText());

            view.setTag(suggest);
        }
    }
}
