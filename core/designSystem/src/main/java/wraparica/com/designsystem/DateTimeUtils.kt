package wraparica.com.designsystem

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.convertToTime(): String {
    val date = Date(this * 1000)
    return SimpleDateFormat("hh:mm a", Locale.getDefault()).format(date)
}

fun Long.convertToDate(format: String = "EEE, MMM dd-hh:mm a" ): String {
    return SimpleDateFormat("EEE, MMM dd-hh:mm a", Locale.getDefault()).format(this)
}