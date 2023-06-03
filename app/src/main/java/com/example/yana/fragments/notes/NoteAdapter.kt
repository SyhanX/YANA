package com.example.yana.fragments.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.data.Note
import com.example.yana.databinding.NoteCardBinding

class NoteAdapter :
    RecyclerView.Adapter<NoteAdapter.CardViewHolder>() {

    private var noteList = emptyList<Note>()
    private lateinit var binding: NoteCardBinding


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        binding = NoteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = noteList[position]
        with (binding) {
            noteTitle.text = currentItem.title
            noteContent.text = currentItem.content
            noteCard.setOnClickListener {
                val action =
                    NoteListFragmentDirections.actionNoteListToNoteUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            noteCard.setOnLongClickListener {
                return@setOnLongClickListener true
            }
        }


    }

    override fun getItemCount() = noteList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }
}