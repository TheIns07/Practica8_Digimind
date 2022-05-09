package com.example.practica8_207003.ui.dashboard

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practica8_207003.R
import com.example.practica8_207003.databinding.FragmentDashboardBinding
import com.example.practica8_207003.ui.Task
import com.example.practica8_207003.ui.home.HomeFragment
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {


        })


        binding.confirmButton.setOnClickListener{
            set_time();
        }

        binding.saveButton.setOnClickListener{
            guardar()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun guardar(){
        var titulo : String = binding.tvTextoRecordatorio.text.toString();
        var tiempo: String = binding.confirmButton.text.toString();
        var dia: String = " "

        if(binding.checkMonday.isChecked)
            dia = "Lunes";
        if(binding.checkThursday.isChecked)
            dia = "Martes";
        if(binding.checkWednesday.isChecked)
            dia = "Miercoles";
        if(binding.checkTuesday.isChecked)
            dia = "Jueves";
        if(binding.checkFriday.isChecked)
            dia = "Viernes";
        if(binding.checkSaturday.isChecked)
            dia = "Sabado";
        if(binding.checkSunday.isChecked)
            dia = "Domingo";

        var tarea = Task(titulo, dia, tiempo)

        HomeFragment.tasks.add(tarea)

        Toast.makeText(context, "Tarea agregada!", Toast.LENGTH_SHORT).show()

    }

    fun set_time(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            binding.confirmButton.text = SimpleDateFormat("MM:mm").format(cal.time)
        }
        TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
        cal.get(Calendar.MINUTE), true).show()
    }

    fun saveJson(){
        var preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val editor = preferencias?.edit()

        val gson: Gson = Gson()

        var json = gson.toJson(HomeFragment.tasks)

        editor?.putString("Tasks", json)
        editor?.apply()
    }
}