package com.example.yana.fragments.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yana.databinding.FragmentNoteUpdateBinding

class NoteUpdateFragment : Fragment() {
    private lateinit var binding: FragmentNoteUpdateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteUpdateBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}