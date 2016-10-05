package pubfinder.pubfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import pubfinder.pubfinder.db.DaoSession;
import pubfinder.pubfinder.db.Pub;

public class MainActivity extends AppCompatActivity {

    private DaoSession m_DaoSession = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get DB-Session
        m_DaoSession = ((GlobalApplication) getApplication()).getDaoSession();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Max hier bist du
                // Examples to insert into DB
                /*
                Pub pub = new Pub(null, "TequilaBar", "Straße 123", "Blackmusic");      // object pub is created
                m_DaoSession.getPubDao().insert(pub);                                   // pub is inserted into database
                */
            }
        });

        // example to get objects from database and their adresses
        /*
        Pub pub = m_DaoSession.getPubDao().load(12);            // via id
        List<Pub> pubs = m_DaoSession.getPubDao().loadAll();    // get all
        pubs.get(0).getName();                                  // get name
        pubs.get(0).getAddress();                               // ...
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
