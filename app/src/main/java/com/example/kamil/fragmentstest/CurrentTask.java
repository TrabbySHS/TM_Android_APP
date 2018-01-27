package com.example.kamil.fragmentstest;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class CurrentTask extends Fragment {

    TextView desc;
    TextView titl;
    TextView date;
    TextView priority;
    Button C;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(AppDatac.getInstance().getTasks()!=null) {
            view = inflater.inflate(R.layout.fragment_current_task, container, false);
            int k = AppDatac.getInstance().getCurrentTaskId();

            desc=view.findViewById(R.id.description);
            titl=view.findViewById(R.id.title);
            date=view.findViewById(R.id.date);
            priority=view.findViewById(R.id.priority);


            desc.setText(AppDatac.getInstance().getTasks().get(k).getDescription());
            titl.setText(AppDatac.getInstance().getTasks().get(k).getTitle());
            date.setText("Date:\n"+AppDatac.getInstance().getTasks().get(k).getData());
            priority.setText("Priority:\n"+AppDatac.getInstance().getTasks().get(k).getPriority());


            C = view.findViewById(R.id.res);
            C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reset();
                }
            });
        }
        else{
            view = inflater.inflate(R.layout.task2, container, false);
        }
        return view;
    }
    public void reset(){
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

}