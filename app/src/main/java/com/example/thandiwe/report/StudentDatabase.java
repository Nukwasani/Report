package com.example.thandiwe.report;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thandiwe on 2017/07/27.
 */

public class StudentDatabase extends SQLiteOpenHelper{


    private static final String TABLE_STUDENT = "person";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_MARK1 = "mark1";
    private static final String COLUMN_MARK2 = "mark2";
    private static final String COLUMN_MARK3 = "mark3";

    private static final String DATABASE_NAME = "personDatabase";
    private static final int DATABASE_VERSION = 1;

    public StudentDatabase(Context context) {
        super(context, TABLE_STUDENT, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "create table " + TABLE_STUDENT + "(" + COLUMN_ID
                + " integer primary key autoincrement, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_SURNAME + " text not null, "
                + COLUMN_MARK1 + " integer not null, "
                + COLUMN_MARK2 + " integer not null, "
                + COLUMN_MARK3 + " integer not null );";

        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);

    }

    public void addStudent(Student student)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, student.getName());
        cv.put(COLUMN_SURNAME, student.getSurname());
        cv.put(COLUMN_MARK1, student.getMark1());
        cv.put(COLUMN_MARK2, student.getMark2());
        cv.put(COLUMN_MARK3, student.getMark3());

        db.insert(TABLE_STUDENT, null, cv);
        db.close();
    }


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();

        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setSurname(cursor.getString(2));
                student.setMark1(cursor.getInt(3));
                student.setMark2(cursor.getInt(4));
                student.setMark3(cursor.getInt(5));


                students.add(student);
            } while (cursor.moveToNext());
        }

        return students;
    }


    public int updateStudent(Student student) {

        String args[] = { String.valueOf(student.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, student.getName());
        cv.put(COLUMN_SURNAME, student.getSurname());
        cv.put(COLUMN_MARK1, student.getMark1());
        cv.put(COLUMN_MARK2, student.getMark2());
        cv.put(COLUMN_MARK3, student.getMark3());

        return  db.update(TABLE_STUDENT, cv, COLUMN_ID + " = ?", args);


    }

    public void deleteStudent(long id) {
        String args[] = { String.valueOf(id) };

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_STUDENT, COLUMN_ID + " = ?", args);
        db.close();
    }
}
