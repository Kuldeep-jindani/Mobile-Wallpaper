package project.sau.pro.com.wallpaper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.srx.widget.PullToLoadView;

import project.sau.pro.com.wallpaper.paginator.Paginator;
import project.sau.pro.com.wallpaper.paginator_hot.Paginator_hot;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    PullToLoadView pullToLoadView;


    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);

        pullToLoadView = view.findViewById(R.id.wallpaper_grid_hot);

        new Paginator_hot(getContext(),pullToLoadView);

        AdView adView = view.findViewById(R.id.adView);
        MobileAds.initialize(getContext(),"ca-app-pub-7796828333997958/4152584076");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-7796828333997958/4152584076").build();
        adView.loadAd(adRequest);
        return  view;
    }

}
