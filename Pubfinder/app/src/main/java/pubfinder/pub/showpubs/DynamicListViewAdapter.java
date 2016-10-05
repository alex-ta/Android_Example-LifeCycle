package pubfinder.pub.showpubs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

/**
 * Created by User on 05.10.2016.
 */

public class DynamicListViewAdapter<TYPE> extends BaseAdapter {

    List<TYPE> list;
    DynamicViewGen<TYPE> viewGen;
    Context ctx;

    public DynamicListViewAdapter(Context ctx, List<TYPE> data, DynamicViewGen<TYPE> gen){
        this.list = data;
        this.ctx = ctx;
        this.viewGen = gen;
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
        return viewGen.getView(list.get(position), ctx);
    }
}
