package com.example.practica8_207003

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.practica8_207003.ui.Task
import com.example.practica8_207003.ui.home.HomeFragment
import com.google.gson.Gson

class AdaptadorTareas: BaseAdapter {
    lateinit var context: Context
    var tasks: ArrayList<Task> = ArrayList<Task>()

    constructor(context: Context, tasks: ArrayList<Task>) {
        this.context = context
        this.tasks = tasks
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(p0: Int): Any {

        return tasks[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflator = LayoutInflater.from(context)
        var vista = inflator.inflate(R.layout.task_view, null)

        var task = tasks[p0]

        val tv_titulo: TextView = vista.findViewById(R.id.tv_title)
        val tv_time: TextView = vista.findViewById(R.id.tv_hours)
        val tv_dia: TextView = vista.findViewById(R.id.tv_days)

        tv_titulo.setText(task.titulo)
        tv_time.setText(task.time)
        tv_dia.setText(task.dia)

        vista.setOnClickListener {
            eliminar(task)
        }
        return vista

    }

    fun eliminar(task: Task) {
        var alertDialog: AlertDialog? = context?.let {
            var builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->
                        HomeFragment.tasks.remove(task)
                        HomeFragment.adapter.notifyDataSetChanged()
                        saveJson()
                        Toast.makeText(context, "Mensaje Borrado", Toast.LENGTH_SHORT).show()
                    })
                setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        //
                    })
            }
            builder?.setMessage("Borrar tarea seleccionada").setTitle("¿Borrar Tarea?")

            builder.create()
        }
        alertDialog?.show()
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