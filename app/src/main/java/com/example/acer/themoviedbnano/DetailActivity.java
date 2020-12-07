package com.example.acer.themoviedbnano;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView title,overview,originalTitle,releaseDate,rating;
    ImageView backgroundImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        title=findViewById(R.id.detailtext1);
        overview=findViewById(R.id.detailtext2);
        originalTitle=findViewById(R.id.detailtext3);
        backgroundImage=findViewById(R.id.detailimg);
        releaseDate=findViewById(R.id.releasedate);
        rating=findViewById(R.id.rating);

        String[] detail=getIntent().getStringArrayExtra("mydata");
        title.setText(detail[0]);

        overview.setText(detail[1]);
        originalTitle.setText(detail[2]);
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+detail[3]).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.warning).into(backgroundImage);
        releaseDate.append(detail[4]+"***");
        rating.append(detail[5]);





    }



}
