package com.zelix.biznesplanuzb.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zelix.biznesplanuzb.databinding.ActivitySuccessBinding
import com.zelix.biznesplanuzb.util.MySharedPreference

class SuccessActivity : AppCompatActivity() {
    lateinit var binding: ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {

            val errorDialog = AlertDialog.Builder(binding.root.context)
            errorDialog.setTitle("Biznes Plan Uzb!")
            errorDialog.setMessage("Yangi biznes go'ya kiritishga ishonchingiz komilmi?")
            errorDialog.setNegativeButton("Yo'q") { _, _ -> }
            errorDialog.setNegativeButton("Ha") { _, _ ->

                MySharedPreference.isPaid = false
                MySharedPreference.isLogged = false
                MySharedPreference.isSuccessPaid = false
                startActivity(Intent(this, MainActivity2::class.java))
                finish()

            }
            errorDialog.show()

        }


    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}