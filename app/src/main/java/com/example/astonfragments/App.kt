package com.example.astonfragments

import android.app.Application
import com.example.astonfragments.contacts.UserRepository

class App: Application() {
    val repository = UserRepository()

    override fun onCreate() {
        super.onCreate()
    }
}