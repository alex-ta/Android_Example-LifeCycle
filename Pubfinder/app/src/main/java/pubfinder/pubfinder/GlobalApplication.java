package pubfinder.pubfinder;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import pubfinder.pubfinder.db.DaoMaster;
import pubfinder.pubfinder.db.DaoSession;

/**
 * Created by Jakobob on 05.10.2016.
 * We use Application to create a Singleton of db, because we need this database in all activities without closing and reopening the db everytime we switch to another activity
 * classes which extends Application and declared in the AndroidManifest.xml starts and closes with the whole application independent from its activities / fragments.
 * So its functionts and variables are accessible from all activities (and all classes with a simple trick)
 */

public class GlobalApplication extends Application {

    private DaoSession m_DaoSession = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public DaoSession getDaoSession(){
        // Singleton to create our Database
        if(m_DaoSession == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "db", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            m_DaoSession = daoMaster.newSession();
        }
        return m_DaoSession;
    }


}
