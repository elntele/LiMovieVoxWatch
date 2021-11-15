package com.knowtest.limovievoxwatch.interfaceRest;
import com.knowtest.limovievoxwatch.model.ListMovies;
import com.knowtest.limovievoxwatch.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

    @GET("3/search/movie?api_key=ceec733b41c12cb94975bcbdcdcba3aa&query=SELECT%20%2A%20&page=1")
    Call<ListMovies> recuperarMovies();

    @GET("3/search/movie?api_key=ceec733b41c12cb94975bcbdcdcba3aa&query=SELECT%20%2A%20&page=")
    Call <ListMovies> recuperarMovies(@Query("page")  String page);

    //Call<>



}
