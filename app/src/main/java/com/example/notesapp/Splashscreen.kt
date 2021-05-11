package com.example.notesapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1500)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@Splashscreen, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        thread.start()
    }
}