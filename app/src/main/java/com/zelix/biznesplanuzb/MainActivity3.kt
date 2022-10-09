package com.zelix.biznesplanuzb

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.zelix.biznesplanuzb.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        closeKeyboard(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)




        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val checkedRadioButtonId = binding.radioGroup.checkedRadioButtonId

            val radioButton = findViewById<RadioButton>(checkedRadioButtonId).text.toString()


            binding.next.setOnClickListener {


                val dialog = AlertDialog.Builder(binding.root.context)
                dialog.setTitle("Biznes Plan Uzb")
                dialog.setMessage("$radioButton turi tandandi")
                dialog.setPositiveButton("Ha") { _, _ ->

                    startActivity(
                        Intent(this, MainActivity4::class.java).putExtra(
                            "type",
                            radioButton
                        )
                    )
                    finish()
                }
                dialog.show()


            }
        }


    }
}