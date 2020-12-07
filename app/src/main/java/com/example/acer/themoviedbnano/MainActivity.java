package com.example.acer.themoviedbnano;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    RecyclerView rv;
    String popularurl="https://api.themoviedb.org/3/movie/popular?api_key=2102fede37aef6e84e37cf5bdd0b80aa";
    String ratedurl="https://api.themoviedb.org/3/movie/top_rated?api_key=2102fede37aef6e84e37cf5bdd0b80aa";
    String urlmain;
    ProgressBar pbar;
    ArrayList<MyModelClass> modelclass;
    TextView selectionText;
    public static  final String popularmovies="Popular movies";
    public static final String topratedmovies="top rated movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rcycle);
        pbar=findViewById(R.id.pbar);
        selectionText=findViewById(R.id.textview);
        modelclass=new ArrayList<>();
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(new MyAdapter(this,modelclass));
        urlmain=popularurl;
        selectionText.setText(popularmovies);
        if(checkInternetConnection())
        new MyAsync().execute();
        else Toast.makeText(this, "please check your Internet Connection", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moviemenu,menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.popular:
                urlmain=popularurl;

                selectionText.setText(popularmovies);



                break;




            case R.id.rated:
                urlmain=ratedurl;
                selectionText.setText(topratedmovies);


                break;

        }
        if(checkInternetConnection())
            new MyAsync().execute();
        else Toast.makeText(this, "please check your Internet Connection", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);

    }



    class MyAsync extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            modelclass.clear();
            rv.setAdapter(new MyAdapter(MainActivity.this,modelclass));
            pbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL urlink=new URL(urlmain);
                HttpURLConnection httpURLConnection= (HttpURLConnection) urlink.openConnection();
                httpURLConnection.connect();;
                InputStream inputStream=httpURLConnection.getInputStream();
                Scanner s= new Scanner(inputStream);
                s.useDelimiter("\\A");
                if(s.hasNext())
                {

                    return s.next();

                }
                else return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try {
                if(data!=null) {
                    JSONObject obj = new JSONObject(data);
                    JSONArray dataArray = obj.optJSONArray("results");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject obj1 = dataArray.optJSONObject(i);
                        String title = obj1.optString("original_title");
                        String overview = obj1.optString("overview");
                        String imgurl = obj1.optString("poster_path");
                        String oimgurl = obj1.optString("backdrop_path");
                        String otitle = obj1.optString("original_title");
                        String rating=obj1.optString("vote_average");
                        String releasedate=obj1.optString("release_date");
                        modelclass.add(new MyModelClass(imgurl, title, overview, otitle, oimgurl,rating,releasedate));

                    }
                } else{
                    Toast.makeText(MainActivity.this, " Sorry!..check your  Internet Connection", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            pbar.setVisibility(View.GONE);


        }
    }
    public boolean checkInternetConnection()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
