package com.example.thandiwe.report;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private long studentID;
    String name;
    private List<Student> students;
    private StudentDatabase sd;
    private Student s;


    public static final String STUDENT = "student";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.ListView1);
        sd = new StudentDatabase(this);
        students = sd.getAllStudents();
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
                android.R.layout.simple_list_item_1, students);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {


               s= students.get(position);
                studentID = s.getId();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to ");
                builder.setTitle(s.getName() + " " + s.getSurname());

                builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this,
                                StudentActivity.class);
                        intent.putExtra(STUDENT,s);
                        startActivity(intent);



                    }
                });

                builder.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        Intent intent = new Intent(MainActivity.this,
                                StudentActivity.class);
                        intent.putExtra(STUDENT,s);
                        startActivity(intent);
                        sd.deleteStudent(s.getId());

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

      switch (item.getItemId()){
          case R.id.menu_add:

              Intent i = new Intent(MainActivity.this, StudentActivity.class);

              startActivity(i);
              return true;
          default:

      }

        return super.onOptionsItemSelected(item);
    }
}
