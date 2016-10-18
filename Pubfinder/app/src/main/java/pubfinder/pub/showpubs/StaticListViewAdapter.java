package pubfinder.pub.showpubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pubfinder.pubfinder.DetailActivity;
import pubfinder.pubfinder.R;
import pubfinder.pubfinder.db.Pub;

/**
 * Created by User on 18.10.2016.
 */

public class StaticListViewAdapter extends BaseAdapter {


    private List<Pub> list;
    private final Activity activity;
    private LayoutInflater inflater;

    public StaticListViewAdapter(Activity ctx, List<Pub> data){
        this.list = data;
        this.activity = ctx;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView= inflater.inflate(R.layout.staticlistentry, null);
        TextView txt = (TextView) convertView.findViewById(R.id.entryname);
        final Pub pub = list.get(position);
        txt.setText(pub.getName());

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name",pub.getName());
                bundle.putString("id", pub.getId()+"");
                bundle.putString("address", pub.getAddress());
                bundle.putString("music", pub.getMusic());

                Log.d("Data",bundle.toString());

                if(activity.getResources().getBoolean(R.bool.isTablet)){
                    //
                    StaticListViewFragment fragment = new StaticListViewFragment();
                    fragment.setArguments(bundle);
                    android.app.FragmentManager manager = activity.getFragmentManager();
                    manager.beginTransaction().replace(R.id.twoPane, fragment);

                }else{

                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("bundle", bundle);
                    activity.startActivity(intent);

                }
            }
        });

        return convertView;
    }
    public void setList(List<Pub> list){
        this.list = list;
    }
    public List<Pub> getList(){
        return this.list;
    }
}
