package pubfinder.pubfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import pubfinder.pubfinder.asynch.UpdateUI;
import pubfinder.pubfinder.db.DaoSession;
import pubfinder.pubfinder.db.Pub;
// import Max
import android.app.AlertDialog;
import android.widget.EditText;
import android.text.InputType;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private ListView showPub;
    private UpdateUI updateUI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI = new UpdateUI(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.showPub = (ListView) this.findViewById(R.id.show_listview);
        this.showPub.setAdapter(updateUI.getAdapter());

        // Load Data
        //pubAdapter = new DynamicListViewAdapter<Pub> (this, pubs, new DynamicViewGen<Pub>(Pub.class));


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
                builder.setTitle("Add Pub");        // set title for popup

                // Set up the input
                final EditText input = new EditText(MainActivity.this);
                // set texttyp for input
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text;
                        m_Text = input.getText().toString();         // get text from input
                        if(m_Text.isEmpty() == true){
                            // dont add it and send an error
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Error")
                                    .setMessage("The Pub has to have a name!")
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with adding a pub
                                            // open the popupmenu again
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                        else {
                            Pub pub = new Pub(null, m_Text, " ", " ");   // make a new object from string
                            updateUI.execute(pub);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                // create new Pub with the input (m_Text)


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

    @Override
    protected void onPause() {
        super.onPause();
        // save Data here
        // Data is already saved in db
    }

    @Override
    protected void onResume() {
        super.onResume();
        // restore Data here
        updateUI.execute();
    }
}
