package rekkisoft.trongvu.com.note.application;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rekkisoft.trongvu.com.note.utils.Define;

public class NoteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Define.NavigationKey.REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }

}
