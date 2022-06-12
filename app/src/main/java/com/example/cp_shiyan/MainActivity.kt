//package com.example.cp_shiyan;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.cp_shiyan.room.Dao;
//import com.example.cp_shiyan.room.RoomRepositoryImpl;
//import com.github.mikephil.charting.charts.Chart;
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//import kotlinx.coroutines.NonCancellable.children
//import java.io.File;
//import java.util.ArrayList;
//
//public class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val lineChart = findViewById<LineChart>(R.id.mpChart)
//        val xEditText = findViewById<EditText>(R.id.editTextX)
//        val yEditText = findViewById<EditText>(R.id.editTextY)
//        val btnInsert = findViewById<Button>(R.id.btnInsert)
//        val btnShow = findViewById<Button>(R.id.btnShow)
//        val btnSave = findViewById<Button>(R.id.btnSave)
//        val btnShowExp = findViewById<Button>(R.id.btnShowExp)
//
//
//        exqInsertBtn()
//        exqShowBtn()
//        exqSaveBtn()
//        exqShowExpBtn()
//        lineDataSet.setLineWidth(4)
//        lineChart.setBackgroundColor(-1)
//
//    }
//
//
//    fun exqSaveBtn (btnSave:Button, lineChart:LineChart) {
//        btnSave.setOnClickListener{
//                lineChart.saveToGallery("image1", 100)
//
//            Toast.makeText(this, "Image was saved", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    fun exqShowExpBtn (btnShowExp:Button){
//        btnShowExp.setOnClickListener{
//
//        }
//    }
//
//
//    /*fun exqInsertBtn (btnInsert:Button){
//        btnInsert.setOnClickListener{
//                float xVal = Float.parseFloat(String.valueOf(xEditText.getText()));
//                float yVal = Float.parseFloat(String.valueOf(yEditText.getText()));
//                myHelper.insertData(xVal,yVal);
//            }
//    }*/
//
//   /* fun getDataValues():List<Entry> {
//        val dataVals = mutableListOf<Entry>()
//        val columns = arrayOf("xValues", "yValues")
//        Cursor cursor = sqLiteDatabase.query("myTable", columns, null, null, null, null, null)
//
//        for (int i=0; i<cursor.getCount();i++){
//            cursor.moveToNext();
//            dataVals.add(new Entry(cursor.getFloat(0), cursor.getFloat(1)));
//        }
//        return dataVals;
//    }*/
//
//}
//
//
