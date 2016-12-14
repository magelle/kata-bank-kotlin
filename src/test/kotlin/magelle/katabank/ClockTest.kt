package magelle.katabank

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate

class ClockTest {

    @Test fun `should return today as formated string`() {
        val clock = getClock()
        assertThat(clock.todayAsString()).isEqualTo("15/10/2015")
    }

    fun getClock() = object : Clock() {
        override fun today() = LocalDate.of(2015, 10, 15)
    }

}