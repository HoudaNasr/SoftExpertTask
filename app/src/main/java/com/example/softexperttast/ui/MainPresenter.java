package com.example.softexperttast.ui;

import com.example.softexperttast.data.api.ApiClient;
import com.example.softexperttast.data.api.ApiService;
import com.example.softexperttast.data.models.Cars;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.presenter {
    ApiService apiService;
    MainContract.view view;

    public MainPresenter(MainContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void start() {
       view.inityUi();
       view.paginate();
    }

    @Override
    public void setView(MainContract.view view) {
        this.view = view;
    }

    @Override
    public void getCars(int page) {
        if (view != null){
            view.showProgress(true);
        }
        Call<Cars> carsCall = apiService.getCars(page);
        carsCall.enqueue(new Callback<Cars>() {
            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {
                if (view != null) {
                    view.showProgress(false);
                }
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                            if (view != null) {
                                if (response.body() != null && !response.body().getData().isEmpty()){
                                    view.notifyAdapter(response.body().getData());
                                }

                            }
                        } else if (response.body().getStatus() == 0){
                            if (view != null) {
                                view.showError(response.body().getError().getError());
                            }
                        }
                    }
                else {
                    view.showError(response.errorBody().toString());
                }
                }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {
                if (view != null) {
                    view.showError(t.getMessage());
                    view.showProgress(false);
                }
            }
        });
    }
}
