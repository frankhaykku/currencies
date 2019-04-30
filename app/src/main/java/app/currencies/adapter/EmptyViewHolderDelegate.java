package app.currencies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.currencies.R;

public class EmptyViewHolderDelegate implements ViewTypeDelegateAdapter {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType, OnClickListener listener) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dummy_page, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
