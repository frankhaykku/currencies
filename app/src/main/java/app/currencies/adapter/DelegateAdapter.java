package app.currencies.adapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import app.currencies.model.Currency;

public class DelegateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ViewType> mData;
    private SparseArrayCompat<ViewTypeDelegateAdapter> mDelegates = new SparseArrayCompat<>();
    private SparseArrayCompat<OnClickListener> mListeners = new SparseArrayCompat<>();
    private boolean isEmptyViewInit = false;

    public DelegateAdapter() {
        mData = new ArrayList<>();
    }

    public DelegateAdapter addDelegate(ViewType.TYPES type, ViewTypeDelegateAdapter delegate, OnClickListener listener) {
        if(delegate != null)
            mDelegates.put(type.ordinal(), delegate);

        if(listener != null)
            mListeners.put(type.ordinal(), listener);

        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mDelegates.get(viewType)
                .createViewHolder(parent, viewType, mListeners.get(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mDelegates.get(getItemViewType(i))
                .onBindViewHolder(viewHolder, mData.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) == null
                ? ViewType.TYPES.EMPTY.ordinal()
                : mData.get(position).getViewType().ordinal();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void enableEmptyView(boolean enable) {
        mData.clear();
        isEmptyViewInit = enable;

        if(enable) mData.add(null);
    }

    public boolean isEmptyViewInit() {
        return isEmptyViewInit;
    }

    public void addItem(ViewType item) {
        if(mData.contains(item)) {
            int index = mData.indexOf(item);

            if(mData.get(index) instanceof Currency) {
                double rate = ((Currency) item).getRate();
                ((Currency) mData.get(index)).setRate(rate);
                return;
            }
        }

        mData.add(item);
    }

    public void moveItemToHead(int position) {
        if(position < 0 || position > mData.size())
            return;

        ViewType type = mData.get(position);
        mData.remove(type);
        mData.add(0, type);
        notifyItemMoved(position, 0);
    }

    public Currency getItem(int position) {
        if(position < 0 || position >mData.size())
            return null;

        return mData.get(position) instanceof Currency
            ? (Currency) mData.get(position)
            : null;
    }
}
