package com.mvp.demo.pokedroid.di;

import com.mvp.demo.pokedroid.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jatempa on 10/23/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}

