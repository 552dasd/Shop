package com.example.shop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shop.carFragmentAdapter.AdapterCarListAdapter;

import com.example.shop.R;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends SupportFragment {
    View view;
    ListView listView;
    public CarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_car, container, false);

        listView = view.findViewById(R.id.pro_information_list);
       // AdapterCarListAdapter adapterCarListAdapter = new AdapterCarListAdapter(getActivity(),);
       // listView.setAdapter(adapterCarListAdapter);
        return view;
    }

}
