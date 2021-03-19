package com.example.estudiantesale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudiantesale.Adapters.StudentAdapter
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.databinding.ActivityFormBinding
import com.example.estudiantesale.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private var students = ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.txt_recycler)

        loadStudents()
    }

    fun loadStudents(){
        var list = students.getEntityStudentArrayList()

        val adapter = StudentAdapter(list, this@RecyclerActivity, students)

        val linearLayout = LinearLayoutManager(this@RecyclerActivity, LinearLayoutManager.VERTICAL,
                false)
        binding.rwsStudents.layoutManager = linearLayout
        binding.rwsStudents.adapter = adapter

    }

    override fun onRestart() {
        super.onRestart()
        loadStudents()
    }
}