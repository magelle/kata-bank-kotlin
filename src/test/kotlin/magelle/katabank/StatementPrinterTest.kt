package magelle.katabank

import org.junit.Test
import org.mockito.Mockito.*

class StatementPrinterTest {

    private val console: Console
    private val statementPrinter: StatementPrinter

    init {
        console = mock(Console::class.java)
        statementPrinter = StatementPrinter(console)
    }

    @Test fun `should print header`() {
        statementPrinter.print(emptyList<Operation>())
        verify(console, times(1)).println("| date | credit | debit | balance |")
    }

    @Test fun `should print operations`() {
        val operations = listOf(
                Operation("10/01/2012", 1000),
                Operation("13/01/2012", 2000),
                Operation("14/01/2012", -500)
        )
        statementPrinter.print(operations)
        val inOrder = inOrder(console)
        inOrder.verify(console, times(1)).println("| date | credit | debit | balance |")
        inOrder.verify(console, times(1)).println("| 14/01/2012 |  | 500,00 | 2500,00 |")
        inOrder.verify(console, times(1)).println("| 13/01/2012 | 2000,00 |  | 3000,00 |")
        inOrder.verify(console, times(1)).println("| 10/01/2012 | 1000,00 |  | 1000,00 |")
    }

}