package com.example.notesapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesAdapter(private val context:Context,private val listener:INotesRVAdapter): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var allNotes = ArrayList<Notes>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_notes, parent, false))
        viewHolder.itemView.imgDelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.itemView.tvTitle.text = allNotes[position].title
        holder.itemView.tvDesc.text = allNotes[position].noteText
        holder.itemView.tvDateTime.text = allNotes[position].dateTime
        holder.itemView.tvSubTitle.text = allNotes[position].subTitle
        holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(ColourPicker.getcolor()))
        holder.itemView.imgShare.setOnClickListener {
                val intent= Intent(Intent.ACTION_SEND)
                intent.type="text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"NOTE TITLE - "+allNotes[position].title+"\n"+"NOTE DESCRIPTION -\n"+allNotes[position].noteText)
                val chooser= Intent.createChooser(intent,"Share this note text using...")
                startActivity(context,chooser,null)

            }
        }


    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Notes>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }


}
interface INotesRVAdapter{
    fun onItemClicked(notes: Notes)
}