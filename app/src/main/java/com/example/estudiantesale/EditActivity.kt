package com.example.estudiantesale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.estudiantesale.Entity.EntityStudent
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.Tools.Constants
import com.example.estudiantesale.databinding.ActivityDetailBinding
import com.example.estudiantesale.databinding.ActivityEditBinding
import com.google.android.material.snackbar.Snackbar

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private val students = ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.txt_edit)
        val position:Int = intent.getIntExtra(Constants.ID, -1)
        if(position != -1){
            val originStudent = students.getStudent(position)
            val student = EntityStudent()

            binding.edtName.setText(originStudent.name)
            binding.edtLastName.setText(originStudent.lastName)
            binding.spnGender.setSelection(originStudent.gender)

            when(originStudent.degree){
                "pasante"->{ binding.rgdDegree.check(binding.rdbUniversityIntern.id) }
                "titulado"->{ binding.rgdDegree.check(binding.rdbFinisehdStudies.id) }
                "trunco"->{binding.rgdDegree.check(binding.rdbUnfinishedStudies.id) }
            }

            binding.ckbSport.isChecked = originStudent.sport
            binding.ckbRead.isChecked = originStudent.read
            binding.ckbTravel.isChecked = originStudent.travel

            binding.swtFinancialAssistance.isChecked = originStudent.financialAssistance

            binding.btnUpdate.setOnClickListener {
                if(binding.edtName.text.trim().isNotEmpty() && binding.edtLastName.text.trim().isNotEmpty()
                        && binding.rgdDegree.checkedRadioButtonId != -1 && binding.spnGender.selectedItemPosition != 0){

                    student.name = binding.edtName.text.toString()
                    student.lastName = binding.edtLastName.text.toString()

                    student.gender = binding.spnGender.selectedItemPosition

                    when(binding.rgdDegree.checkedRadioButtonId){
                        binding.rdbFinisehdStudies.id -> { student.degree = "titulado" }
                        binding.rdbUnfinishedStudies.id -> { student.degree = "trunco" }
                        binding.rdbUniversityIntern.id -> { student.degree = "pasante" }
                    }

                    student.sport = binding.ckbSport.isChecked
                    student.travel = binding.ckbTravel.isChecked
                    student.read = binding.ckbRead.isChecked

                    student.financialAssistance = binding.swtFinancialAssistance.isChecked

                    val index = students.edit(student, position, originStudent.name)
                    if(index >= 0){
                        Toast.makeText(this@EditActivity, "Estudiante actualizado", Toast.LENGTH_SHORT).show()
                        finish()
                    }else if(index == -1){
                        Toast.makeText(this@EditActivity, "El nombre del estudiante no se puede repetir", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@EditActivity, "Estudiante no actualizado", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this@EditActivity, "El nombre, apellidos, género y nivel de estudios " +
                            "son obligatorios", Toast.LENGTH_SHORT).show()
                }
            }

        }else{
            Toast.makeText(this@EditActivity, "Error al cargar la actividad", Toast.LENGTH_SHORT).show()
            finish()
        }

    }


}