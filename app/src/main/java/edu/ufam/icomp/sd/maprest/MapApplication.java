package edu.ufam.icomp.sd.maprest;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by brandell on 16/04/17.
 */

public class MapApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).
                name(Realm.DEFAULT_REALM_NAME).schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
