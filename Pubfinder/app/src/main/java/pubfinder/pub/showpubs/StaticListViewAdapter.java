package pubfinder.pub.showpubs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pubfinder.pubfinder.R;
import pubfinder.pubfinder.db.Pub;

/**
 * Created by User on 18.10.2016.
 */

public class StaticListViewAdapter extends BaseAdapter {


    private List<Pub> list;
    private LayoutInflater inflater;

    public StaticListViewAdapter(Context ctx, List<Pub> data, DynamicViewGen<Pub> gen){
        this.list = data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= inflater.inflate(R.layout.staticListEntry, null);
        TextView txt = (TextView) convertView.findViewById(R.id.entryName);
        txt.setText(list.get(position).getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
