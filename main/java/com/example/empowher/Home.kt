package com.example.empowher

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {

    private val buttonLabels = arrayOf(
        "Fitness", "Menstrual Health", "To-do List", "Education", "Work",
        "SOS", "Album", "Small Business", "Events", "Helpline"
    )

    private val buttonImages = intArrayOf(
        R.drawable.ic_fitness, R.drawable.ic_menstrual_health, R.drawable.ic_todo_list,
        R.drawable.ic_education, R.drawable.ic_work, R.drawable.ic_sos,
        R.drawable.ic_album, R.drawable.ic_small_business, R.drawable.ic_events,
        R.drawable.ic_helpline
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val gridView: GridView = findViewById(R.id.gridView)
        val adapter = Grid(this, buttonLabels, buttonImages)
        gridView.adapter = adapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this, Fitness::class.java))
                1 -> startActivity(Intent(this, MH::class.java))
                2 -> startActivity(Intent(this, TodoList::class.java))
                3 -> startActivity(Intent(this, Edu::class.java))
                4 -> startActivity(Intent(this, Work::class.java))
                5 -> startActivity(Intent(this, Sos::class.java))
                6 -> startActivity(Intent(this, Album::class.java))
                7 -> startActivity(Intent(this, SB::class.java))
                8 -> startActivity(Intent(this, Event::class.java))
                9 -> startActivity(Intent(this, Help::class.java))
            }
        }
    }
}
