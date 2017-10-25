package com.mvp.demo.pokedroid.presenter;

import com.mvp.demo.pokedroid.model.PokeapiService;
import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jatempa on 10/23/17.
 */

public class PresenterImpl implements Presenter {
    private int offset = 0;
    private boolean readyToLoad = false;
    private PokemonAdapter adapter;
    private PokeapiService service;

    public PresenterImpl(PokemonAdapter adapter, PokeapiService service) {
        this.adapter = adapter;
        this.service = service;
        readyToLoad = true;
    }

    @Override
    public void fetchData(int offset) {
        Observable<PokemonList> observable = getService().getPokemonList(20, offset);
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new PokemonObserver(getAdapter()));
        readyToLoad = true;
    }

    @Override
    public void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems) {
        if (readyToLoad) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                readyToLoad = false;
                offset += 20;
                fetchData(getOffset());
            }
        }
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public PokemonAdapter getAdapter() {
        return adapter;
    }

    @Override
    public PokeapiService getService() {
        return service;
    }
}