package com.example.softexperttast.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.softexperttast.R;
import com.example.softexperttast.adapters.CarsAdapter;
import com.example.softexperttast.data.models.Datum;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.view {
    private CarsAdapter carsAdapter;
    private RecyclerView rvCars;
    private List<Datum> carsList;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private MainPresenter mainPresenter;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int currentPage = 1 , lastPage =  10 , next;
    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        mainPresenter.start();
        mainPresenter.getCars(currentPage);
    }

    @Override
    public void inityUi() {
        rvCars = findViewById(R.id.cars_recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        carsList = new ArrayList<>();
        carsList.clear();
        carsAdapter = new CarsAdapter(carsList);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this , LinearLayoutManager.VERTICAL , false);
        rvCars.setAdapter(carsAdapter);
        rvCars.setHasFixedSize(false);
        rvCars.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showProgress(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void notifyAdapter(List<Datum> carList) {
        this.carsList.addAll(carList);
        carsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        mainPresenter.setView(MainActivity.this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mainPresenter.setView(null);
        super.onPause();
    }

    @Override
    public void paginate() {
        rvCars.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data
                            if (lastPage != 0 && lastPage > currentPage){
                                currentPage ++;
                                mainPresenter.getCars(currentPage);
                            }
                            loading = true;
                        }
                    }
                }
            }
        });
    }
    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(rvCars , message , Snackbar.LENGTH_INDEFINITE);
        snackbar.setDuration(4000);
        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.purple_500));
        snackbar.setAction("تجاهل", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}