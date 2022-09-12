package com.example.bmicalculator

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
//    private lateinit var sf:SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText=findViewById<EditText>(R.id.setWeight)
        val heightText=findViewById<EditText>(R.id.setHeight)
        val calButton=findViewById<Button>(R.id.btnCalculate)
//        sf=getSharedPreferences("my_sf", MODE_PRIVATE)
//        editor=sf.edit()

        calButton.setOnClickListener {
            val weight=weightText.text.toString()
            val height=heightText.text.toString()

            if (validateInputData(weight, height)){
                val bmi=weight.toFloat()/((height.toFloat()/3.32)*(height.toFloat()/3.32))
                //gwt result into two decimal digits
                val bmi2digits=String.format("%.2f",bmi).toFloat()
                displayResult(bmi2digits)
            }
        }
    }


    private fun validateInputData(weight:String?, height:String?): Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty, Enter Weight", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "height is Empty, Enter height", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex=findViewById<TextView>(R.id.tvIndex)
        val resultDescription=findViewById<TextView>(R.id.tvResult)
        val info=findViewById<TextView>(R.id.tvInfo)

        resultIndex.text=bmi.toString()
        info.text=getString(R.string.normalrange)

        var resultText=""
        var colour=0

        when{
            bmi < 18.50 -> {
                resultText="You are Underweight"
                colour=R.color.underweight
            }
            bmi in 18.50..24.99 -> {
                resultText="You are Normal"
                colour=R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText="You are Overweight"
                colour=R.color.overweight
            }
            bmi > 29.99 -> {
                resultText="You are Obese"
                colour=R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, colour))
        resultDescription.text=resultText
    }
}