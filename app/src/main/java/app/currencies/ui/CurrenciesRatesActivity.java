package app.currencies.ui;

import app.currencies.R;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import app.currencies.adapter.CurrencyViewHolderDelegate;
import app.currencies.adapter.DelegateAdapter;
import app.currencies.adapter.EmptyViewHolderDelegate;
import app.currencies.adapter.OnClickListener;
import app.currencies.model.Currency;
import app.currencies.adapter.ViewType;


public class CurrenciesRatesActivity extends MvpAppCompatActivity implements CurrenciesRatesView {

    @InjectPresenter
    CurrenciesRatesPresenter mPresenter;
    DelegateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
    }

    private void viewInit() {
        RecyclerView rv = findViewById(R.id.currencies_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new DelegateAdapter()
                .addDelegate(ViewType.TYPES.EMPTY, new EmptyViewHolderDelegate(), null)
                .addDelegate(ViewType.TYPES.CURRENCY, new CurrencyViewHolderDelegate(), listener);
        rv.setAdapter(mAdapter);

        mAdapter.enableEmptyView(true);
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(int position) {
            mPresenter.disposeRequest();
            onCurrencyValueClick(position);
        }

        @Override
        public void onValueChanged(int position, double value) {
            mPresenter.setRate(value);
            mPresenter.resumeRequest();
        }
    };

    private void onCurrencyValueClick(int position) {
        Currency currency = mAdapter.getItem(position);
        mPresenter.setCurrentCurrencyName(currency.getShortName());
        mAdapter.moveItemToHead(position);
    }

    @Override
    public void update(List<Currency> currencies) {
        if(mAdapter.isEmptyViewInit())
            mAdapter.enableEmptyView(false);

        for(Currency currency : currencies)
            mAdapter.addItem(currency);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resumeRequest();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.disposeRequest();
    }
}
