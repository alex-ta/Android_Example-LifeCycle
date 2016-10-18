package pubfinder.pub.showpubs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pubfinder.pubfinder.R;

/**
 * Created by User on 18.10.2016.
 */

public class StaticListViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get Data from Bundle
        String name = savedInstanceState.getString("name");
        String id = savedInstanceState.getString("id");
        String music = savedInstanceState.getString("music");
        String address = savedInstanceState.getString("address");

        View view = inflater.inflate(R.layout.detail_show_pubs, container, false);

        TextView nameText = (TextView) view.findViewById(R.id.detail_show_pub_name);
        nameText.setText(name);
        TextView idText = (TextView) view.findViewById(R.id.detail_show_pub_id);
        idText.setText(id);
        TextView musicText = (TextView) view.findViewById(R.id.detail_show_pub_music);
        musicText.setText(music);
        TextView addressText = (TextView) view.findViewById(R.id.detail_show_pub_address);
        addressText.setText(address);

        // Inflate the layout for this fragment
        return view ;
    }


}
