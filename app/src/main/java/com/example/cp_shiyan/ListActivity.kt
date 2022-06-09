package com.example.cp_shiyan

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import androidx.room.RoomDatabase
import com.example.cp_shiyan.adapter.RvAdapter
import com.example.cp_shiyan.databinding.ActivityListBinding
import com.example.cp_shiyan.room.Dao
import com.example.cp_shiyan.room.RoomRepositoryImpl
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ListActivity : AppCompatActivity(), CoroutineScope {
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // view binding for the activity
    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of Language
//    private lateinit var listItemList: List<ItemListBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val roomRepositoryImpl = RoomRepositoryImpl(this)

        // load data to language list

        // create  layoutManager
        val layoutManager: LayoutManager = LinearLayoutManager(this)

        // pass it to rvLists layoutManager
        binding.rvItems.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        lifecycleScope.launch {
            val rvAdapter = RvAdapter(roomRepositoryImpl.getAllItems())
            // attach adapter to the recycler view
            binding.rvItems.adapter = rvAdapter
        }
    }

    // on destroy of view make
    // the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        _binding = null
    }
}