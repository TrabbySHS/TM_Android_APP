package com.example.kamil.fragmentstest;


import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;


public class Task extends Fragment {

    Button ad;
    ArrayList <TaskModel>k=new ArrayList<TaskModel>();
    ListView listView;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if(AppDatac.getInstance().getTasks()!=null) {
            view = inflater.inflate(R.layout.fragment_task,container,false);
            /*if (AppDatac.getInstance().getCurrentFragment()==1){
                ((manageActivity) getActivity()).setViewPager(1);
            }
            else if(AppDatac.getInstance().getCurrentFragment()==2){
                ((manageActivity) getActivity()).setViewPager(2);
            }*/
            listView = view.findViewById(R.id.listView);
            if (AppDatac.getInstance().getTasks() != null) {


                CArrayListAdapter adapter = new CArrayListAdapter(AppDatac.getInstance().getTasks(), (getActivity()).getApplicationContext());
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AppDatac.getInstance().setCurrentTaskId(i);
                        ((manageActivity) getActivity()).setViewPager(1);
                    }
                });

                listView.setAdapter(adapter);
                ad = view.findViewById(R.id.refresh);
                ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                    }
                });

            } else {
                ad = view.findViewById(R.id.refresh);
                ad.setText("Refresh");
                ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                    }
                });
            }
        }
        else{
            view = inflater.inflate(R.layout.task2,container,false);
        }
        return view;

    }

    public void reset(){
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }


}
