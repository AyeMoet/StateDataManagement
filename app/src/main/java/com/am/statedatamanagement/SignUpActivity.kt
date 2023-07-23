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
import com.am.statedatamanagement.databinding.ActivitySignUpBinding
import com.google.firebase.firestore.util.Util
import java.util.*


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var picker: DatePickerDialog
    private var gender: String = ""
    companion object {
        fun newInent(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listener()
    }

    private fun listener() {
        binding.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }

            btnMale.setOnClickListener {
                btnFemale.setBackgroundColor(resources.getColor(R.color.gray_background_color))
                btnMale.setBackgroundResource(R.drawable.border_background)
                gender = btnMale.text.toString()
            }

            btnFemale.setOnClickListener {
                btnMale.setBackgroundColor(resources.getColor(R.color.gray_background_color))
                btnFemale.setBackgroundResource(R.drawable.border_background)
                gender = btnFemale.text.toString()
            }

            btnNewAccount.setOnClickListener {
                if (checkValidation()) {
                    Toast.makeText(this@SignUpActivity, "Account create successfully\n You can move on next step.\n ${etFirstName.text.toString()}\n" +
                            " ${etLastName.text.toString()}\n ${etEmailAddress.text.toString()}\n ${etDob.text.toString()}\n $gender \n ${etNationality.text.toString()} \n ${etCountryOfResident.text.toString()}\n ${et_mobile.text.toString()}",Toast.LENGTH_LONG).show()
                }
            }

            etDob.setOnClickListener {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)
                // date picker dialog
                // date picker dialog
                picker = DatePickerDialog(this@SignUpActivity,
                    { view, year, monthOfYear, dayOfMonth -> etDob.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
                    year,
                    month,
                    day
                )
                picker.show()
            }

            etEmailAddress.setOnClickListener {
                isValidateEmail(etEmailAddress.text.toString())
            }
        }
    }

    private fun isValidateEmail(email: String): Boolean {
        var b = false
        try {
            b = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            if (b == false) {
                binding.etEmailAddress.error = "Email Address is Invalid"
            } else if (b == true) {
                binding.etEmailAddress.error = "Email Address is Valid"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
        return b
    }



    private fun checkValidation(): Boolean {
        binding.apply {
            if (etFirstName.text.length < 0) {
                etFirstName.error = "Please fill First Name"
                return false
            }
            if (etLastName.text.length < 0) {
                etLastName.error = "Please fill last Name"
                return false
            }
            if (etEmailAddress.text.length < 0) {
                etEmailAddress.error = "Please fill Email Address"
                return false
            } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmailAddress.text.toString()).matches()) {
                etEmailAddress.error = "Please check your email address and Please try again."
                return false
            }
            if (etDob.text.length < 0) {
                etDob.error = "Please fill Date of Birth"
                return false
            }
            if (etNationality.text.length < 0) {
                etNationality.error = "Please fill Nationality"
                return false
            }
            if (etCountryOfResident.text.length < 0) {
                etCountryOfResident.error = "Please fill Country Of Resident"
                return false
            }
        }
        return true
    }
}