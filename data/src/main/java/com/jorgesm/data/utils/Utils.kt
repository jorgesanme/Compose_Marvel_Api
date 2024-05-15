package com.jorgesm.data.utils

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

object Utils {
    private fun createMD5(input: String): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        val hash = BigInteger(1, messageDigest.digest(input.toByteArray())).toString(16)
            .padStart(32, '0')
        Log.i("Yo", hash)
        return  hash
    }

    fun createEndPointHash(): String {
        return createMD5(
            Const.TIME_STAMP.toString()
                    + Const.API_PRIVATE_KEY
                    + Const.API_PUBLIC_KEY
        )
    }
}