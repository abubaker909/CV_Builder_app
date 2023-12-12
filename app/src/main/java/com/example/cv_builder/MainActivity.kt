package com.example.cv_builder

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cv_builder.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var db: DBHelper
    private lateinit var selectedDate: String

    // Define a view binding instance
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)

        // Access views using view binding
        val spinnerDegree = binding.spinnerDegree
        val radioGroupGender = binding.radioGroupGender
        val buttonDatePicker = binding.buttonDatePicker
        val checkAcademia = binding.checkAcademia
        val checkIndustry = binding.checkIndustry
        val checkBusiness = binding.checkBusiness

        val degrees = arrayOf("BS (IT)", "BS (CS)", "BS (SE)", "BS (DS)")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, degrees)
        spinnerDegree.adapter = adapter

        buttonDatePicker.setOnClickListener {
            showDatePicker()
        }

        val rollNumberText = binding.rollnumber
        val nameText = binding.name
        val cgpaText = binding.Cgpa

        binding.Button.setOnClickListener {
            val rollnumberText = rollNumberText.text.toString()
            val nameText = nameText.text.toString()
            val cgpaText = cgpaText.text.toString().toDoubleOrNull()
            val degree = spinnerDegree.selectedItem.toString()
            val radioButtonId = radioGroupGender.checkedRadioButtonId
            val radioGender = findViewById<RadioButton>(radioButtonId)
            val gender = radioGender.text.toString()

            val interests = mutableListOf<String>()
            if (checkAcademia.isChecked) {
                interests.add("Academia")
            }
            if (checkIndustry.isChecked) {
                interests.add("Industry")
            }
            if (checkBusiness.isChecked) {
                interests.add("Business")
            }

            if (rollnumberText.isEmpty() || nameText.isEmpty() || cgpaText == null) {
                Toast.makeText(this, "Enter all Fields!", Toast.LENGTH_LONG).show()
            } else {
                val savedData = db.saveUserData(
                    rollnumberText, nameText, cgpaText,
                    degree, gender, selectedDate, interests.joinToString(", ")
                )
                if (savedData) {
                    Toast.makeText(this, "Information Saved", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Information Exists!", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.View.setOnClickListener {
            val cursor = db.getUserData()
            val stringBuffer = StringBuffer()
            cursor?.let {
                while (it.moveToNext()) {
                    stringBuffer.append("Roll Number: " + it.getString(0) + "\n")
                    stringBuffer.append("Name: " + it.getString(1) + "\n")
                    stringBuffer.append("CGPA: " + it.getString(2) + "\n")
                    stringBuffer.append("Degree: " + it.getString(3) + "\n")
                    stringBuffer.append("Gender: " + it.getString(4) + "\n")
                    stringBuffer.append("Date of Birth: " + it.getString(5) + "\n")
                    stringBuffer.append("Interests: " + it.getString(6) + "\n\n")
                }
            }
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            builder.setTitle("CV LIST")
            builder.setMessage(stringBuffer.toString())
            builder.show()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Update the selectedDate variable with the chosen date
            selectedDate = "$year-${month + 1}-$dayOfMonth"
            binding.buttonDatePicker.text = selectedDate // Display the selected date on the button
        }, year, month, day)

        datePickerDialog.show()
    }
}








