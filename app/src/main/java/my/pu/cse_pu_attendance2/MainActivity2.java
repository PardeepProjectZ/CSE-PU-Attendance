package my.pu.cse_pu_attendance2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity2 extends AppCompatActivity {
    LineChartView lineChartView;
    String sn, sr, ss, sA, cs = "";
    TextView SN,SR,SS,SL;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SN = findViewById(R.id.SN);
        SR = findViewById(R.id.SRN);
        SS = findViewById(R.id.SS);
        SL = findViewById(R.id.SL);

        Intent intent = getIntent();
        sn = intent.getStringExtra("Student_name");
        sr = intent.getStringExtra("Student_Rollnum");
        cs = intent.getStringExtra("class_selected");
        ss = intent.getStringExtra("sub_Selected");

        firebaseFirestore.collection("SEM5").document("Class").collection(cs).document(sr).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    sA = documentSnapshot.getData().get(ss).toString();
                    SL.setText(sA);
                }
            }
        });

        SN.setText(sn);
        SR.setText(sr);
        SS.setText(ss);


//        lineChartView = findViewById(R.id.chart);


//        Chart.setInteractive(boolean isInteractive);
//        Chart.setZoomType(ZoomType zoomType);
//        Chart.setContainerScrollEnabled(boolean isEnabled, ContainerScrollType type);

//        List<PointValue> values = new ArrayList<PointValue>();
//        values.add(new PointValue(0, 2));
//        values.add(new PointValue(1, 4));
//        values.add(new PointValue(2, 3));
//        values.add(new PointValue(3, 4));
//
//        //In most cased you can call data model methods in builder-pattern-like manner.
//        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
//        List<Line> lines = new ArrayList<Line>();
//        lines.add(line);
//
//        LineChartData data = new LineChartData();
//        data.setLines(lines);
//
////        LineChartView chart = new LineChartView(this);
//       lineChartView.setLineChartData(data);
    }
}