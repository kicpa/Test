package kms.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpMap();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    }
*/
    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
           // mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
             //       .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
               // setUpMap();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(35, 128))
                .title("Marker"));
    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng myHome = new LatLng(37, 128);
        //지정한 위치로 카메라를 이동하기 위한 객체
        //CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(myHome, 7);
        //에니메이션 효ㅘ글 주면서 해당 위치로 카메라 이동하기
       // mMap.animateCamera(cu);

        Parser ParserList = new Parser();
        ArrayList<MapList> wtlistAll = ParserList.weatherList();


        //각 구장들의 좌표를 찍어줌

        mMap.addMarker(new MarkerOptions().position(new LatLng(37.5305809, 126.8810)).title("목동구장").snippet("온도 : " + wtlistAll.get(2).getTemper() + " 습도 : " + wtlistAll.get(2).getHumi() + " 날씨 : " + wtlistAll.get(2).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.5106812, 127.0720398)).title("잠실구장").snippet("온도 : " + wtlistAll.get(1).getTemper() + " 습도 : " + wtlistAll.get(1).getHumi() + " 날씨 : " + wtlistAll.get(1).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.4368, 126.6932)).title("문학구장").snippet("온도 : " + wtlistAll.get(9).getTemper() + " 습도 : " + wtlistAll.get(9).getHumi() + " 날씨 : " + wtlistAll.get(9).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.300318, 127.009529)).title("수원구장").snippet("온도 : " + wtlistAll.get(5).getTemper() + " 습도 : " + wtlistAll.get(5).getHumi() + " 날씨 : " + wtlistAll.get(5).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(36.3170557, 127.42915)).title("대전구장").snippet("온도 : " + wtlistAll.get(8).getTemper() + " 습도 : " + wtlistAll.get(8).getHumi() + " 날씨 : " + wtlistAll.get(8).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(35.1682592, 126.8884114)).title("광주기아챔피언스필드").snippet("온도 : " + wtlistAll.get(3).getTemper() + "습도 : " + wtlistAll.get(3).getHumi() + " 날씨 : " + wtlistAll.get(3).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(35.8816982, 128.5856)).title("대구구장").snippet("온도 : " + wtlistAll.get(6).getTemper() + " 습도 : " + wtlistAll.get(6).getHumi() + " 날씨 : " + wtlistAll.get(6).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(35.1939975, 129.0619248)).title("사직구장").snippet("온도 : " + wtlistAll.get(4).getTemper() + " 습도 : " + wtlistAll.get(4).getHumi() + " 날씨 : " + wtlistAll.get(4).getCloud()));
        mMap.addMarker(new MarkerOptions().position(new LatLng(35.220332, 128.5808975)).title("마산구장").snippet("온도 : " + wtlistAll.get(7).getTemper() + " 습도 : " + wtlistAll.get(7).getHumi() + " 날씨 : " + wtlistAll.get(7).getCloud()));


        //addMarker(mMap,37.5305809,126.8810,"목동구장","온도: ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");


    }

    public static MapsFragment newInstance(int sectionNumber) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putInt("3", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MapsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_fragment, container, false);
        setUpMap();
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt("3"));
    }



}
