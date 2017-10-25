package com.mvp.demo.pokedroid.di;

import android.app.Application;

/**
 * Created by jatempa on 10/23/17.
 */

public class App extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}

