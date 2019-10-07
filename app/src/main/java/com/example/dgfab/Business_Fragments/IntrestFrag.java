package com.example.dgfab.Business_Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dgfab.R;


public class IntrestFrag extends Fragment {
TextView New_group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intrest, container, false);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        New_group=view.findViewById(R.id.new_group);

          New_group.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
//                  Intent intent=new Intent(view.getContext(), CreateNewGroup.class);
//                  startActivity(intent);
              }
          });



        super.onViewCreated(view, savedInstanceState);

    }
}
