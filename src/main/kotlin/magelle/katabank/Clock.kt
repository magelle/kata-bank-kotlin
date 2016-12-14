package magelle.katabank

import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class Clock {
    val format = "dd/MM/yyyy"
    fun todayAsString(): String = today().format(DateTimeFormatter.ofPattern(format))
    protected open fun today() = LocalDate.now()!!
}