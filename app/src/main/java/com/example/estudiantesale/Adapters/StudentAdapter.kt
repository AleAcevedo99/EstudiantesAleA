package com.example.estudiantesale.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.estudiantesale.DetailActivity
import com.example.estudiantesale.EditActivity
import com.example.estudiantesale.Entity.EntityStudent
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.R
import com.example.estudiantesale.Tools.Constants
import com.example.estudiantesale.databinding.ItemStudentBinding
import com.google.android.material.snackbar.Snackbar


class StudentAdapter(val studentList:ArrayList<EntityStudent>, val context: Context, val students:ListStudents): RecyclerView.Adapter<StudentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StudentHolder(inflater.inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.txvFullName.text = "${studentList[position].name} ${studentList[position].lastName}"
        holder.txvGender.text = "${if (studentList[position].gender == 1) "Masculino" else "Femenino"}"
        holder.txvDegree.text = "${studentList[position].degree}"

        holder.btnDelete.setOnClickListener {
            actionDialog(position, it).show()
        }

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java).apply{
                putExtra(Constants.ID, position)
            }
            context.startActivity(intent)
        }

        holder.btnView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply{
                putExtra(Constants.ID, position)
            }

            context.startActivity(intent)
        }

    }

    fun actionDialog(position: Int, view: View): AlertDialog {
        val alert = AlertDialog.Builder(context)
        alert.setTitle("UdelP Estudiantes v0.1")
        alert.setIcon(R.drawable.fenix)
        alert.setMessage("¿Realmente deseas eliminar el estudiante?")

        alert.setPositiveButton("Sí"){_,_ ->
            if(students.delete(position)){
                Toast.makeText(context, "Estudiante eliminado", Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }else{
                Snackbar.make(view, "Error al eliminar", Snackbar.LENGTH_SHORT).show()
            }
        }

        alert.setNegativeButton("No"){_,_ ->

        }

        return  alert.create()
    }

}

class StudentHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemStudentBinding.bind(view)

    val imgLogo = binding.imvLogo
    val txvFullName = binding.txvFullName
    val txvGender = binding.txvGender
    val txvDegree = binding.txvDegree
    val btnDelete = binding.btnDelete
    val btnEdit = binding.btnEdit
    val btnView = binding.btnView

}