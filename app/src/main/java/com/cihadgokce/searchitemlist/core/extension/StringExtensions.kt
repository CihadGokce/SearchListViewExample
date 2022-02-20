package com.cihadgokce.searchitemlist.core.extension

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


object StringExtensions {
    fun convertDateFormat(inputDateStr : String) : String {
        var inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var outputFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")
        var date: Date = inputFormat.parse(inputDateStr)
        var outputDateStr: String = outputFormat.format(date)
        return outputDateStr
    }
}