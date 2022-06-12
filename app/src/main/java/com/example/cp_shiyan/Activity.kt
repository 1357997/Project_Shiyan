package com.example.cp_shiyan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.cp_shiyan.databinding.ActivityMainBinding
import com.example.cp_shiyan.room.RoomRepositoryImpl
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Activity : AppCompatActivity(), CoroutineScope {

    val lineDataSet = LineDataSet(null, null)
    val dataSets = mutableListOf<ILineDataSet>()
    lateinit var lineData: LineData


    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repositoryImpl = RoomRepositoryImpl(this)
        binding.btnInsert.setOnClickListener {
            launch { repositoryImpl.insertItem(binding.editTextItemName.text.toString()) }
        }
        binding.btnShowExp.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        binding.btnShow.setOnClickListener { exqShowBtn(binding.mpChart) }
        binding.mpChart.setBackgroundColor(-1)
        lineDataSet.lineWidth = 4.toFloat()
        saveChart()
    }

    fun saveChart(){
        binding.btnSave.setOnClickListener {
            binding.mpChart.saveToGallery(binding.editTextItemName.text.toString(), 100)
            //binding.mpChart.saveToPath(binding.editTextItemName.text.toString(),"/storage/emmc/DCIM")
        }
    }

    fun exqShowBtn(lineChart: LineChart) {

        val listPoints: MutableList<Entry> = mutableListOf(
            Entry(binding.editTextX1.text.toString().toFloat(),
                binding.editTextY1.text.toString().toFloat()
            ),
            Entry(
                binding.editTextX2.text.toString().toFloat(),
                binding.editTextY2.text.toString().toFloat()
            ),
            Entry(
                binding.editTextX3.text.toString().toFloat(),
                binding.editTextY3.text.toString().toFloat()
            ),
            Entry(
                binding.editTextX4.text.toString().toFloat(),
                binding.editTextY4.text.toString().toFloat()
            ),
            Entry(
                binding.editTextX5.text.toString().toFloat(),
                binding.editTextY5.text.toString().toFloat()
            ),
        )

        lineDataSet.setValues(listPoints)

        lineDataSet.setLabel("Data")
        dataSets.clear()
        dataSets.add(lineDataSet)
        lineData = LineData(dataSets)
        lineChart.clear()
        lineChart.setData(lineData)
        lineChart.invalidate()
    }
}
