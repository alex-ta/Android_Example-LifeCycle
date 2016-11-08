package pubfinder.pubfinder.asynch;

import android.app.Activity;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

import pubfinder.pub.showpubs.StaticListViewAdapter;
import pubfinder.pubfinder.GlobalApplication;
import pubfinder.pubfinder.db.Pub;
import pubfinder.pubfinder.db.PubDao;

/**
 * Created by User on 08.11.2016.
 */

public class UpdateUI  {

    public UpdateUI(Activity activity) {
        this.dao = ((GlobalApplication) activity.getApplication()).getDaoSession().getPubDao();
        this.adapter = new StaticListViewAdapter(activity, new LinkedList<Pub>());
        this.activity = activity;
    }

    private Activity activity;
    private PubDao dao;
    private StaticListViewAdapter adapter;

    public BaseAdapter getAdapter(){
        return adapter;
    }

    public void execute(Pub...elements){
        new Update(activity,dao).execute(elements);
    }

    class Update extends Asynch<Pub> {
        public Update(Activity ctx, PubDao dao) {
            super(ctx);
            this.dao = dao;
        }

        private PubDao dao;

        @Override
        public List<Pub> onExec(Pub... element) {
            for (Pub p : element) {
                this.dao.insert(p);
            }
            return dao.loadAll();
        }

        @Override
        public void onPostExec(List<Pub> elements) {
            adapter.setList(elements);
            adapter.notifyDataSetChanged();
        }
    }
}
