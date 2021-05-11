package com.example.notesapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabBtnCreateNote.setBackgroundColor(Color.parseColor(ColourPicker.getcolor()))
        RecyclerView.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter=NotesAdapter(this,this)
        RecyclerView.adapter=adapter
        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->list?.let {
            adapter.updateList(it)
        }
        })
        fabBtnCreateNote.setOnClickListener{
            val intent= Intent(this,create_note::class.java)
            startActivity(intent)
        }
    }
    override fun onItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
    }
}