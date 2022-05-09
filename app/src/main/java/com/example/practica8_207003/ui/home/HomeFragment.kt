package com.example.practica8_207003.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practica8_207003.databinding.FragmentHomeBinding
import androidx.annotation.DrawableRes
import com.example.practica8_207003.*
import com.example.practica8_207003.ui.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    companion object {
        var tasks: ArrayList<Task> = ArrayList<Task>()
        var first = true
       lateinit var adapter: AdaptadorTareas
    }

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

        val gridView: GridView = binding.gridview


        reading()
        adapter = AdaptadorTareas(root.context, tasks)

        gridView.adapter = adapter;

        return root
    }

    fun reading (){
        var preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val gson: Gson = Gson()

        var json = preferencias?.getString("Tasks", null)

        val type = object: TypeToken<ArrayList<Task?>?>(){}.type

        if(json == null){
            tasks = ArrayList<Task>()
            tasks.add(Task("Tarea de Ejemplo", "Lunes", "3:00"))

        }
        else {
            tasks = gson.fromJson(json, type)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}