package com.example.kamil.fragmentstest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class manageActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    public ArrayList<TaskModel> tm=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        setRepeatingAsyncTask();
        //new DownloadJSON().execute();
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent3=new Intent();
        setResult(manageActivity.RESULT_CANCELED,intent3);
        finish();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Task(), "Tasks");
        adapter.addFragment(new CurrentTask(), "Current Task");
        //adapter.addFragment(new Group(), "Group");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public void Restart(){
        Intent i = new Intent(manageActivity.this, manageActivity.class);
        startActivity(i);
        finish();
    }

    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            DownloadJSON jsonTask = new DownloadJSON();
                            Log.e("REPEATER","Wykonano");
                            jsonTask.execute();
                        } catch (Exception e) {
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 120*1000);

    }


    private class DownloadJSON extends AsyncTask<URL, Integer, ArrayList<TaskModel>> {
        protected ArrayList<TaskModel> doInBackground(URL... urls) {
            HttpHelper HttpHelper=new HttpHelper();
            String JSONString=HttpHelper.GetHttpHelper("https://api.myjson.com/bins/18j4ep");
            ArrayList<TaskModel> taskModelArrayList = null;
            try {
                JSONObject parentObject = new JSONObject(JSONString);
                JSONArray parentArray = parentObject.getJSONArray("Tasks");
                taskModelArrayList=new ArrayList<>();
                Log.e("AT", "Przed For");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    TaskModel taskModel = new TaskModel();
                    taskModel.setId(finalObject.getString("id"));
                    taskModel.setData(finalObject.getString("data"));
                    taskModel.setPriority(finalObject.getString("priority"));
                    taskModel.setDescription(finalObject.getString("description"));
                    taskModel.setTitle(finalObject.getString("title"));

                    taskModelArrayList.add(taskModel);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(AppDatac.getInstance().getTasks()==null) {
                AppDatac.getInstance().setTasks(taskModelArrayList);
                Restart();
            }
            AppDatac.getInstance().setTasks(taskModelArrayList);
            return taskModelArrayList;
        }

        protected void onProgressUpdate(Integer... progress){
        }

        protected void onPostExecute(ArrayList<TaskModel> result) {
            //Restart();
        }
    }

}

