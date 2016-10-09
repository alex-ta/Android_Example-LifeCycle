package pubfinder.pubfinder;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pubfinder.pub.showpubs.DynamicListViewAdapter;
import pubfinder.pub.showpubs.DynamicViewGen;
import pubfinder.pubfinder.db.DaoSession;
import pubfinder.pubfinder.db.Pub;
// import Max
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.EditText;
import android.text.InputType;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private ListView showPub;
    private DaoSession m_DaoSession = null;
    private String m_Text = "Hallo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.showPub = (ListView) this.findViewById(R.id.show_listview);
        // Fill Data

        List<Pub> pubs = new ArrayList<Pub>();
        Pub pub = new Pub(null, "TequilaBar1", "Straße 123", "Blackmusic");      // object pub is created
        pubs.add(pub);
        pub = new Pub(null, "TequilaBar2", "Straße 23", "HipHop");      // object pub is created
        pubs.add(pub);
        pub = new Pub(null, "TequilaBar3", "Straße 13", "Metal");
        pubs.add(pub);
        pub = new Pub(null, "TequilaBar4", "Straße 12", "Rap");
        pubs.add(pub);
        // Load Data
        this.showPub.setAdapter(new DynamicListViewAdapter<Pub>(this.getApplicationContext(), pubs, new DynamicViewGen<Pub>(Pub.class)));

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Pub");

                // Set up the input
                final EditText input = new EditText(MainActivity.this);
                // set texttyp for input
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.m_Text = input.getText().toString();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                // create new Pub with the input (m_Text)
                Pub pub = new Pub(null, m_Text, " ", " ");
                MainActivity.this.m_DaoSession.getPubDao().insert(pub);
            }
            // how and where to update the ListView?

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
