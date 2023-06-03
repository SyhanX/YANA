package com.example.yana.fragments.trash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yana.databinding.FragmentTrashListBinding

class TrashListFragment : Fragment() {
    private lateinit var binding: FragmentTrashListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrashListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}