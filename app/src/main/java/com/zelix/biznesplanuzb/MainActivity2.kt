package com.zelix.biznesplanuzb

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.zelix.biznesplanuzb.databinding.ActivityMain2Binding
import com.zelix.biznesplanuzb.models.UserDetail
import com.zelix.biznesplanuzb.util.MySharedPreference
import uz.click.mobilesdk.core.ClickMerchant
import uz.click.mobilesdk.core.ClickMerchantConfig
import uz.click.mobilesdk.core.ClickMerchantManager
import uz.click.mobilesdk.core.callbacks.ClickMerchantListener
import uz.click.mobilesdk.impl.paymentoptions.PaymentOptionEnum
import uz.click.mobilesdk.impl.paymentoptions.ThemeOptions

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        closeKeyboard(binding.root)

        MySharedPreference.isSigned = true



        val currentUser = UserDetail(0, "", null, false)

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
        }, 2000)

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



        binding.imageView2.setOnClickListener {

/*            val url =
                "https://my.click.uz/services/pay?service_id=25104&merchant_id=17518&amount=1000&transaction_param=Alovuddin"
            val url3 =
                "https://my.click.uz/services/pay?service_id=22850&merchant_id=15850&amount=20000&transaction_param=154024&return_url=https://t.me/SeenSMSbot?token=b07fbdca53935fda4d47aa13d5c25ce8"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)*/

            //   startActivity(Intent(this, MainActivity3::class.java))
            val config = ClickMerchantConfig.Builder()
                .serviceId(25104)
                .merchantId(17518)
                .amount(15000.0)
                //transaction param is optional (if you not have your billing system)
                .transactionParam("Biznes g'oya kiritish")
                // .returnUrl("https://www.youtube.com/")
                .locale("UZ")
                .option(PaymentOptionEnum.CLICK_EVOLUTION)
                .theme(ThemeOptions.NIGHT)
                .productName("Biznes Plan Uzb")
                .productDescription("Biznesni osongina boshlang!")
                .merchantUserId(28287)
                .requestId(currentUser.requestId)
                .build()


            ClickMerchantManager.logs = BuildConfig.DEBUG

            ClickMerchant.init(
                supportFragmentManager, config,
                object : ClickMerchantListener {
                    override fun onReceiveRequestId(id: String) {
                        currentUser.requestId = id
                        //  Toast.makeText(this@MainActivity2, "Nima ekan endi bu $id !", Toast.LENGTH_SHORT).show()
                     //   println("nima ekan bu $id ?")
                    }

                    override fun onSuccess(paymentId: Long) {
                        currentUser.paymentId = paymentId
                        currentUser.paid = true
                        MySharedPreference.isPaid = true
                        startActivity(Intent(this@MainActivity2, MainActivity3::class.java))
                        finish()
                    }

                    override fun onFailure() {
                        currentUser.requestId = ""

                    }

                    override fun onInvoiceCancelled() {
                        currentUser.requestId = ""

                    }

                    override fun closeDialog() {
                        ClickMerchant.dismiss()
                    }
                }
            )


        }


    }
}