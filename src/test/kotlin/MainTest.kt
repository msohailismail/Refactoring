import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class MainTest {

    @Test
    fun `cover all types of plays`() {
        // Given
        val plays = mutableMapOf(
            "hamlet" to Play(name = "Hamlet", type = "tragedy"),
            "as-like" to Play(name = "As You Like It", type = "comedy"),
            "othello" to Play(name = "Othello", type = "tragedy"),
        )

        val performances = listOf(
            Performance(playID = "hamlet", audience = 55),
            Performance(playID = "as-like", audience = 35),
            Performance(playID = "othello", audience = 40)
        )

        val invoice = Invoice(customer = "BigCo", performances)

        // When
        val result = statement(invoice, plays)

        var expected = "Statement for ${invoice.customer}\n"
        expected += "  Hamlet: $650.00 (55 seats)\n"
        expected += "  As You Like It: $580.00 (35 seats)\n"
        expected += "  Othello: $500.00 (40 seats)\n"
        expected += "Amount owed is $1,730.00\n"
        expected += "You earned 47 credits\n"
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should throw exception for unknown play type`() {
        // Given
        val plays = mutableMapOf(
            "hamlet" to Play(name = "Hamlet", type = "tragedy"),
            "as-like" to Play(name = "As You Like It", type = "comedy"),
            "othello" to Play(name = "Othello", type = "unknown"),
        )

        val performances = listOf(
            Performance(playID = "hamlet", audience = 55),
            Performance(playID = "as-like", audience = 35),
            Performance(playID = "othello", audience = 40)
        )

        val invoice = Invoice(customer = "BigCo", performances)

        // When & Assert
        val exception = assertThrows<Exception> {
            statement(invoice, plays)
        }
        assertEquals("Unknown type: unknown", exception.message)
    }
}