package com.example.empowher

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MH : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mh)

        val etLastPeriodDate: EditText = findViewById(R.id.etLastPeriodDate)
        val etCycleLength: EditText = findViewById(R.id.etCycleLength)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)
        val tvResults: TextView = findViewById(R.id.tvResults)
        val tvTip: TextView = findViewById(R.id.tvTip)

        btnCalculate.setOnClickListener {
            val lastPeriodDateStr = etLastPeriodDate.text.toString()
            val cycleLengthStr = etCycleLength.text.toString()

            if (lastPeriodDateStr.isEmpty() || cycleLengthStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    val lastPeriodDate = sdf.parse(lastPeriodDateStr)
                    val cycleLength = cycleLengthStr.toInt()

                    val calendar = Calendar.getInstance()
                    calendar.time = lastPeriodDate

                    // Calculate next period date
                    calendar.add(Calendar.DAY_OF_YEAR, cycleLength+28)
                    val nextPeriodDate = sdf.format(calendar.time)

                    // Calculate ovulation date
                    calendar.time = lastPeriodDate
                    calendar.add(Calendar.DAY_OF_YEAR, cycleLength +14)
                    val ovulationDate = sdf.format(calendar.time)

                    val resultsText = """
                        Next Period Date: $nextPeriodDate
                        Ovulation Date: $ovulationDate
                    """.trimIndent()

                    tvResults.text = resultsText
                    tvResults.visibility = TextView.VISIBLE
                    tvTip.visibility = TextView.VISIBLE
                } catch (e: Exception) {
                    Toast.makeText(this, "Invalid input. Please enter the date in YYYY-MM-DD format.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
