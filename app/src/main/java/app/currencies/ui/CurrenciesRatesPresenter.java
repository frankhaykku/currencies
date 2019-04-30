package app.currencies.ui;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import app.currencies.api.Api;
import app.currencies.api.RestService;
import app.currencies.model.Currency;
import app.currencies.model.RestResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CurrenciesRatesPresenter extends MvpPresenter<CurrenciesRatesView> {

    private String mCurrentCurrencyName = "EUR";
    private Disposable mDisposable;
    private double mAmount = 1.0;

    public CurrenciesRatesPresenter() {
        startRequest();
    }

    private void startRequest() {
        if(mDisposable != null)
            disposeRequest();

        mDisposable = Observable.interval(1000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getCurrencies, this::onError);
    }

    private void getCurrencies(Long aLong) {
        Api api = RestService.getInstance().getApi();

        Observable<RestResponse> observable = api.getRatesByName(mCurrentCurrencyName);
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createCurrencyList, Throwable::printStackTrace);
    }

    private void createCurrencyList(RestResponse response) {
        List<Currency> mCurrencies = new ArrayList<>();
        mCurrencies.add(new Currency(response.getName(), mAmount));

        for(Map.Entry<String, Double> value : response.getRates().entrySet()) {
            mCurrencies.add(new Currency(value.getKey(), value.getValue() * mAmount));
        }

        getViewState().update(mCurrencies);
    }

    public void setCurrentCurrencyName(String name) { mCurrentCurrencyName = name; }

    private void onError(Throwable throwable) {
        Log.e("Error", "Something went wrong in Observable Timer");
    }

    public void resumeRequest() {
        if(mDisposable.isDisposed())
            startRequest();
    }

    public void disposeRequest() {
        if(!mDisposable.isDisposed())
            mDisposable.dispose();
    }

    public void setRate(double value) {
        mAmount = value;
    }
}
