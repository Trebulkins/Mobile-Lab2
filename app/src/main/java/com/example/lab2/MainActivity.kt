package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.abs
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val aBut: Button = findViewById(R.id.button)
        val tSum: TextView = findViewById(R.id.textSum)
        val tLast: TextView = findViewById(R.id.textLast)
        val tCycle: TextView = findViewById(R.id.textCycle)
        val inpX: TextInputEditText = findViewById(R.id.inputX)
        var aX: Double
        var xSum: Double
        var xLast: Double
        var aStep: Int
        var xCycle: Int

        aBut.setOnClickListener {
            if (inpX.text.toString().toDoubleOrNull() != null){
                xSum = 0.0
                xCycle = 0
                aStep = 3
                aX = inpX.text.toString().toDouble()
                xSum += 1/aX
                xLast = 1.0

                if (abs(aX) > 0.99996) {
                    while (abs(xLast) >= 0.0001) {
                        xCycle += 1
                        xLast = (-1.0).pow(xCycle.toDouble()) / (aStep * (aX.pow(aStep.toDouble())))
                        xSum += xLast
                        aStep += 2
                    }

                    tSum.text = "Сумма: $xSum"
                    tLast.text = "Последнее слагаемое: ${String.format("%.10f", xLast)}"
                    tCycle.text = "Повторений цикла: $xCycle"
                }
                else Toast.makeText(this, "Введите число большее 0.99996!", LENGTH_SHORT).show()
            }
            else Toast.makeText(this, "Введенная строка не является числом!", LENGTH_SHORT).show()
        }
    }
}