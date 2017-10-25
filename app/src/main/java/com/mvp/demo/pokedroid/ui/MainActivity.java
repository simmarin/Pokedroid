package com.mvp.demo.pokedroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mvp.demo.pokedroid.R;
import com.mvp.demo.pokedroid.di.App;
import com.mvp.demo.pokedroid.presenter.Presenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    Presenter presenter;
    @Inject
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
        // Initialize
        recyclerView.setAdapter(presenter.getAdapter());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    presenter.updateData(
                            layoutManager.getChildCount(),
                            layoutManager.getItemCount(),
                            layoutManager.findFirstVisibleItemPosition()
                    );
                }
            }
        });

        if (savedInstanceState == null) {
            presenter.fetchData(presenter.getOffset());
            Log.d("POKEDROID", "Valor del offset " + presenter.getOffset());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("offset", presenter.getOffset());
        Log.d("POKEDROID", "Valor del offset " + presenter.getOffset());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (presenter != null) {
            presenter.setOffset(savedInstanceState.getInt("offset"));
            Log.d("POKEDROID", "Valor del offset " + presenter.getOffset());
        }
    }
}
