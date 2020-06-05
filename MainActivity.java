package com.example.planningyourday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    public CalendarView calendarView;
    public String myDateString;
    public Button button_addTask, button;
    public CheckBox checkB, checkB2, checkB3;
    public Toolbar toolbar;
    public EditText editText, editText2, editText3;
    public static String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonAddTask();
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
    }

    public void addListenerOnButtonAddTask(){
        button_addTask=(Button)findViewById(R.id.button_addTask);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                myDateString = (dayOfMonth+"."+month+"."+year);
                fileName = (dayOfMonth+"_"+month+"_"+year+".txt");
            }
        });
        //открывается вкладка для написания задач на конкретный день
        button_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.planningyourday.AddTasksActivity");
                startActivity(intent);
            }
        });

    }


    public void Read(View view)
    {
        try {
            FileInputStream fileInput = openFileInput(fileName);
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader buffer = new BufferedReader(reader);
            StringBuffer strBuffer = new StringBuffer();
            if(buffer.readLine()!=null){
            editText.setText(buffer.readLine());
            strBuffer.append(buffer.readLine()+"\n");}
            if(buffer.readLine()!=null){
            editText2.setText(buffer.readLine());
            strBuffer.append(buffer.readLine()+"\n");}
            if(buffer.readLine()!=null){
            editText3.setText(buffer.readLine());
            strBuffer.append(buffer.readLine()+"\n");}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

}
