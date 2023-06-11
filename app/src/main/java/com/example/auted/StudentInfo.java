package com.example.auted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class StudentInfo extends AppCompatActivity {
    Button addButton,back;
    EditText FirstName, LastName, StudentId, DateOfBirth;
    Button dateButton;
    TextView date;
    RadioGroup mGender;
    RadioButton mGenderOption;
    String strGender;
    private DatePickerDialog datePickerDialog;
    int year;
   int month;
   int dayofmonth;
    Calendar calendar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        back = findViewById(R.id.imageArrowOne);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentInfo.this, welcomScreen.class);
                startActivity(intent);
            }
        });
        StudentId = findViewById(R.id.stdId);
        FirstName = findViewById(R.id.FIRSTNAME);
        LastName = findViewById(R.id.LASTNAME);
        dateButton = findViewById(R.id.date);
        mGender = findViewById(R.id.radio);
        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                mGenderOption = mGender.findViewById(i);
                switch (i) {
                    case R.id.male:
                        strGender = mGenderOption.getText().toString();
                        break;
                    case R.id.female:
                        strGender = mGenderOption.getText().toString();
                        break;
                    default:
                }
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(StudentInfo.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + month + "/" + year);
                                //     date.setText(day+ "/"+(month+1)+"/"+year));
                            }

                        }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });
        addButton = findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
        }

    public void uploadData() {

        String studentid = StudentId.getText().toString();
        String firstname = FirstName.getText().toString();
        String lastname = LastName.getText().toString();
        String dateofbirth = dateButton.getText().toString();


        DataClass dataClass = new DataClass(studentid, firstname, lastname, dateofbirth, strGender);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(studentid)
                .set(dataClass);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        FirebaseDatabase.getInstance().getReference("AUTED").child(studentid).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
           public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(StudentInfo.this, "saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
           }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(StudentInfo.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
       });
    }
}