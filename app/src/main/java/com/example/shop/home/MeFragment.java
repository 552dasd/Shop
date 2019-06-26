package com.example.shop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.shop.R;
import com.example.shop.adapter.MeGvItemAdapter;
import com.example.shop.enrty.MeGvItem;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends SupportFragment {
    View view;
   GridView gridView;
   List<MeGvItem> list = new ArrayList<>();

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_me, container, false);
        init();
        initData();
        gridView.setAdapter(new MeGvItemAdapter(getContext(),list));
        return view;
    }

    private void initData() {
        list.add(new MeGvItem(R.drawable.payment,"111"));
        list.add(new MeGvItem(R.drawable.a,"111"));
        list.add(new MeGvItem(R.drawable.a,"111"));
        list.add(new MeGvItem(R.drawable.a,"111"));
    }


    public void init(){
        gridView = view.findViewById(R.id.me_gv_list);
    }

}
