package app.currencies.model;

import java.util.Objects;
import app.currencies.CurrenciesInformationHelper;
import app.currencies.adapter.ViewType;

public class Currency implements ViewType {

    private String mShortName;
    private String mFullName;
    private int mImage;
    private Double mRate;

    public Currency(String shortName, Double rate) {
        this.mShortName = shortName;
        this.mRate = rate;

        mFullName = CurrenciesInformationHelper.getDescByShortName(shortName);
        mImage = CurrenciesInformationHelper.getFlagByShortName(shortName);
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        this.mShortName = shortName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = image;
    }

    public Double getRate() {
        return mRate;
    }

    public void setRate(Double rate) {
        this.mRate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(mShortName, currency.mShortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mShortName);
    }

    @Override
    public TYPES getViewType() {
        return TYPES.CURRENCY;
    }
}
