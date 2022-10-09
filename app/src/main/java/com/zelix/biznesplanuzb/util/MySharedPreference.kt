package com.zelix.biznesplanuzb.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object MySharedPreference {

    lateinit var sharedPreferences: SharedPreferences

    fun getInstance(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "" +
                    "", Context.MODE_PRIVATE
        )
    }

    var emial: String?
        get() = sharedPreferences.getString("emial", null)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putString("emial", value)
            }
        }

    var ism: String?
        get() = sharedPreferences.getString("ism", null)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putString("ism", value)
            }
        }

    var fam: String?
        get() = sharedPreferences.getString("fam", null)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putString("fam", value)
            }
        }

    var tel: String?
        get() = sharedPreferences.getString("tel", null)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putString("tel", value)
            }
        }
    var isPaid: Boolean?
        get() = sharedPreferences.getBoolean("isPaid", false)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putBoolean("isPaid", value)
            }
        }
    var isLogged: Boolean?
        get() = sharedPreferences.getBoolean("isLogged", false)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putBoolean("isLogged", value)
            }
        }
    var isSigned: Boolean?
        get() = sharedPreferences.getBoolean("isSigned", false)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                this.putBoolean("isSigned", value)
            }
        }

}