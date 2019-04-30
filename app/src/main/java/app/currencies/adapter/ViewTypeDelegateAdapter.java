package app.currencies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface ViewTypeDelegateAdapter {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType, OnClickListener listener);
    void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item);
}
