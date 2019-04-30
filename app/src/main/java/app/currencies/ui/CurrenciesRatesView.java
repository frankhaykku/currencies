package app.currencies.ui;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import app.currencies.model.Currency;

public interface CurrenciesRatesView extends MvpView {
    void update(List<Currency> mCurrencies);
}
