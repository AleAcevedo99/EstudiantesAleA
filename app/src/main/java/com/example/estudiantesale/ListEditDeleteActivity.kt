package com.example.estudiantesale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.Tools.Constants
import com.example.estudiantesale.databinding.ActivityFormBinding
import com.example.estudiantesale.databinding.ActivityListEditDeleteBinding
import java.text.FieldPosition

class ListEditDeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListEditDeleteBinding
    private val students = ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListEditDeleteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.txt_list_edit_delte)

        loadStudentList()

        binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View,
                                                    position: Int, id: Long ->
            actionDialog(position).show()

        }
    }

    fun actionDialog(position: Int): AlertDialog {
        val alert = AlertDialog.Builder(this@ListEditDeleteActivity)
        alert.setMessage("¿Qué desea hacer?")

        alert.setPositiveButton("Eliminar"){_,_ ->
            if(students.delete(position)){
                Toast.makeText(this@ListEditDeleteActivity, "Estudiante eliminado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@ListEditDeleteActivity, "Error al eliminar", Toast.LENGTH_SHORT).show()
            }
            loadStudentList()
        }

        alert.setNegativeButton("Editar"){_,_ ->
            val intent = Intent(this@ListEditDeleteActivity, EditActivity::class.java).apply{
                putExtra(Constants.ID, position)
            }
            startActivity(intent)
        }
        return  alert.create()
    }

    override fun onRestart() {
        super.onRestart()
        loadStudentList()
    }

    fun loadStudentList(){
        val adapter = ArrayAdapter<String>(this@ListEditDeleteActivity,
                android.R.layout.simple_list_item_1, students.getStringArrayEditDelete())

        binding.ltvStudents.adapter = adapter

    }
}