package my.pu.cse_pu_attendance2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.Viewholder> {
    Context mContext;
    ArrayList<String> Student_name = new ArrayList<>();
    ArrayList<String> student_image = new ArrayList<>();
    ArrayList<String> student_rollnum = new ArrayList<>();
    String SelectedTorS, Subject_selected;
    String Class_selected = "";
    Integer Student_Sub_attendance;
    String DocumentIdofstudent = "";
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    String stuent_Rollnum = "";
    SharedPreferences
            sharedPreferences_forattendence;

    public RecyclerView_Adapter(Context mContext, ArrayList<String> student_name, ArrayList<String> student_image, ArrayList<String> student_rollnum, String selectedTorS, String subject_selected, String class_selected) {
        this.mContext = mContext;
        this.Student_name = student_name;
        this.student_image = student_image;
        this.student_rollnum = student_rollnum;
        SelectedTorS = selectedTorS;
        Subject_selected = subject_selected;
        Class_selected = class_selected;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blueprint_studentcard, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
//        Glide.with(holder.student_image.getContext())
//                .asBitmap()
//                .load(student_image.get(position))
//                .into(holder.student_image);

        holder.student_name.setText(Student_name.get(position));

        holder.student_rollnum.setText(student_rollnum.get(position));

        if (SelectedTorS.equals("Teacher")) {
            holder.toggle.setVisibility(View.VISIBLE);

            holder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        //Present
                        stuent_Rollnum = student_rollnum.get(position);
                        Toast.makeText(mContext, stuent_Rollnum, Toast.LENGTH_SHORT).show();
                        if (!Class_selected.equals("")) {

                            Getting_Sub_attendance();
//                            add_subject_first();
//                            Putting_Sub_attendance();
                        } else {
                            Toast.makeText(mContext, "Select Class First", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        //Absent
                        When_Abbasent();
                    }
                }
            });
        } else {
            holder.toggle.setVisibility(View.INVISIBLE);

        }

        holder.student_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity2.class);
                intent.putExtra("Student_name" , Student_name.get(position));
                intent.putExtra("Student_Rollnum",student_rollnum.get(position));
                intent.putExtra("class_selected",Class_selected );
                intent.putExtra("sub_Selected",Subject_selected);

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return student_image.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView student_name, student_rollnum;
        ImageView student_image;
        ToggleButton toggle;
        CardView student_card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
//            student_image = itemView.findViewById(R.id.student_img);
            student_name = itemView.findViewById(R.id.student_name);
            toggle = itemView.findViewById(R.id.toggleButton);
            student_card = itemView.findViewById(R.id.studet_cardview_Card);
            student_rollnum = itemView.findViewById(R.id.student_rollnum);
        }
    }





    public void Getting_Sub_attendance() {

        firebaseFirestore.collection("SEM5").document("Class").collection(Class_selected).document(stuent_Rollnum).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (task.isSuccessful()) {


//                        Student_Sub_attendance = Integer.parseInt(documentSnapshot.getData().get(Subject_selected).toString());

                        if (documentSnapshot.exists()){
                            if (documentSnapshot.getData().get(Subject_selected)!=null) {
                                Student_Sub_attendance = Integer.parseInt(documentSnapshot.getData().get(Subject_selected).toString());
                                Toast.makeText(mContext, "snapshot haga", Toast.LENGTH_SHORT).show();
                                Putting_Sub_attendance();
                            }else{
                                Student_Sub_attendance = 0;
                                Toast.makeText(mContext, "snapshot nae haga12", Toast.LENGTH_SHORT).show();

                                Putting_Sub_attendance();
                            }
                        }else{
                            Student_Sub_attendance = 0;
                            Toast.makeText(mContext, "snapshot nae haga", Toast.LENGTH_SHORT).show();

                            Putting_Sub_attendance();
                        }

//                                DocumentIdofstudent = documentSnapshot.getId();

//                        Putting_Sub_attendance();

//                        } else {
//                            Log.w("Tsg", "Error getting documents.", task.getException());
//                        }
                    }
                });
    }

    public void Putting_Sub_attendance() {

        //        Student_Sub_attendance = 0;

        Student_Sub_attendance = Student_Sub_attendance + 1;

        Map<String, Object> m = new HashMap<>();
        m.put(Subject_selected, String.valueOf(Student_Sub_attendance));

        firebaseFirestore.collection("SEM5").document("Class").collection(Class_selected).document(stuent_Rollnum).update(m).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(mContext, "Successfully updated the Attendance of subject" + Subject_selected, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void When_Abbasent() {
        if (Student_Sub_attendance <= 0) {
        } else {
            Student_Sub_attendance = Student_Sub_attendance - 1;



        Map<String, Object> m = new HashMap<>();
        m.put(Subject_selected, String.valueOf(Student_Sub_attendance));

        firebaseFirestore.collection("SEM5").document("Class").collection(Class_selected).document(stuent_Rollnum).update(m).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(mContext, "Successfully updated the Attendance of subject" + Subject_selected, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        }
    }



};
