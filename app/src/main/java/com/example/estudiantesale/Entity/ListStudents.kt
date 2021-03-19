package com.example.estudiantesale.Entity

import android.util.Log
import com.example.estudiantesale.Tools.Constants

class ListStudents {

    fun add(student: EntityStudent): Int{
        var answer: Int = -1
        if(!existsNameFilter(student.name)){
            listStudents.add(student)
            answer = listStudents.size - 1
        }
        return answer
    }

    fun existsName(name:String): Boolean {
        var answer: Boolean = false
        for(element in listStudents){
            if(element.name == name){
                answer = true
                break
            }
        }
        return answer
    }

    fun existsNameFilter(name: String):Boolean{
        return listStudents.filter { it.name == name }.isNotEmpty()
    }

    fun edit(student: EntityStudent, position:Int, originName: String): Int{
        var answer: Int = 0
        if(student.name == originName || !existsNameFilter(student.name)){
            if(position < listStudents.size){
                listStudents[position] = student
                answer = position
            }
        }else{
            answer = -1
        }

        return  answer
    }

    fun delete(position: Int):Boolean{
        var answer: Boolean = false
        if(position < listStudents.size){
            listStudents.removeAt(position)
            answer = true
        }
        return  answer
    }

    fun showListStudents(){
        Log.d(Constants.LOG_TAG, "${listStudents.size}")
        for((index,item) in listStudents.withIndex()){
            Log.d(Constants.LOG_TAG, "$index ${item.name} ${item.degree} ${item.gender}")
        }

    }

    fun getStringArray():Array<String>{
        val answerList = arrayListOf<String>()
        for((index, item) in listStudents.withIndex()){
            answerList.add("${item.name} ${item.lastName} | " +
                    "${item.degree} | " +
                    "${if (item.gender == 1) "Masculino"
                    else if(item.gender == 2) "Femenino"
                    else "Género no seleccionado" }" )
        }
        return answerList.toTypedArray()
    }

    fun getStringArrayEditDelete():Array<String>{
        val answerList = arrayListOf<String>()
        for((index, item) in listStudents.withIndex()){
            answerList.add("${item.name} ${item.lastName} | " +
                    "${if (item.gender == 1) "Masculino"
                    else if(item.gender == 2) "Femenino"
                    else "Género no seleccionado" } | " +
                    "${if(item.financialAssistance) "Con beca" else "Sin beca"}" )
        }
        return answerList.toTypedArray()
    }

    fun getEntityStudentArray():Array<EntityStudent>{
        return listStudents.toTypedArray()
    }

    fun getEntityStudentArrayList():ArrayList<EntityStudent>{
        return listStudents
    }

    fun getStudent(index:Int):EntityStudent{
        return  listStudents[index]
    }

    //Para que la lista persista
    companion object{
        private var listStudents= arrayListOf<EntityStudent>()
    }


}