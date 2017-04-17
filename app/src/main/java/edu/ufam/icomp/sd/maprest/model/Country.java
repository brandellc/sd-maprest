package edu.ufam.icomp.sd.maprest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by brandell on 16/04/17.
 */

public class Country extends  RealmObject implements Serializable{
    @SerializedName("numericCode")
    @Expose
    private int id;
    @PrimaryKey
    private String name;
    private String capital;
    private String region;
    private String latlong;

    @Ignore
    private List<String> latlng ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<String>  latlng) {
        this.latlng = latlng;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong){
        this.latlong = latlong;
    }
    public void setLatlong() {
        if (latlng.size() == 2)
            this.latlong = latlng.get(0)+":"+latlng.get(1);
        else
            this.latlong = "0:0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Country){
            Country country = (Country) obj;
            return country.id == this.id;
        }
        return false;
    }
}
