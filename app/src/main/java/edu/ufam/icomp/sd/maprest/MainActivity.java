package edu.ufam.icomp.sd.maprest;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.ufam.icomp.sd.maprest.control.Client;
import edu.ufam.icomp.sd.maprest.model.Country;
import edu.ufam.icomp.sd.maprest.view.CountriesAdapter;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Realm realm;
    private ListView lstview;
    private CountriesAdapter adapter;
    private Button btnRetrive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstview = (ListView)  findViewById(R.id.countryList);
        btnRetrive = (Button) findViewById(R.id.btnRetrieve);

        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country = adapter.getCountry(position);
                Bundle bundle = new Bundle();
                bundle.putString("name", country.getName());
                bundle.putString("latlong", country.getLatlong());
                Intent intent = new Intent(MainActivity.this, MapsCountryActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        realm =  Realm.getDefaultInstance();

        if(realm.where(Country.class).count() != 0){
            btnRetrive.setEnabled(false);
            RealmResults<Country> countriesRealm = realm.where(Country.class).findAll();
            List<Country> countries = new ArrayList<Country>();
            for (Country country: countriesRealm){
                countries.add(country);
            }
            adapter = new CountriesAdapter(MainActivity.this, countries);
            lstview.setAdapter(adapter);
        }


    }


    public void getData(View view) {
        Client.getCountriesClient().getCountries().enqueue(new Callback<List<Country>>() {
        @Override
        public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    if (response.isSuccessful()) {
                        List<Country> countries = response.body();
                        realm.beginTransaction();
                        for (Country country : countries) {
                            country.setLatlong();
                            realm.copyToRealm(country);
                        }
                        realm.commitTransaction();
                        adapter = new CountriesAdapter(MainActivity.this, countries);
                        lstview.setAdapter(adapter);
                    } else {
                        System.out.println(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            btnRetrive.setEnabled(false);
    }


}
