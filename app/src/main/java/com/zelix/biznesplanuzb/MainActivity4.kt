package com.zelix.biznesplanuzb

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.zelix.biznesplanuzb.databinding.ActivityMain4Binding
import com.zelix.biznesplanuzb.models.User
import com.zelix.biznesplanuzb.util.MySharedPreference
import java.text.SimpleDateFormat
import java.util.*


class MainActivity4 : AppCompatActivity() {
    private lateinit var dialogProgress: AlertDialog
    private lateinit var binding: ActivityMain4Binding

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        closeKeyboard(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)


        val type = intent.getStringExtra("type").toString()
        binding.tv.text = type

        val db = FirebaseFirestore.getInstance()


        binding.button.setOnClickListener {

            if (binding.editText.text.isNotBlank()) {
                if (binding.editText2.text.isNotBlank()) {


                    if (!isNetworkConnected()) {
                        val dialog = AlertDialog.Builder(binding.root.context)
                        dialog.setTitle("Biznes Plan Uzb")
                        dialog.setMessage("Dasturdan foydalanish uchun internet kerak!")
                        dialog.setPositiveButton("Ok") { _, _ -> }
                        dialog.show()
                    } else {

                        val dialog = AlertDialog.Builder(binding.root.context)
                        dialog.setTitle("Biznes Plan Uzb")
                        dialog.setMessage("Ma'lumotlar to'g'ri ekanligiga ishonchingiz komilmi?")
                        dialog.setNegativeButton("Yo'q") { _, _ -> }
                        dialog.setNegativeButton("Ha") { _, _ ->
                            dialog.create().cancel()
                            setProgress()

                            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                            val currentDate = sdf.format(Date())

                            val user = User(
                                MySharedPreference.emial,
                                MySharedPreference.ism,
                                MySharedPreference.fam,
                                MySharedPreference.tel,
                                type,
                                binding.editText.text.toString(),
                                currentDate.toString(),
                                binding.editText2.text.toString(),
                                null
                            )

                            db.collection("users")
                                .add(user)
                                .addOnSuccessListener {
                                    dialogProgress.cancel()
                                    startActivity(Intent(this, SuccessActivity::class.java))
                                    MySharedPreference.isLogged = true
                                    finish()
                                }
                                .addOnFailureListener { e ->

                                    dialogProgress.cancel()
                                    val errorDialog = AlertDialog.Builder(binding.root.context)
                                    errorDialog.setTitle("Xato!")
                                    errorDialog.setMessage("Nimadur xato ketti. Iltimos, keyinroq qaytadan urining.\nEhtimol, internet bilan bog'liq muammo bor :) Internetingizni tekshiring")
                                    errorDialog.setNegativeButton("Okay") { _, _ -> }
                                    errorDialog.show()
                                    Log.w(TAG, "Error adding document", e)
                                }


                        }
                        dialog.setCancelable(false)
                        dialog.show()

                    }


                } else
                    binding.editText.error = "Biznesingizni to'liq ma'lumotini kiriting"
            } else
                binding.editText.error = "Biznesingizni qisqacha ma'lumotini kiriting"


        }


    }

    private fun setProgress() {
        dialogProgress = AlertDialog.Builder(binding.root.context).create()
        val view = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.custom_progress, null, false)
        dialogProgress.setView(view)
        dialogProgress.setContentView(view)
        dialogProgress.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogProgress.setCancelable(false)
        dialogProgress.show()
    }

}

