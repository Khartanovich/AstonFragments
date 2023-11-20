package com.example.astonfragments.contacts

data class User(
    val id: Int,
    var name: String,
    var phoneNumber: String,
    var isCheckedForDelete: Boolean
)