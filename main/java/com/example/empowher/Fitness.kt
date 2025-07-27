package com.example.empowher

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Fitness: AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness)

        // BMI Calculator
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val btnCalculateBmi = findViewById<Button>(R.id.btnCalculateBmi)
        val tvBmiResult = findViewById<TextView>(R.id.tvBmiResult)

        btnCalculateBmi.setOnClickListener {
            val heightCm = etHeight.text.toString().toDoubleOrNull()
            val weightKg = etWeight.text.toString().toDoubleOrNull()

            if (heightCm != null && weightKg != null) {
                val heightM = heightCm / 100
                val bmi = weightKg / (heightM * heightM)
                tvBmiResult.text = String.format("BMI: %.2f", bmi)
                // Display a toast message based on the BMI value
                val bmiMessage = when {
                    bmi < 18.5 -> "Underweight"
                    bmi in 18.5..24.9 -> "Normal weight"
                    bmi in 25.0..29.9 -> "Overweight"
                    else -> "Obesity"
                }
                Toast.makeText(this, bmiMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show()
            }
        }



        // Fitness and Diet Plans
        val cbPaleo = findViewById<CheckBox>(R.id.rbPaleo)
        val cbKeto = this.findViewById<CheckBox>(R.id.rbKeto)
        val cbLowCarb = findViewById<CheckBox>(R.id.rbLowCarb)
        val cbWhole30 = findViewById<CheckBox>(R.id.rbWhole30)
        val cbVegan = findViewById<CheckBox>(R.id.rbVegan)
        val ivDietPlan = findViewById<ImageView>(R.id.ivDietPlan)

        val dietImages = mapOf(
            R.id.rbPaleo to R.drawable.paleo,
            R.id.rbKeto to R.drawable.keto,
            R.id.rbLowCarb to R.drawable.low_carb,
            R.id.rbWhole30 to R.drawable.whole30,
            R.id.rbVegan to R.drawable.vegan
        )

        val checkboxes = listOf(cbPaleo, cbKeto, cbLowCarb, cbWhole30, cbVegan)

        checkboxes.forEach { checkbox ->
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkboxes.forEach {
                        if (it != checkbox) it.isChecked = false
                    }
                    ivDietPlan.setImageResource(dietImages[checkbox.id] ?: 0)
                    ivDietPlan.visibility = ImageView.VISIBLE
                } else {
                    ivDietPlan.visibility = ImageView.GONE
                }
            }
        } } }
