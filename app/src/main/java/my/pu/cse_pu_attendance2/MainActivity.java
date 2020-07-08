package my.pu.cse_pu_attendance2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView studentRecycler;
    ArrayList<String> student_name = new ArrayList<>();
    ArrayList<String> student_image = new ArrayList<>();
    ArrayList<String> student_rollnum = new ArrayList<>();
    RecyclerView_Adapter recyclerview_adapter;
    FirebaseFirestore firebaseFirestore_getting;
    String Teacher_Selected;
    SharedPreferences sharedPreferences;
    Spinner Class_Spinner;
    ArrayList<String> Class_List;
    Button Add_Subject;
    String Subject_Selected_String = "";
    String class_selected = "";
    FloatingActionButton floating_Add_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentRecycler = findViewById(R.id.recyclerview);
        Class_Spinner = findViewById(R.id.class_spinner);
        floating_Add_student = findViewById(R.id.add_student);
        firebaseFirestore_getting = FirebaseFirestore.getInstance();
        Add_Subject = findViewById(R.id.add_class_open_dialog);
        Class_List = new ArrayList<>();
        ////////////////
        ////////////////////////////////////////////////////////adding subjects
        Class_List.add("Select Class");
        Class_List.add("ADA");
        Class_List.add("Python");
        //////////////////////////////////////////////////////adding roll num
//        student_rollnum.add("11801139");
//        student_rollnum.add("11801139");
//        student_rollnum.add("11801139");
        //////////////////////////////////////////////////////
        ArrayAdapter<String> class_list_adapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Class_List);



        Open_add_RemoveSubjectDialog();

        Class_Spinner.setAdapter(class_list_adapt);
        Class_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).toString().equals("Select Class")) {

                } else {
//                    Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                    Subject_Selected_String = adapterView.getItemAtPosition(i).toString();

                    student_image.clear();
                    student_name.clear();
                    fillingData();

//                   studentRecycler.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Addstudent();
//        Intent intent = getIntent();
//        Teacher_Selected = intent.getStringExtra("TS");


        sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        Teacher_Selected = sharedPreferences.getString("Pass", "");

        if (Teacher_Selected.equals("Teacher")) {
            floating_Add_student.setVisibility(View.VISIBLE);
            Add_Subject.setVisibility(View.VISIBLE);
        }else{
            floating_Add_student.setVisibility(View.INVISIBLE);
            Add_Subject.setVisibility(View.INVISIBLE);
        }

        if (Subject_Selected_String.equals("")) {

        } else {
//    fillingData();
        }




    }


    public void initRecycle() {
        Intent getfromclassact = getIntent();
        class_selected = getfromclassact.getStringExtra("Class_Selected");

        recyclerview_adapter = new RecyclerView_Adapter(this, student_name, student_image, student_rollnum, Teacher_Selected, Subject_Selected_String, class_selected);

        studentRecycler.setAdapter(recyclerview_adapter);
        studentRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void fillingData() {
        Intent getfromclassact = getIntent();

        class_selected = getfromclassact.getStringExtra("Class_Selected");
        Toast.makeText(this, class_selected, Toast.LENGTH_SHORT).show();
        firebaseFirestore_getting.collection("SEM5").document("Class").collection(class_selected).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange d : queryDocumentSnapshots.getDocumentChanges()) {
                    if (d.getType() == DocumentChange.Type.ADDED) {
//                        Toast.makeText(MainActivity.this, d.getDocument().getData().get("Name").toString(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(MainActivity.this, d.getDocument().getData().get("Roll Number").toString(), Toast.LENGTH_SHORT).show();
//                        student_image.clear();
//                        student_name.clear();

                        student_name.add(d.getDocument().getData().get("Name").toString());
////                      student_rollnum.add(d.getDocument().get("ADA").toString());
                        student_rollnum.add(d.getDocument().getData().get("Roll Number").toString());
                        student_image.add("https://images.indianexpress.com/2020/01/sidhu-moose-wala.jpg");
//                        student_image.add("https://images.indianexpress.com/2020/01/sidhu-moose-wala.jpg");
//                        student_image.add("https://images.indianexpress.com/2020/01/sidhu-moose-wala.jpg");


                        initRecycle();

//                        studentRecycler.getAdapter().notifyDataSetChanged();

                    }
                    recyclerview_adapter.notifyDataSetChanged();

                }
            }
        });


//        student_image.add("https://images.indianexpress.com/2020/01/sidhu-moose-wala.jpg");
//        student_name.add("Sidhu Moose Wala");
//        student_rollnum.add("11801139");
//
//        student_image.add("https://images.indianexpress.com/2020/01/sidhu-moose-wala.jpg");
//        student_name.add("Sunny Malton");
//        student_rollnum.add("11801140");
//
//        student_image.add("https://images.indianexpress.com/2020/01/sidhu-moose-wala.jpg");
//        student_name.add("Sidhu Moose Wala");
//        student_rollnum.add("11801141");
//
//        initRecycle();
    }


    //add class
    public void Open_add_RemoveSubjectDialog() {
        Add_Subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_Remove_Subject_Dialogue add_remove_subject_dialogue = new Add_Remove_Subject_Dialogue();
                add_remove_subject_dialogue.show(getSupportFragmentManager(), "Add_Remove_Suject");
            }
        });
    }

    //getting this data from dialog
    public void AddSubject_Dialog(String Subname_addsub) {
        Class_List.add(Subname_addsub);
    }

    public void RemoveSubject_Dialog(String Subname_Remove) {
        Class_List.remove(Subname_Remove);
    }

    public void Addstudent() {

        floating_Add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open dialog
                Add_Student_Dialogue add_student_dialogue = new Add_Student_Dialogue(class_selected);
                add_student_dialogue.show(getSupportFragmentManager(), "AddStudent");
            }
        });
    }

}

