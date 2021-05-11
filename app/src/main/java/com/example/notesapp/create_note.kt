package com.example.notesapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_note.*
import java.text.SimpleDateFormat
import java.util.*

class create_note : AppCompatActivity() {
    var currentDate:String?=null

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        colorView.setBackgroundColor(Color.parseColor(ColourPicker.getcolor()))
        val sdf= SimpleDateFormat(" dd/MM/yyyy, hh:mm:ss")
        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        currentDate=sdf.format(Date())

        tvDateTime.text="Date and Time -"+currentDate
        imgBack.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        }
fun submitData(view: View){
    val noteText= etNoteDesc.text.toString()
    val noteTitle= etNoteTitle.text.toString()
    val noteSubtitle= etNoteSubTitle.text.toString()
    if (noteText.isNotEmpty()||noteTitle.isNotEmpty()||noteSubtitle.isNotEmpty()){
        viewModel.insertNote(Notes(noteTitle,noteSubtitle,currentDate,noteText))
        Toast.makeText(this,"$noteTitle Saved", Toast.LENGTH_SHORT).show()
    }
    val intent= Intent(this,MainActivity::class.java)
    startActivity(intent)
}
}