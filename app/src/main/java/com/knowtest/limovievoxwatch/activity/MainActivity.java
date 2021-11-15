package com.knowtest.limovievoxwatch.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.knowtest.limovievoxwatch.R;
import com.knowtest.limovievoxwatch.interfaceRest.DataService;
import com.knowtest.limovievoxwatch.model.ListMovies;
import com.knowtest.limovievoxwatch.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private List <Movie> movies = new ArrayList<>();
    private  ListMovies listMovies;
    private  int index;
    private boolean go = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requisicaoRestaRetrofit();
    }


    private void requisicaoRestaRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrieveListaRetrofit();
    }

    private void retrieveListaRetrofit() {

        DataService service = retrofit.create(DataService.class);
       if (!go) {
           for (int i = 1; i <= 4; i++) {
               index+=1;

               Call<ListMovies> call = service.recuperarMovies(Integer.toString(i));

               call.enqueue(new Callback<ListMovies>() {
                                @Override
                                public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                                    if (response.isSuccessful()) {

                                        listMovies = response.body();
                                        movies.addAll(listMovies.getMovieList());
                                        if (index == 4)
                                            go = true;
                                    }
                                }

                                @Override
                                public void onFailure(Call<ListMovies> call, Throwable t) {
                                    Log.d("Deu falha", t.toString());
                                }
                            }


               );

           }
       }
    }


    @Override
    public void  onStart() {

        super.onStart();
        roteamento();
    }

    public void roteamento(){

        if (go){
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            intent.putParcelableArrayListExtra("array", (ArrayList<Movie>) movies);
            startActivity(intent);

        }

        Intent intent = new Intent(getApplicationContext(), WaitActivity.class);
        startActivity(intent);


    }






}
