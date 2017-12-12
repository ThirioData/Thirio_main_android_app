package com.thirio.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.thirio.android.model.Travel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivityFood extends AppCompatActivity {
    private static final String EXTRA_TRAVEL = "EXTRA_TRAVEL";
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;

    public static Intent newInstance(Context context, Travel travel) {
        Intent intent = new Intent(context, InfoActivityFood.class);
        intent.putExtra(EXTRA_TRAVEL, travel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.thiriologotext);

        Travel travel = getIntent().getParcelableExtra(EXTRA_TRAVEL);
        if (travel != null) {
            image.setImageResource(travel.getImage());
            title.setText(travel.getName());
        }
    }

}
