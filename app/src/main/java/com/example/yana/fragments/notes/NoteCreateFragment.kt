package com.example.yana.fragments.notes

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.yana.R
import com.example.yana.data.Note
import com.example.yana.data.NoteViewModel
import com.example.yana.databinding.FragmentNoteCreateBinding

class NoteCreateFragment : Fragment() {
    private lateinit var binding: FragmentNoteCreateBinding
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteCreateBinding.inflate(layoutInflater, container, false)
        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        manageMenu()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                addNote()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }

    private fun addNote() {
        val noteTitle = binding.addTitle.text.toString()
        val noteContent = binding.addContent.text.toString()
        val noteId = 0

        if (inputCheck(noteTitle, noteContent)) {
            val note = Note(noteId, noteTitle, noteContent)
            mNoteViewModel.addNote(note)
        }
        findNavController().navigate(R.id.action_noteCreate_to_noteList2)
    }

    private fun inputCheck(title: String, content: String): Boolean {
        val isTitleEmpty = TextUtils.isEmpty(title)
        val isContentEmpty = TextUtils.isEmpty(content)
        return !(isTitleEmpty && isContentEmpty)
    }

    private fun manageMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return if (menuItem.itemId == android.R.id.home) {
                    addNote()
                    true
                } else false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}