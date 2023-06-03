package com.example.yana.fragments.notes

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yana.R
import com.example.yana.data.Note
import com.example.yana.data.NoteViewModel
import com.example.yana.databinding.FragmentNoteUpdateBinding

class NoteUpdateFragment : Fragment() {
    private lateinit var binding: FragmentNoteUpdateBinding
    private val args by navArgs<NoteUpdateFragmentArgs>()
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteUpdateBinding.inflate(layoutInflater, container, false)
        with(binding) {
            updateTitle.setText(args.currentNote.title)
            updateContent.setText(args.currentNote.content)
        }
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
                updateNote()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setPositiveButton("Yes") { _, _ ->
                mNoteViewModel.deleteNote(args.currentNote)
                findNavController().navigate(R.id.action_noteUpdateFragment_to_noteList)
            }
            setNegativeButton("No") { _, _ -> }
            setTitle("Delete note")
            setMessage("Are you sure you want to delete this note?")
            create().show()
        }

    }

    private fun updateNote() {
        val title = binding.updateTitle.text.toString()
        val content = binding.updateContent.text.toString()

        if (inputCheck(title, content)) {
            val updatedNote = Note(args.currentNote.id, title, content)
            mNoteViewModel.updateNote(updatedNote)
            findNavController().navigate(R.id.action_noteUpdateFragment_to_noteList)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(title: String, content: String): Boolean {
        val isTitleEmpty = TextUtils.isEmpty(title)
        val isContentEmpty = TextUtils.isEmpty(content)
        return !(isTitleEmpty && isContentEmpty)
    }

    private fun manageMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete -> {
                        deleteNote()
                        true
                    }
                    android.R.id.home -> {
                        updateNote()
                        true
                    }
                    else -> false
                }

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
