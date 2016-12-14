package magelle.feature

import magelle.katabank.*
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.mock

class KataBankFeature {

    private val console = mock(Console::class.java)
    private val clock = mock(Clock::class.java)
    private val operationRepository: OperationRepository
    private val statementPrinter: StatementPrinter
    private val account: Account

    init {
        operationRepository = OperationRepository(clock)
        statementPrinter = StatementPrinter(console)
        account = Account(operationRepository, statementPrinter)
    }

    @Test fun scenario() {
        given(clock.todayAsString()).willReturn("10/01/2012", "13/01/2012", "14/01/2012")
        account.deposit(1000)
        account.deposit(2000)
        account.withdraw(500)

        account.printStatement()

        val inOrder = inOrder(console)
        inOrder.verify(console).println("| date | credit | debit | balance |");
        inOrder.verify(console).println("| 14/01/2012 |  | 500,00 | 2500,00 |");
        inOrder.verify(console).println("| 13/01/2012 | 2000,00 |  | 3000,00 |");
        inOrder.verify(console).println("| 10/01/2012 | 1000,00 |  | 1000,00 |");
    }

}

