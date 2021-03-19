package com.example.estudiantesale

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.example.estudiantesale.Entity.EntityStudent
import com.example.estudiantesale.Entity.ListStudents
import com.example.estudiantesale.Tools.Constants
import com.example.estudiantesale.databinding.ActivityDetailBinding
import com.example.estudiantesale.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private var students = ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.txt_home)

        binding.edtDate.setOnClickListener {
            var y = 2021
            var m = 0
            var d = 16
            var dpd = DatePickerDialog(this@FormActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                }, y, m, d)
            dpd.show()
        }

        binding.btnOk.setOnClickListener{
            val student = EntityStudent()

            if(binding.edtName.text.trim().isNotEmpty() && binding.edtLastName.text.trim().isNotEmpty()
                    && binding.rgdDegree.checkedRadioButtonId != -1 && binding.spnGender.selectedItemPosition != 0){
                //Leyendo campo de texto
                student.name = binding.edtName.text.toString()
                student.lastName = binding.edtLastName.text.toString()

                //Leyendo spinner
                student.gender = binding.spnGender.selectedItemPosition
                //val genderText: String = binding.spnGender.selectedItem.toString()

                //Leyendo radio button
                when(binding.rgdDegree.checkedRadioButtonId){
                    binding.rdbFinisehdStudies.id -> { student.degree = "titulado" }
                    binding.rdbUnfinishedStudies.id -> { student.degree = "trunco" }
                    binding.rdbUniversityIntern.id -> { student.degree = "pasante" }
                }

                //Leyendo checkbox
                student.sport = binding.ckbSport.isChecked
                student.travel = binding.ckbTravel.isChecked
                student.read = binding.ckbRead.isChecked

                //Leyendo switch
                student.financialAssistance = binding.swtFinancialAssistance.isChecked

                val index = students.add(student)
                if(index >= 0){
                    Toast.makeText(this@FormActivity, "Estudiante guardado", Toast.LENGTH_SHORT).show()
                    cleanForm()
                }else{
                    Snackbar.make(it, "Estudiante no guardado. El nombre no puede repetirse.",
                            Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(it, "El nombre, apellidos, género y nivel de estudios son obligatorios",
                        Snackbar.LENGTH_SHORT).show()
            }



        }
        /*
        binding.spnGender.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@FormActivity, "En evento onNothingSelected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedText = parent?.getItemAtPosition(position)
                Toast.makeText(this@FormActivity, "En evento onItemSelected $position $selectedText", Toast.LENGTH_SHORT).show()
            }

        }

        binding.swtFinancialAssistance.setOnCheckedChangeListener{it, isCheked ->
            val checked = if(isCheked) "On" else "Off"
            Toast.makeText(this@FormActivity, "En evento setOnCheckedChangeListener $checked", Toast.LENGTH_SHORT).show()
        }
        */
    }

    fun cleanForm(){
        binding.edtName.setText("")
        binding.edtLastName.setText("")
        binding.spnGender.setSelection(0)
        binding.rgdDegree.clearCheck()
        binding.ckbSport.isChecked = false
        binding.ckbRead.isChecked = false
        binding.ckbTravel.isChecked = false
        binding.swtFinancialAssistance.isChecked = false
    }

    //Recibe la sección donde va el menu y se indica que menu se va a integrar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Se ejecuta cuando se selecciona algo del menu, recibe el elemento seleccionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmList -> {
                val intent = Intent(this@FormActivity, ListActivity::class.java)
                startActivity(intent)
            }
            R.id.itmListEditDelelte -> {
                val intent = Intent(this@FormActivity, ListEditDeleteActivity::class.java)
                startActivity(intent)
            }
            R.id.itmRecyclerView->{
                val intent = Intent(this@FormActivity, RecyclerActivity::class.java)
                startActivity(intent)
            }
            R.id.itmExit -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}