package com.example.thandiwe.report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity {


    private EditText etName;
    private EditText etSurname;
    private EditText etMark_1;
    private EditText etMark_2;
    private EditText etMark_3;


    private boolean update = false;
   // private boolean delete = false;
    private Student s;
    private long personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        etName =(EditText)findViewById(R.id.etName);
        etSurname =(EditText)findViewById(R.id.etSurname);
        etMark_1 =(EditText)findViewById(R.id.etMark1);
        etMark_2 =(EditText)findViewById(R.id.etMark2);
        etMark_3 =(EditText)findViewById(R.id.etMark3);

        Intent intent = getIntent();

        s =(Student) intent.getSerializableExtra(MainActivity.STUDENT);
        if(s!=null)
        {
            update = true;
            etName.setText(s.getName());
            etSurname.setText(s.getSurname());
           /* etMark_1.setText(s.getMark1());
            etMark_2.setText(s.getMark2());
            etMark_3.setText(s.getMark3());*/
            Button b = (Button)findViewById(R.id.button1);
            b.setText("Update");
        }


    }


    public void addPerson(View view)
    {

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        int mark1 = Integer.parseInt(etMark_1.getText().toString());
        int mark2 = Integer.parseInt(etMark_2.getText().toString());
        int mark3 = Integer.parseInt(etMark_3.getText().toString());


        StudentDatabase sd = new StudentDatabase(this);
        if(update)
        {




            s.setName(name);
            s.setSurname(surname);
            s.setMark1(mark1);
            s.setMark2(mark2);
            s.setMark3(mark3);


            sd.updateStudent(s);
        }

        else
        {

            Student s = new Student();
            s.setName(name);
            s.setSurname(surname);
            s.setMark1(mark1);
            s.setMark2(mark2);
            s.setMark3(mark3);
            sd.addStudent(s);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Back(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
