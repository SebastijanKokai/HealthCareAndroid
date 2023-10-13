package com.example.healthcare.extension

import android.text.TextUtils

fun String.isPasswordValid(): Boolean {
    // TODO Add more secure password validation
    return this.length >= 6
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
        .matches()
}