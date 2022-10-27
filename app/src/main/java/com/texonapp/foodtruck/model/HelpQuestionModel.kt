package com.texonapp.foodtruck.model

data class HelpQuestionModel(
    val title: String,
    val description: String,
    var activated: Boolean = false
)
