package com.example.todoapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ArrayList<ToDoInfo> toDoList;

    ListView lstview;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoList = new ArrayList<>();
        toDoList.add(new ToDoInfo("#" + String.valueOf(toDoList.size() + 1), "Title1", "Do homework of DSA class", "19/04/2023", false));
        toDoList.add(new ToDoInfo("#" + String.valueOf(toDoList.size() + 1), "Title2", "Do homework of OOP class", "20/04/2023", false));
        lstview = (ListView) findViewById(R.id.listViewToDo);

        adapter = new CustomAdapter(toDoList);
        lstview.setAdapter(adapter);
        registerForContextMenu(lstview);


    }
    ActivityResultLauncher<Intent> launcherNew = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if (data.hasExtra("index")){
                            ToDoInfo selectedToDo = new ToDoInfo("#" + String.valueOf(data.getIntExtra("index", 0) + 1), data.getStringExtra("title"), data.getStringExtra("descp"), data.getStringExtra("date"), data.getBooleanExtra("isDone", false));
                            toDoList.set(data.getIntExtra("index", 0), selectedToDo);
                        }
                        else{
                            ToDoInfo newToDo = new ToDoInfo("#" + String.valueOf(toDoList.size() + 1), data.getStringExtra("title"), data.getStringExtra("descp"), data.getStringExtra("date"), data.getBooleanExtra("isDone", false));
                            toDoList.add(newToDo);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_context_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.optionNew:
                launcherNew.launch(new Intent(MainActivity.this, NewAndEditActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ToDoInfo selectedToDo = toDoList.get(info.position);
        switch (item.getItemId()){
            case R.id.optionEdit:
                Intent intent = new Intent(MainActivity.this, NewAndEditActivity.class);
                intent.putExtra("title", selectedToDo.title);
                intent.putExtra("descp", selectedToDo.description);
                intent.putExtra("date", selectedToDo.date);
                intent.putExtra("isDone", selectedToDo.isDone);
                intent.putExtra("index", info.position);
                launcherNew.launch(intent);
                return true;
            case R.id.optionDelete:
                toDoList.remove(adapter.getItem(info.position));
                adapter.notifyDataSetChanged();
                return true;
            case R.id.optionMarkAsDone:
                toDoList.set(info.position, new ToDoInfo(selectedToDo.ordinalNumber, selectedToDo.title, selectedToDo.description, selectedToDo.date, !selectedToDo.isDone));
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

}