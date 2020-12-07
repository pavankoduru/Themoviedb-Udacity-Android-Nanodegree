package com.example.acer.themoviedbnano;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyModelClass> modelclass;
    public MyAdapter(MainActivity mainActivity, ArrayList<MyModelClass> modelclass) {
        context=mainActivity;
        this.modelclass=modelclass;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.adapterdesign,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        MyModelClass model=modelclass.get(i);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+model.getImgurl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.warning).into(myViewHolder.imageView);


        Log.i("image1clicked","image1clicked");
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("imageclicked","imageclicked");

                String[] detail=new String[6];
                detail[0]=modelclass.get(i).getTitle();

                detail[1]=modelclass.get(i).getOverview();
                detail[2]=modelclass.get(i).getOtitle();
                detail[3]=modelclass.get(i).getOimgurl();
                detail[4]=modelclass.get(i).getMreleaseDate();
                detail[5]=modelclass.get(i).getMrating();


                Intent intent=new Intent(context.getApplicationContext(),DetailActivity.class);

                intent.putExtra("mydata",detail);

                context.startActivity(intent);

            }
        });
       


    }

    @Override
    public int getItemCount() {
        return modelclass.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);


        }
    }
}
