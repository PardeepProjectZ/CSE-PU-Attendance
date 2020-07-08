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
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_Remove_Subject_Dialogue extends DialogFragment {
    //widgets
    private EditText Sub_name;
    private Button Addsub,Removesub,Cancel_bu;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance() ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_remove_subject,container,false);

        Removesub = view.findViewById(R.id.subremove);
        Addsub = view.findViewById(R.id.subadd);
        Sub_name = view.findViewById(R.id.add_Subject_edittext);
Cancel_bu = view.findViewById(R.id.subcancel);

        Cancel_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        Addsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!Sub_name.equals("")){
                    //here create a collection of class name thats it
                  String subadd=  Sub_name.getText().toString();
                  //ab bs yeh data mainactivity me bhejdo
                    ((MainActivity)getActivity()).AddSubject_Dialog(subadd);
                    Toast.makeText(view.getContext(), "Class "+subadd+" Added", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(view.getContext(), "Enter Subject Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Removesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Sub_name.equals("")){
                    //here create a collection of class name thats it
                    String subremove=  Sub_name.getText().toString();
//remove from array list of subject
                    ((MainActivity)getActivity()).RemoveSubject_Dialog(subremove);
                    Toast.makeText(view.getContext(), "Class "+subremove+" Removed", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(view.getContext(), "Enter Subject Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
