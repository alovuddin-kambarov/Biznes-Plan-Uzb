package com.zelix.biznesplanuzb.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zelix.biznesplanuzb.databinding.ActivityWebBinding


class WebActivity : AppCompatActivity() {
    lateinit var binding:ActivityWebBinding
    @SuppressLint("SetJavaScriptEnabled", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.web.webViewClient = WebViewClient()
        binding.web.settings.loadsImagesAutomatically = true
        binding.web.settings.javaScriptEnabled = true
        binding.web.scrollBarStyle = View.VISIBLE
        binding.web.settings.builtInZoomControls = true
        binding.web.settings.setSupportZoom(false)
        binding.web.settings.loadWithOverviewMode = true
        binding.web.settings.useWideViewPort = true
        binding.web.settings.allowContentAccess = true
     //    binding.web.webViewClient = WebViewClass(wv, loadProgress)
       binding.web.loadUrl("https://my.click.uz/services/pay?service_id=22850&merchant_id=15850&amount=1000&transaction_param=154024&return_url=https://t.me/SeenSMSbot?token=8a890ebb99b753cb277f44ab782719c7")
     //   binding.web.loadUrl("https://github.com/click-llc/android-msdk")

    }


}