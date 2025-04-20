/**
 * @author Mohamed Naser.
 */
package com.project.weather_utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDateFromUnix(unixTime: Long): String {
    val date = Date(unixTime * 1000)
    val format = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
    return format.format(date)
}