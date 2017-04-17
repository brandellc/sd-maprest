package edu.ufam.icomp.sd.maprest.control;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import edu.ufam.icomp.sd.maprest.model.Country;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by brandell on 16/04/17.
 */

public class Client {
    private static CountriesInterface countriesService;

    public static CountriesInterface getCountriesClient(){
        if (countriesService == null){
            //Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
             //       .create();
            Retrofit restAdapter = new Retrofit.Builder().baseUrl("https://restcountries.eu")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            countriesService = restAdapter.create(CountriesInterface.class);
        }

        return countriesService;
    }
    public interface CountriesInterface{
        @GET("/rest/v1/all/")
        Call<List<Country>> getCountries();

    }
}
