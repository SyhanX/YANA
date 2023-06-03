package com.example.yana.fragments.folders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yana.R
import com.example.yana.databinding.FragmentFolderListBinding

class FolderListFragment : Fragment() {
    private lateinit var binding: FragmentFolderListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFolderListBinding.inflate(layoutInflater, container, false)

        binding.createFolder.setOnClickListener {
            Toast.makeText(requireContext(), R.string.hello_blank_fragment, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}