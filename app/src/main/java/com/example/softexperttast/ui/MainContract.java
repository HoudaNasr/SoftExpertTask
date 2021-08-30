package com.example.softexperttast.ui;

import com.example.softexperttast.data.models.Datum;

import java.util.List;

public interface MainContract {
    interface view{
        void inityUi();
        void paginate();
        void showError(String error);
        void showProgress(boolean isShow);
        void notifyAdapter(List<Datum> carList);
    }

    interface presenter{
        void start();
        void setView(MainContract.view view);
        void getCars(int page);
    }
}
