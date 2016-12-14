package magelle.katabank

import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

class AccountTest {

    val operationRepository = mock(OperationRepository::class.java)!!
    val statementPrinter = mock(StatementPrinter::class.java)!!
    val account = Account(operationRepository, statementPrinter)

    @Test fun `should do a deposit`() {
        account.deposit(1000)
        verify(operationRepository, times(1)).deposit(1000)
    }

    @Test fun `should do a withdrawal`() {
        account.withdraw(1000)
        verify(operationRepository, times(1)).withdrawal(1000)
    }

    @Test fun `should print statement`() {
        val operations = emptyList<Operation>()
        given(operationRepository.allOperations()).willReturn(operations)
        account.printStatement()
        verify(statementPrinter, times(1)).print(operations)
    }

}

