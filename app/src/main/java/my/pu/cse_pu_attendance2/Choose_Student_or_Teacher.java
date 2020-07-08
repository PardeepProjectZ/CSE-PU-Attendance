package my.pu.cse_pu_attendance2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Choose_Student_or_Teacher extends AppCompatActivity {
CardView StudentCard,TeacherCard;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__student_or__teacher);

        StudentCard = findViewById(R.id.student_card);
        TeacherCard = findViewById(R.id.teacher_card);

        sharedPreferences = getSharedPreferences("MY_PREF" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        onclickCard();
    }

    public void onclickCard(){
        StudentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Choose_Class.class);
                editor.putString("Pass","Teac");
                editor.commit();
                startActivity(intent);
            }
        });

        TeacherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), If_teacher_password.class);
                editor.putString("Pass","Teacher");
                editor.commit();
                startActivity(intent);
            }
        });
    }

}