package com.example.practica8_207003.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practica8_207003.Carrito
import com.example.practica8_207003.R
import com.example.practica8_207003.Recordatorio
import com.example.practica8_207003.databinding.FragmentHomeBinding
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.annotation.DrawableRes

import com.example.practica8_207003.MainActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val carrito: Carrito
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

            binding.gridview.adapter

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}