package edu.ufam.icomp.sd.maprest.view;

import java.util.ArrayList;
import java.util.List;

import edu.ufam.icomp.sd.maprest.R;
import edu.ufam.icomp.sd.maprest.model.Country;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by brandell on 16/04/17.
 */

public class CountriesAdapter extends ArrayAdapter<Country> {
    private List<Country> countries;
    private Context context;
    public CountriesAdapter(Context context, List<Country> countries){
        super(context, -1, countries);
        this.countries = countries;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.country_item_list,parent,false);
        TextView txtName = (TextView) view.findViewById(R.id.txtCountryName);
        TextView txtCapital = (TextView) view.findViewById(R.id.txtCapital);
        TextView txtRegion = (TextView) view.findViewById(R.id.txtRegion);

        txtName.setText(countries.get(position).getName());
        txtCapital.setText(countries.get(position).getCapital());
        txtRegion.setText(countries.get(position).getRegion());

        return view;
    }

    public Country getCountry(int position){
        return countries.get(position);
    }
}
