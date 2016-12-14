package magelle.katabank

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.initMocks

class OperationRepositoryTest {

    val operationRepository: OperationRepository
    val clock = mock(Clock::class.java)

    init {
        initMocks(this)
        `when`(clock.todayAsString()).thenReturn("15/02/2014")
        operationRepository = OperationRepository(clock)
    }

    @Test fun `should store a deposit`() {
        operationRepository.deposit(150)
        val allOperations = operationRepository.allOperations()
        assertThat(allOperations).hasSize(1)
        assertThat(allOperations[0].date).isEqualTo("15/02/2014")
        assertThat(allOperations[0].amount).isEqualTo(150)
    }

    @Test fun `should store a withdrawal`() {
        operationRepository.withdrawal(150)
        val allOperations = operationRepository.allOperations()
        assertThat(allOperations).hasSize(1)
        assertThat(allOperations[0].date).isEqualTo("15/02/2014")
        assertThat(allOperations[0].amount).isEqualTo(-150)
    }

}

