package rekkisoft.trongvu.com.note.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NoteApplication extends Application {
    private final String REALM_NAME = "note.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
