package com.example.kamil.fragmentstest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Kamil Motyl
 */

public class MainActivity extends AppCompatActivity {

    String LOGIN="Kamil";
    String PASSWORD="Motyl";
    Button enter;
    Button btB;
    EditText loginL;
    EditText loginP;
    CheckBox RM;
    TextView k;
    private int cpaa=123;
    Button bt;
    public static final String loginPref = "loginPref" ;
    SharedPreferences loginSPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!AppDatac.getInstance().getState()) {
            setContentView(R.layout.activity_main);
            //login here
            enter = findViewById(R.id.login1);
            loginL = findViewById(R.id.loginL);
            loginP = findViewById(R.id.loginP);
            RM = findViewById(R.id.RM);
            loginSPref = getSharedPreferences(loginPref, Context.MODE_PRIVATE);

            if (loginSPref.getString("LOGIN", null) == null && loginSPref.getString("PASSWORD", null) == null) {
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();

                        if (RM.isChecked()) {
                            if (Objects.equals(loginL.getText().toString(), LOGIN) && Objects.equals(loginP.getText().toString(), PASSWORD)) {
                                AppDatac.getInstance().setLogin(loginL.getText().toString());
                                AppDatac.getInstance().setPassword(loginP.getText().toString());
                                AppDatac.getInstance().setState(true);
                                SharedPreferences.Editor editor = loginSPref.edit();

                                editor.putString("LOGIN", loginL.getText().toString());
                                editor.putString("PASSWORD", loginP.getText().toString());
                                editor.apply();


                                Intent nextActivity = new Intent(MainActivity.this, manageActivity.class);
                                startActivityForResult(nextActivity, cpaa);
                            } else
                                Toast.makeText(context, "Wrong Password1", Toast.LENGTH_SHORT).show();

                        } else {
                            if (Objects.equals(loginL.getText().toString(), LOGIN) && Objects.equals(loginP.getText().toString(), PASSWORD)) {
                                AppDatac.getInstance().setLogin(loginL.getText().toString());
                                AppDatac.getInstance().setPassword(loginP.getText().toString());
                                AppDatac.getInstance().setState(true);
                                Intent nextActivity = new Intent(MainActivity.this, manageActivity.class);
                                startActivityForResult(nextActivity, cpaa);
                            } else
                                Toast.makeText(context, "Wrong Password2", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } else {
                setContentView(R.layout.activity_main2);
                k = findViewById(R.id.textView4);
                AppDatac.getInstance().setLogin(loginSPref.getString("LOGIN", null));
                AppDatac.getInstance().setPassword(loginSPref.getString("PASSWORD", null));
                k.setText(AppDatac.getInstance().getLog());
                bt = (Button) findViewById(R.id.buttonk);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppDatac.getInstance().setPassword("");
                        AppDatac.getInstance().setLogin("");
                        AppDatac.getInstance().setState(false);
                        SharedPreferences.Editor editor = loginSPref.edit();

                        editor.putString("LOGIN", null);
                        editor.putString("PASSWORD", null);
                        editor.apply();
                        Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);

                    }
                });
                btB = findViewById(R.id.button);
                btB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent nextActivity = new Intent(MainActivity.this, manageActivity.class);
                        startActivity(nextActivity);

                    }
                });


            }
        } else {
            loginSPref = getSharedPreferences(loginPref, Context.MODE_PRIVATE);
            AppDatac.getInstance().setState(true);
            if (!AppDatac.getInstance().getState()) {
                setContentView(R.layout.activity_main);
                //login here
                enter = findViewById(R.id.login1);
                loginL = findViewById(R.id.loginL);
                loginP = findViewById(R.id.loginP);
                RM = findViewById(R.id.RM);
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();

                        if (RM.isChecked()) {
                            if (Objects.equals(loginL.getText().toString(), LOGIN) && Objects.equals(loginP.getText().toString(), PASSWORD)) {
                                AppDatac.getInstance().setLogin(loginL.getText().toString());
                                AppDatac.getInstance().setPassword(loginP.getText().toString());
                                AppDatac.getInstance().setState(true);
                                SharedPreferences.Editor editor = loginSPref.edit();

                                editor.putString("LOGIN", loginL.getText().toString());
                                editor.putString("PASSWORD", loginP.getText().toString());
                                editor.apply();
                                Intent nextActivity = new Intent(MainActivity.this, manageActivity.class);
                                startActivityForResult(nextActivity, cpaa);
                            } else
                                Toast.makeText(context, "Wrong Password1", Toast.LENGTH_SHORT).show();

                        } else {
                            if (Objects.equals(loginL.getText().toString(), LOGIN) && Objects.equals(loginP.getText().toString(), PASSWORD)) {
                                AppDatac.getInstance().setLogin(loginL.getText().toString());
                                AppDatac.getInstance().setPassword(loginP.getText().toString());
                                AppDatac.getInstance().setState(true);
                                Intent nextActivity = new Intent(MainActivity.this, manageActivity.class);
                                startActivityForResult(nextActivity, cpaa);
                            } else
                                Toast.makeText(context, "Wrong Password2", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } else {
                setContentView(R.layout.activity_main2);
                k = findViewById(R.id.textView4);
                AppDatac.getInstance().setLogin(loginSPref.getString("LOGIN", null));
                AppDatac.getInstance().setLogin(loginSPref.getString("PASSWORD", null));
                k.setText(AppDatac.getInstance().getLog());
                bt = (Button) findViewById(R.id.buttonk);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppDatac.getInstance().setPassword("");
                        AppDatac.getInstance().setLogin("");
                        AppDatac.getInstance().setState(false);
                        SharedPreferences.Editor editor = loginSPref.edit();

                        editor.putString("LOGIN", null);
                        editor.putString("PASSWORD", null);
                        editor.apply();

                        Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);

                    }
                });
                btB = findViewById(R.id.button);
                btB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent nextActivity = new Intent(MainActivity.this, manageActivity.class);
                        startActivity(nextActivity);

                    }
                });


            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        this.recreate();
    }

}
