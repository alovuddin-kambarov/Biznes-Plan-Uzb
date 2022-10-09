package com.zelix.biznesplanuzb

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.zelix.biznesplanuzb.databinding.ActivityMainBinding
import com.zelix.biznesplanuzb.util.MySharedPreference


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            TapTargetView.showFor(this,  // `this` is an Activity
                TapTarget.forView(
                    findViewById(R.id.info),
                    "Ma'lumot olish uchun bu yerga bosing",
                    ""
                ) // All options below are optional
                    .outerCircleColor(R.color.main_color) // Specify a color for the outer circle
                    .outerCircleAlpha(0.96f) // Specify the alpha amount for the outer circle
                    .targetCircleColor(R.color.blush) // Specify a color for the target circle
                    .titleTextSize(25) // Specify the size (in sp) of the title text
                    .titleTextColor(R.color.white) // Specify the color of the title text
                    .descriptionTextSize(15) // Specify the size (in sp) of the description text
                    .descriptionTextColor(R.color.main_color) // Specify the color of the description text
                    .textColor(R.color.white) // Specify a color for both the title and description text
                    .textTypeface(Typeface.SANS_SERIF) // Specify a typeface for the text
                    .dimColor(R.color.black) // If set, will dim behind the view with 30% opacity of the given color
                    .drawShadow(true) // Whether to draw a drop shadow or not
                    .cancelable(false) // Whether tapping outside the outer circle dismisses the view
                    .tintTarget(true) // Whether to tint the target view's color
                    .transparentTarget(false) // Specify whether the target is transparent (displays the content underneath)
                    // .icon(Drawable) // Specify a custom drawable to draw as the target
                    .targetRadius(60),  // Specify the target radius (in dp)
                object : TapTargetView.Listener() {
                    // The listener can listen for regular clicks, long clicks or cancels
                    override fun onTargetClick(view: TapTargetView) {
                        super.onTargetClick(view) // This call is optional
                        //doSomething()

                        val dialog = AlertDialog.Builder(binding.root.context).create()
                        val view = LayoutInflater.from(binding.root.context)
                            .inflate(R.layout.info_dialog, null, false)
                        dialog.setView(view)
                        dialog.setContentView(view)
                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        dialog.show()
                    }
                })
        }, 2000)


        closeKeyboard(binding.root)

        if (!isNetworkConnected()) {
            val dialog = AlertDialog.Builder(binding.root.context)
            dialog.setTitle("Biznes Plan Uzb")
            dialog.setMessage("Dasturdan foydalanish uchun internet kerak!")
            dialog.setPositiveButton("Ok") { _, _ -> }
            dialog.show()
        }

        binding.info.setOnClickListener {


            val dialog = AlertDialog.Builder(binding.root.context).create()
            val view = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.info_dialog, null, false)
            dialog.setView(view)
            dialog.setContentView(view)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
        }

        binding.enter.setOnClickListener {
            if (binding.email.text.isNotBlank()) {

                if (binding.ism.text.isNotBlank()) {

                    if (binding.fam.text.isNotBlank()) {

                        if (binding.phone.text!!.length == 19) {

                            var phoneNumber = binding.phone.text.toString()
                            phoneNumber = phoneNumber.replace("(", "", true)
                            phoneNumber = phoneNumber.replace(")", "", true)
                            phoneNumber = phoneNumber.replace(" ", "", true)
                            phoneNumber = phoneNumber.replace("-", "", true)


                            val dialog = AlertDialog.Builder(binding.root.context)
                            dialog.setTitle("Biznes Plan Uzb")
                            dialog.setMessage("Ma'lumotlar to'g'ri ekanligiga ishonchingiz komilmi? Siz bilan $phoneNumber orqali bog'lanamiz!")
                            dialog.setPositiveButton("Ha") { _, _ ->

                                MySharedPreference.emial = binding.email.text.toString()
                                MySharedPreference.ism = binding.ism.text.toString()
                                MySharedPreference.fam = binding.fam.text.toString()


                                MySharedPreference.tel = phoneNumber

                                if (!isNetworkConnected()) {
                                    val dialog = AlertDialog.Builder(binding.root.context)
                                    dialog.setTitle("Biznes Plan Uzb")
                                    dialog.setMessage("Dasturdan foydalanish uchun internet kerak!")
                                    dialog.setPositiveButton("Ok") { _, _ -> }
                                    dialog.show()
                                } else {
                                    startActivity(Intent(this, MainActivity2::class.java))
                                    finish()
                                }


                            }
                            dialog.show()

                        } else {
                            Toast.makeText(
                                this,
                                "Iltimos, telefon raqamingizni to'g'ri kiriting",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    } else {
                        binding.fam.error = "familiya kiritng"
                    }
                } else {
                    binding.ism.error = "ism kiritng"
                }
            } else {
                binding.email.error = "email kiritng"
            }
        }


    }
}



