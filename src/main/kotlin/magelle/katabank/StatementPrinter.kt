package magelle.katabank

import java.text.DecimalFormat
import java.util.concurrent.atomic.AtomicInteger

class StatementPrinter(val console: Console) {
    private val numberFormat = "#.00"
    private val numberFormater = DecimalFormat(numberFormat)
    fun print(operations: List<Operation>) {
        console.println("| date | credit | debit | balance |")
        val balance = AtomicInteger(0)
        operations.map { operationToLine(balance, it) }
                .reversed()
                .forEach {
                    console.println(it)
                }
    }

    private fun operationToLine(balance: AtomicInteger, it: Operation): String {
        val credit = if (it.amount > 0) numberFormater.format(it.amount) else ""
        val debit = if (it.amount < 0) numberFormater.format(-it.amount) else ""
        val accBalance = numberFormater.format(balance.addAndGet(it.amount))
        return """| ${it.date} | $credit | $debit | $accBalance |"""
    }

}