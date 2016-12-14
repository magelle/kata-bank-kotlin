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
        operationRepository = OperationRepository(clock)
    }

    @Test fun `should store a deposit`() {
        `when`(clock.todayAsString()).thenReturn("15/02/2014")
        operationRepository.deposit(150)
        val allOperations = operationRepository.allOperations()
        assertThat(allOperations).hasSize(1)
        assertThat(allOperations[0].date).isEqualTo("15/02/2014")
        assertThat(allOperations[0].amount).isEqualTo(150)
    }

    @Test fun `should store a withdrawal`() {
        `when`(clock.todayAsString()).thenReturn("15/02/2016")
        operationRepository.withdrawal(150)
        val allOperations = operationRepository.allOperations()
        assertThat(allOperations).hasSize(1)
        assertThat(allOperations[0].date).isEqualTo("15/02/2016")
        assertThat(allOperations[0].amount).isEqualTo(-150)
    }

}

