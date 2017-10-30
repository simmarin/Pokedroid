package com.mvp.demo.pokedroid.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mvp.demo.pokedroid.R;
import com.mvp.demo.pokedroid.di.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Details2Activity extends AppCompatActivity {
    private PokemonAdapter adapter;

    @BindView(R.id.imaged)
    ImageView imaged;
    @BindView(R.id.named)
    TextView named;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        ButterKnife.bind(this);
        String name="";
        Bundle extras= getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        if (extras !=null){
            imaged.setImageBitmap(bmp );
            name=extras.getString("name");
            named.setText(name);

        }

    }
}

