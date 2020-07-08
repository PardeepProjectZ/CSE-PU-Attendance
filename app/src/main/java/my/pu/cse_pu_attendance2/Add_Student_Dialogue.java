package my.pu.cse_pu_attendance2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Add_Student_Dialogue extends DialogFragment {
    //widgets
    private EditText Student_Name, Student_Roll;
    private Button Done, Cancle;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    String selected_class = "";

    public Add_Student_Dialogue(String selected_class) {
        this.selected_class = selected_class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_student, container, false);

        Cancle = view.findViewById(R.id.studentadd_cancle);
        Done = view.findViewById(R.id.studentadd_done);
        Student_Name = view.findViewById(R.id.add_Student_edittext);
        Student_Roll = view.findViewById(R.id.add_Student_Roll_edittext);


        Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!Student_Name.equals("") && !Student_Roll.equals("")) {
                    //here create a collection of class name thats it
                    final String sn = Student_Name.getText().toString();
                    String sr = Student_Roll.getText().toString();
                    Map<String , Object> snr = new HashMap<>();
                    snr.put("Name" , sn);
                    snr.put("Roll Number" ,sr);

                    //here selected class phejo
                    firebaseFirestore.collection("SEM5").document("Class").collection(selected_class).document(sr).set(snr).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(view.getContext(), "Student "+sn+" Added", Toast.LENGTH_SHORT).show();
                                getDialog().dismiss();
                            }
                        }
                    });
                } else {
                    Toast.makeText(view.getContext(), "Enter Student Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
