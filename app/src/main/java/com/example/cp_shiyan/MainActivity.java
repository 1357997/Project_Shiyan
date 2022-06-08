package com.example.cp_shiyan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText xEditText, yEditText;
    Button btnInsert, btnShow, btnSave, btnShowExp;
    LineChart lineChart;

    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    LineData lineData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.mpChart);
        xEditText = findViewById(R.id.editTextX);
        yEditText = findViewById(R.id.editTextY);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        btnSave = findViewById(R.id.btnSave);
        btnShowExp = findViewById(R.id.btnShowExp);
        myHelper = new MyHelper (this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        exqInsertBtn();
        exqShowBtn();
        exqSaveBtn();
        exqShowExpBtn();

        lineDataSet.setLineWidth(4);
        lineChart.setBackgroundColor(-1);

    }


    private void exqSaveBtn(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineChart.saveToGallery("image1", 100);

                Context context = getApplicationContext();
                CharSequence text = "Image was saved";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    private void exqShowExpBtn(){
        btnShowExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void exqShowBtn() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineDataSet.setValues(getDataValues());
                lineDataSet.setLabel("Data");
                dataSets.clear();
                dataSets.add(lineDataSet);
                lineData = new LineData(dataSets);
                lineChart.clear();
                lineChart.setData(lineData);
                lineChart.invalidate();
            }
        });
    }

    private void exqInsertBtn() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float xVal = Float.parseFloat(String.valueOf(xEditText.getText()));
                float yVal = Float.parseFloat(String.valueOf(yEditText.getText()));
                myHelper.insertData(xVal,yVal);
            }
        });
    }

    private ArrayList<Entry> getDataValues(){
        ArrayList<Entry> dataVals = new ArrayList<>();
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("myTable", columns, null, null, null, null, null);

        for (int i=0; i<cursor.getCount();i++){
            cursor.moveToNext();
            dataVals.add(new Entry(cursor.getFloat(0), cursor.getFloat(1)));
        }
        return dataVals;
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    //Fires after the OnStop() state
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

}

