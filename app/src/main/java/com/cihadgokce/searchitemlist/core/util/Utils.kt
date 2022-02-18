package com.cihadgokce.searchitemlist.core.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject


class Utils(val context: Context) {


    fun getJSONFromAssets(fileName: String): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val jSONFile = context.assets.open(fileName)
            val size = jSONFile.available()
            val buffer = ByteArray(size)
            jSONFile.read(buffer)
            jSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}