package com.zelix.biznesplanuzb.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.zelix.biznesplanuzb.R
import com.zelix.biznesplanuzb.databinding.ActivityMain2Binding
import com.zelix.biznesplanuzb.models.Click
import com.zelix.biznesplanuzb.retrofit.resource.Status
import com.zelix.biznesplanuzb.retrofit.view_model.ViewModel
import com.zelix.biznesplanuzb.util.MySharedPreference
import com.zelix.biznesplanuzb.util.closeKeyboard
import com.zelix.biznesplanuzb.util.isNetworkConnected
import java.text.SimpleDateFormat
import java.util.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var viewModel: ViewModel
    private lateinit var dialog: AlertDialog

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        closeKeyboard(binding.root)

        MySharedPreference.isSigned = true
        setProgress()

        Handler(Looper.myLooper()!!).postDelayed({
            TapTargetView.showFor(this,  // `this` is an Activity
                TapTarget.forView(
                    findViewById(R.id.imageView2),
                    "To'lov qilish uchun bu yerga bosing"
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
                    .dimColor(R.color.blue) // If set, will dim behind the view with 30% opacity of the given color
                    .drawShadow(false) // Whether to draw a drop shadow or not
                    .cancelable(true) // Whether tapping outside the outer circle dismisses the view
                    .tintTarget(false) // Whether to tint the target view's color
                    .transparentTarget(false) // Specify whether the target is transparent (displays the content underneath)
                    //  .icon() // Specify a custom drawable to draw as the target
                    .targetRadius(60),  // Specify the target radius (in dp)
                object : TapTargetView.Listener() {
                    // The listener can listen for regular clicks, long clicks or cancels
                    override fun onTargetClick(view: TapTargetView) {
                        super.onTargetClick(view) // This call is optional
                        //doSomething()


                    }
                })
        }, 800)

        binding.imageView.setOnClickListener {

            val dialog = AlertDialog.Builder(binding.root.context)
            dialog.setTitle("Biznes Plan Uzb")
            dialog.setMessage("Hozircha faqat Click bilan to'lov qilish mumkin")
            dialog.setPositiveButton("Ok") { _, _ ->

                /*      startActivity(Intent(this, MainActivity3::class.java))
                      MySharedPreference.isPaid = true
                      finish()*/

            }
            dialog.show()
        }

        if (!isNetworkConnected()) {
            val dialog = AlertDialog.Builder(binding.root.context)
            dialog.setTitle("Biznes Plan Uzb")
            dialog.setMessage("Dasturdan foydalanish uchun internet kerak!")
            dialog.setPositiveButton("Ok") { _, _ -> }
            dialog.show()
        }

        binding.check.setOnClickListener {
            checkPaid(true)
        }


        val userId = createID()
        MySharedPreference.userId = userId
        binding.imageView2.setOnClickListener {

            //    startActivity(Intent(this,WebActivity::class.java))
            val url =
                "https://my.click.uz/services/pay?service_id=25104&merchant_id=17518&amount=15000&transaction_param=${userId}";

            try {
                val i = Intent();
                i.setPackage("com.android.chrome");
                i.action = Intent.ACTION_VIEW;
                i.data = Uri.parse(url);
                startActivity(i)
            } catch (e: Exception) {
                try {
                    val i = Intent();
                    i.setPackage("com.sec.android.app.sbrowser");
                    i.action = Intent.ACTION_VIEW;
                    i.data = Uri.parse(url);
                    startActivity(i)
                } catch (e: Exception) {
                    val i = Intent();
                    i.action = Intent.ACTION_VIEW;
                    i.data = Uri.parse(url);
                    startActivity(i)
                }

            }

        }


    }


    private fun checkPaid(isButton: Boolean) {

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val click = MySharedPreference.userId!!

        var a = false
        var a1 = false
        viewModel.getPaidStatus(binding.root.context, click).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    dialog.cancel()
//                    arrayList.addAll(it.data!!.results.books)

                    println("senorita $a: ${it.data}")

                    if (it.data!!.ok) {
                        if (it.data.merchant_trans_id == MySharedPreference.userId.toString()) {

                            startActivity(Intent(applicationContext, MainActivity3::class.java))
                            finish()


                        } else {
                            if (isButton) {

                                if (!a) {
                                    val aaa = AlertDialog.Builder(binding.root.context)
                                        .setTitle("Biznes Plan Uzb")
                                        .setMessage("To'lov hali qilinmagan!")
                                        .setPositiveButton(
                                            "Ok"
                                        ) { p0, _ ->
                                            p0.cancel()

                                        }
                                    aaa.show()
                                    aaa.setOnCancelListener {
                                        a = false
                                    }
                                }
                                a = true
                            }
                        }
                    }else{
                        if (isButton) {

                            if (!a) {
                                val aaa = AlertDialog.Builder(binding.root.context)
                                    .setTitle("Biznes Plan Uzb")
                                    .setMessage("To'lov hali qilinmagan!")
                                    .setPositiveButton(
                                        "Ok"
                                    ) { p0, _ ->
                                        p0.cancel()

                                    }
                                aaa.show()
                                aaa.setOnCancelListener {
                                    a = false
                                }
                            }
                            a = true
                        }
                    }

                }
                Status.LOADING -> {
                    dialog.show()
                }
                Status.ERROR -> {


                    dialog.cancel()
                    if (!a1) {

                        val aaa = AlertDialog.Builder(binding.root.context).setTitle("Xato")
                            .setMessage("Nimadur xato ketti. Ehtimol, internet bilan bog'liq muammo bor")
                            .setPositiveButton(
                                "Ok"
                            ) { p0, _ ->
                                p0.cancel()

                            }

                        aaa.setOnCancelListener {
                            a1 = false
                        }
                        aaa.show()

                    }
                    a1 = true
                }
            }


        }
    }

    private fun createID(): Int {
        return  ((SystemClock.uptimeMillis() % 999999999).toInt());
    }

    override fun onResume() {
        super.onResume()


        checkPaid(false)

/*
        if (MySharedPreference.isSuccessPaid!!) {
            startActivity(Intent(applicationContext, MainActivity3::class.java))
            finish()
        }*/

    }


    private fun setProgress() {
        dialog = AlertDialog.Builder(binding.root.context).create()
        val view = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.custom_progress, null, false)
        dialog.setView(view)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
    }
}