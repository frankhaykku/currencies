package app.currencies.adapter;

public interface ViewType {
    TYPES getViewType();

    enum TYPES {
        EMPTY,
        CURRENCY,
    }
}
