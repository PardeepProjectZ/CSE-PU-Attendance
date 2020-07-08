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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class Add_Class_Dialogue extends DialogFragment {
    //widgets
    private EditText Class_name;
    Button Done,Cancle;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance() ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_class,container,false);

        Cancle = view.findViewById(R.id.classadd_cancle);
        Done = view.findViewById(R.id.classadd_done);
        Class_name = view.findViewById(R.id.add_Class_edittext);


        Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!Class_name.equals("")){
                    //here create a collection of class name thats it
                  String cc=  Class_name.getText().toString();
//                    firebaseFirestore_getting.collection("SEM5").document("Class").collection(class_selected).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    firebaseFirestore.collection("SEM5").document("Class").collection(cc).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            Toast.makeText(view.getContext(), "Class Added", Toast.LENGTH_SHORT).show();
                            for (DocumentChange d : queryDocumentSnapshots.getDocumentChanges()) {
                    if (d.getType() == DocumentChange.Type.ADDED) {
                        Toast.makeText(view.getContext(), d.getDocument().getId().toString(), Toast.LENGTH_SHORT).show();
                    }
                            }

                        }
                    });


                }else{
                    Toast.makeText(view.getContext(), "Enter Class Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
