package my.pu.cse_pu_attendance2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class If_teacher_password extends AppCompatActivity {
EditText teacherPass;
Button Done;
SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_if_teacher_password);


        teacherPass = findViewById(R.id.Get_Teacher_Password);
        Done = findViewById(R.id.done_button);

        onclickdone();
    }
    public void onclickdone(){
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teacherPass.getText().toString().equals("T")){
                    Intent tosubject = new Intent(view.getContext() , Choose_Class.class);

//                    tosubject.putExtra("Teacher" , "Teacher");
                    startActivity(tosubject);
                }else{
                    Toast.makeText(If_teacher_password.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}