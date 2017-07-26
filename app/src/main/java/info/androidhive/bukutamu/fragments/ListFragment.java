package info.androidhive.bukutamu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import info.androidhive.bukutamu.R;
import info.androidhive.bukutamu.adapter.RefreshListAdapter;
import info.androidhive.bukutamu.data.DatabaseHandler;
import info.androidhive.bukutamu.data.Registrasi;


public class ListFragment extends Fragment{

    RefreshListAdapter myCustomAdapter=null;
    ListView listView=null;
    DatabaseHandler db=null;
    List<Registrasi> reg=null;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three, container, false);

        db=new DatabaseHandler(getActivity());
        reg=db.getData();
        myCustomAdapter=new RefreshListAdapter(getActivity(),R.layout.fragment_list, reg);

        listView = (ListView) v.findViewById(R.id.swipe_place_list_view);
        listView.setAdapter(myCustomAdapter);

        return v;
    }
}
