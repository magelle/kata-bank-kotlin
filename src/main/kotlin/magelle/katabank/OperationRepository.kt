package magelle.katabank

import java.util.*
import java.util.Collections.unmodifiableList

class OperationRepository(val clock: Clock) {

    val operations = ArrayList<Operation>()

    fun deposit(amount: Int) {
        operations.add(Operation(clock.todayAsString(), amount))
    }

    fun withdrawal(amount: Int) {
        operations.add(Operation(clock.todayAsString(), -amount))
    }

    fun allOperations(): List<Operation> = unmodifiableList(operations)

}