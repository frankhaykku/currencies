package app.currencies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class RestResponse {

    @Expose
    @SerializedName("base")
    private String mName;

    @Expose
    @SerializedName("date")
    private String mDate;

    @Expose
    @SerializedName("rates")
    Map<String, Double> mRates;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public Map<String, Double> getRates() {
        return mRates;
    }

    public void setRates(Map<String, Double> rates) {
        this.mRates = mRates;
    }
}
