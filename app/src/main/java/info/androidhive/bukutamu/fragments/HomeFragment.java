package info.androidhive.bukutamu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import info.androidhive.bukutamu.R;


public class HomeFragment extends Fragment{

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        final VideoView videoView = (VideoView) v.findViewById(R.id.videoView);
        videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");

        videoView.start();
        return v;
    }

}
