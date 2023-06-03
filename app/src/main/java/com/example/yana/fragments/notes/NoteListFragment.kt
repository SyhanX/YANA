package com.example.yana.fragments.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yana.R
import com.example.yana.data.NoteViewModel
import com.example.yana.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var mNoteViewModel: NoteViewModel
    private val adapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.noteRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mNoteViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
            if (it.isNotEmpty()) {
                binding.noteRecycler.visibility = View.VISIBLE
                binding.noNotesPlaceholder.visibility = View.GONE
            } else {
                binding.noteRecycler.visibility = View.GONE
                binding.noNotesPlaceholder.visibility = View.VISIBLE
            }
        }

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteList_to_noteCreate)
        }

        return binding.root
    }

}