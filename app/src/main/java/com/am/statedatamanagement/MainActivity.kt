package com.am.statedatamanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.Window

import android.os.Build
import com.am.statedatamanagement.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val w = window
//            w.setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )
//        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listener()
    }

    private fun listener() {
        binding.btnNewAccount.setOnClickListener {
            startActivity(
                SignUpActivity.newInent(this@MainActivity)
            )
        }
    }
}