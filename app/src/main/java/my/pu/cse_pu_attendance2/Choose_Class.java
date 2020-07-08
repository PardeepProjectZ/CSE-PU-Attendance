package my.pu.cse_pu_attendance2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Choose_Class extends AppCompatActivity {
    ListView Class_ListView;

    FloatingActionButton Add_Class;
    Intent intent_to_main;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__class);

        Class_ListView = findViewById(R.id.class_list);
        Add_Class = findViewById(R.id.add_Class);
        ArrayList<String> class_List = new ArrayList<>();
        class_List.add("2CE6");
        class_List.add("2CE5");


        intent_to_main = new Intent(this, MainActivity.class);

        ArrayAdapter<String> CarrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, class_List);

        Class_ListView.setAdapter(CarrayAdapter);
        Class_ListView.setOnItemClickListener(listClick);

        addclass();
    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String value = (String) Class_ListView.getItemAtPosition(i);


//            Toast.makeText(Choose_Class.this, ts, Toast.LENGTH_SHORT).show();
            intent_to_main.putExtra("Class_Selected", value);
            Toast.makeText(Choose_Class.this, value, Toast.LENGTH_SHORT).show();
//            intent_to_main.putExtra("T",ts);
            startActivity(intent_to_main);
        }
    };


    public void addclass() {

        sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        String st = sharedPreferences.getString("Pass", "");
        if (st.equals("Teacher")) {
            Add_Class.setVisibility(View.VISIBLE);

        } else {
            Add_Class.setVisibility(View.INVISIBLE);
        }
        Add_Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open a form to fill up the class name and create that collection in DB
                Add_Class_Dialogue add_class_dialogue = new Add_Class_Dialogue();
                add_class_dialogue.show(getSupportFragmentManager(), "add_class_dialog");
            }
        });
    }
}