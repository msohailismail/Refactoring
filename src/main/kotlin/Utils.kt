import java.text.NumberFormat
import java.util.*

fun format(number: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US).apply {
        minimumFractionDigits = 2
    }
    return formatter.format(number)
}