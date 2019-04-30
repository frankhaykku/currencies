package app.currencies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import app.currencies.R;
import app.currencies.model.Currency;

public class CurrencyViewHolderDelegate implements ViewTypeDelegateAdapter {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType, OnClickListener listener) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_currency_one_item, parent, false),
                listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {
        ((ViewHolder)holder).bind((Currency) item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnFocusChangeListener, TextView.OnEditorActionListener {

        ImageView mCurrencyImage;
        TextView mCurrencyName;
        TextView mCurrencyDesc;
        EditText mCurrencyValueField;
        OnClickListener mListener;

        public ViewHolder(View itemView, OnClickListener listener) {
            super(itemView);

            mCurrencyImage = itemView.findViewById(R.id.currency_image);
            mCurrencyName = itemView.findViewById(R.id.currency_short_name);
            mCurrencyDesc = itemView.findViewById(R.id.currency_desc);
            mCurrencyValueField = itemView.findViewById(R.id.currency_value_field);

            mListener = listener;

            mCurrencyValueField.setOnFocusChangeListener(this);
            mCurrencyValueField.setOnEditorActionListener(this);
            mCurrencyValueField.addTextChangedListener(new TextWatcher() {
                private String beforeText = "";

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    beforeText = s.toString();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s == null)
                        return;

                    int initialCursorPos = start + before;
                    int countAfterCursorPos = getNumberOfLetters(beforeText.substring(initialCursorPos, beforeText.length()));
                    mCurrencyValueField.removeTextChangedListener(this);
                    mCurrencyValueField.setText(s.toString());
                    mCurrencyValueField.setSelection(s.length());
                    mCurrencyValueField.addTextChangedListener(this);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }

                private int getNewCursorPosition(int countAfterCursorPos, String text){
                    int position = 0;
                    int c = countAfterCursorPos;
                    String reveredString = new StringBuilder(text).reverse().toString();
                    for(int i = 0; i < reveredString.length(); i++) {
                        if(c == 0)
                            break;

                        c--;
                        position++;
                    }

                    return text.length() - position;
                }

                private int getNumberOfLetters(String text){
                    int count = 0;
                    for(int i = 0; i < text.length(); i++)
                        count++;

                    return count;
                }
            });
        }

        public void bind(Currency currency) {
            Picasso.get().load(currency.getImage()).into(mCurrencyImage);

            mCurrencyName.setText(currency.getShortName());
            mCurrencyDesc.setText(currency.getFullName());

            double rate = currency.getRate();
            if(rate == (long) rate)
                mCurrencyValueField.setText(String.format(Locale.getDefault(),
                        "%d",(long) rate));
            else
                mCurrencyValueField.setText(String.format(Locale.getDefault(), "%.3f", rate)
                        .replaceAll("0*$", ""));
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus) return;

            if(mListener == null) return;

            ((EditText) v).setSelection(((EditText) v).getText().length());
            mListener.onClick(getAdapterPosition());
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE)
                handleNewValue();

            mCurrencyValueField.clearFocus();
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        }

        private void handleNewValue() {
            int length = mCurrencyValueField.length();
            double value = length > 0
                    ? Double.parseDouble(mCurrencyValueField.getText().toString())
                    : 0;

            if(mListener != null)
                mListener.onValueChanged(getAdapterPosition(), value);
        }
    }
}
