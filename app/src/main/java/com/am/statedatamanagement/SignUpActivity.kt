package com.am.statedatamanagement

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.widget.DatePicker

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import com.google.firebase.firestore.util.Util
import java.util.*


class SignUpActivity : AppCompatActivity() {
    private lateinit var picker: DatePickerDialog
    private var gender: String = ""
    companion object {
        fun newInent(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        listener()
    }

    private fun listener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }

        btn_male.setOnClickListener {
            btn_female.setBackgroundColor(resources.getColor(R.color.gray_background_color))
            btn_male.setBackgroundResource(R.drawable.border_background)
            gender = btn_male.text.toString()
        }

        btn_female.setOnClickListener {
            btn_male.setBackgroundColor(resources.getColor(R.color.gray_background_color))
            btn_female.setBackgroundResource(R.drawable.border_background)
            gender = btn_female.text.toString()
        }

        btn_new_account.setOnClickListener {
            if (checkValidation()) {
                Toast.makeText(this, "Account create successfully\n You can move on next step.\n ${et_firstName.text.toString()}\n" +
                        " ${et_lastName.text.toString()}\n ${et_emailAddress.text.toString()}\n ${et_dob.text.toString()}\n $gender \n ${et_nationality.text.toString()} \n ${et_countryOfResident.text.toString()}\n ${et_mobile.text.toString()}",Toast.LENGTH_LONG).show()
            }
        }

        et_dob.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            // date picker dialog
            // date picker dialog
            picker = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> et_dob.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
                year,
                month,
                day
            )
            picker.show()
        }

        et_emailAddress.setOnClickListener {
            isValidateEmail(et_emailAddress.text.toString())
        }
    }

    private fun isValidateEmail(email: String): Boolean {
        var b = false
        try {
            b = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            if (b == false) {
                et_emailAddress.error = "Email Address is Invalid"
            } else if (b == true) {
                et_emailAddress.error = "Email Address is Valid"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
        return b
    }



    private fun checkValidation(): Boolean {
        if (et_firstName.text.length < 0) {
            et_firstName.error = "Please fill First Name"
            return false
        }
        if (et_lastName.text.length < 0) {
            et_lastName.error = "Please fill last Name"
            return false
        }
        if (et_emailAddress.text.length < 0) {
            et_emailAddress.error = "Please fill Email Address"
            return false
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et_emailAddress.text.toString()).matches()) {
            et_emailAddress.error = "Please check your email address and Please try again."
            return false
        }
        if (et_dob.text.length < 0) {
            et_dob.error = "Please fill Date of Birth"
            return false
        }
        if (et_nationality.text.length < 0) {
            et_nationality.error = "Please fill Nationality"
            return false
        }
        if (et_countryOfResident.text.length < 0) {
            et_countryOfResident.error = "Please fill Country Of Resident"
            return false
        }
        return true
    }
}