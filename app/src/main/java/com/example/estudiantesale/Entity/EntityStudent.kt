package com.example.estudiantesale.Entity

data class EntityStudent(
    var name:String,
    var lastName:String,
    var gender:Int,
    var degree:String,
    var read:Boolean,
    var sport:Boolean,
    var travel:Boolean,
    var financialAssistance: Boolean){
    constructor():this("", "", 0, "", false, false, false, false)
}