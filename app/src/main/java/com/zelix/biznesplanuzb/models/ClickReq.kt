package com.zelix.biznesplanuzb.models

data class ClickReq(
    val click_trans_id: String,
    val service_id: String,
    val click_paydoc_id: String,
    val error_notes: String,
    val merchat_prepare_id: String,
    val sign_time: String,
    val sign_string: String,
    val merchant_trans_id:String,
    val amount:Int,
    val ok:Boolean
)