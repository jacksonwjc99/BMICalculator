package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {
    lateinit var bmiImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bmiImage = findViewById(R.id.imageViewProfile)

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener {
            calcBMI(it)
        }

        findViewById<Button>(R.id.buttonReset).setOnClickListener {
            reset(it)
        }
    }

    private fun calcBMI(view: View){
        val weight = findViewById<EditText>(R.id.editTextWeight).text.toString().toInt()
        val height = findViewById<EditText>(R.id.editTextHeight).text.toString().toInt()

        var bmi =  weight.toDouble() / ((height.toDouble() /100) * (height.toDouble() /100))

        val string: String = getString(R.string.bmi)

        textViewBMI.text = string + " " + String.format("%.1f", bmi).toDouble();

        val drawableResource = when (bmi) {
            in 0.0..18.4 -> R.drawable.under
            in 18.5..24.9 -> R.drawable.normal
            in 25.0..Double.MAX_VALUE -> R.drawable.over
            else -> R.drawable.empty
        }
        bmiImage.setImageResource(drawableResource)
    }

    private fun reset(view: View){
        findViewById<EditText>(R.id.editTextWeight).text = null;
        findViewById<EditText>(R.id.editTextHeight).text = null;

        val string: String = getString(R.string.bmi)

        bmiImage.setImageResource(R.drawable.empty)
        textViewBMI.text = string;
    }
}
