package com.example.estudiantesale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.Tools.Constants
import com.example.estudiantesale.databinding.ActivityFormBinding
import com.example.estudiantesale.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val students = ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.txt_list)

        //Contexto, layout, contenido
        val adapter = ArrayAdapter<String>(this@ListActivity,
                android.R.layout.simple_list_item_1, students.getStringArray())

        binding.ltvStudents.adapter = adapter

        binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View,
                                                    position: Int, id: Long ->

            val selectedItem = adapterView.getItemAtPosition(position)
            //Toast.makeText(this@ListActivity, "$position $id $selectedItem ", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@ListActivity, DetailActivity::class.java).apply{
                putExtra(Constants.ID, position)
            }

            startActivity(intent)

        }
    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@ListActivity,
                android.R.layout.simple_list_item_1, students.getStringArray())

        binding.ltvStudents.adapter = adapter
    }
}