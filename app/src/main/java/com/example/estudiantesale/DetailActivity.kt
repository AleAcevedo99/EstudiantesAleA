package com.example.estudiantesale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.Tools.Constants
import com.example.estudiantesale.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val students = ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.txt_detail)

        val index:Int = intent.getIntExtra(Constants.ID, -1)
        if(index != -1){
            val student = students.getStudent(index)

            binding.txvFullName.text = "${student.name} ${student.lastName}"
            binding.txvGender.text = "${if (student.gender == 1) "Masculino" 
                                            else if(student.gender == 2) "Femenino" 
                                            else "género no seleccionado" }"
            binding.txvDegree.text = "${student.degree}"
            binding.txvFinancialAssistence.text = "${if(student.financialAssistance) "Con beca" else "Sin beca"}"
            binding.txvHobbies.text = "Pasatiempos: ${if (student.read) "Leer" else ""}" +
                                                    " ${if (student.travel) "Viajar" else ""}" +
                                                    " ${if (student.sport) "Deportes" else ""} " +
                                                    "${if (!student.read && 
                                                            !student.travel && 
                                                            !student.sport) "Sin pasatiempos" else ""}"

            /*
            binding.btnDelte.setOnClickListener {

            }
            */


        }else{
            Toast.makeText(this@DetailActivity, "Error al cargar la actividad", Toast.LENGTH_SHORT).show()
            finish()
        }

    }



}