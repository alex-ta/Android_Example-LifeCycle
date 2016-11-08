package pubfinder.pubfinder.asynch;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import pubfinder.pub.showpubs.StaticListViewAdapter;
import pubfinder.pubfinder.GlobalApplication;
import pubfinder.pubfinder.db.DaoSession;
import pubfinder.pubfinder.db.Pub;
import pubfinder.pubfinder.db.PubDao;



/**
 * Created by User on 08.11.2016.
 */

public abstract class Asynch<E> extends AsyncTask<E,E,List<E>>{



    protected Activity activity;
    private ProgressBar progressBar;

    public Asynch (Activity ctx){
        super();
        this.activity = ctx;
        RelativeLayout layout = new RelativeLayout(ctx);
        progressBar = new ProgressBar(ctx,null,android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar,params);

    }

    public abstract List<E> onExec(E...element);
    public abstract void onPostExec(List<E> elements);


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(List<E> es) {
        super.onPostExecute(es);
        progressBar.setVisibility(View.INVISIBLE);
        onPostExec(es);
    }

    @Override
    protected List<E> doInBackground(E... params) {
        return onExec(params);
    }
}
