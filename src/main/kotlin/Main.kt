import java.lang.Exception
import kotlin.math.floor

fun main(args: Array<String>) {
}

fun statement(invoice: Invoice, plays: MutableMap<String, Play>): String {
    var totalAmount = 0
    var volumeCredits = 0

    var result = "Statement for ${invoice.customer}\n"

    for(perf in invoice.performances) {
        val play = plays[perf.playID] ?: continue
        var thisAmount: Int

        when (play.type) {
            "tragedy" -> {
                thisAmount = 40000
                if (perf.audience > 30) thisAmount += 1000 * (perf.audience - 30)
            }
            "comedy" -> {
                thisAmount = 30000
                if (perf.audience > 20) thisAmount += 10000 + 500 * (perf.audience - 20)
                thisAmount += 300 * perf.audience
            }
            else -> throw Exception("Unknown type: ${play.type}")
        }

        // Add volume credits
        volumeCredits += Math.max(perf.audience - 30, 0)
        // add extra credit for every ten comedy attendees
        if ("comedy" == play.type) volumeCredits += floor(perf.audience / 5.0).toInt()

        // print line for this order
        result += "  ${play.name}: ${format(thisAmount/100.0)} (${perf.audience} seats)\n"
        totalAmount += thisAmount
    }

    result += "Amount owed is ${format(totalAmount/100.0)}\n"
    result += "You earned $volumeCredits credits\n"

    return result
}