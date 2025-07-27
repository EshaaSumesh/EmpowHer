package com.example.empowher

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        recyclerView = findViewById(R.id.recyclerView)
        val addButton = findViewById<FloatingActionButton>(R.id.addButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(tasks, ::onEditTask, ::onDeleteTask)
        recyclerView.adapter = adapter

        addButton.setOnClickListener { showAddTaskDialog() }
    }

    private fun showAddTaskDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Task")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("Add") { _, _ ->
            val task = input.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyItemInserted(tasks.size - 1)
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }

    private fun onEditTask(position: Int) {
        val task = tasks[position]
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Task")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setText(task)
        builder.setView(input)

        builder.setPositiveButton("Save") { _, _ ->
            val updatedTask = input.text.toString()
            if (updatedTask.isNotEmpty()) {
                tasks[position] = updatedTask
                adapter.notifyItemChanged(position)
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }

    private fun onDeleteTask(position: Int) {
        tasks.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
