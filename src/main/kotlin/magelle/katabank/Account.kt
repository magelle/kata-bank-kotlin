package magelle.katabank

class Account(val operationRepository: OperationRepository, val statementPrinter: StatementPrinter) {

    fun deposit(amount: Int) {
        operationRepository.deposit(amount)
    }

    fun withdraw(amount: Int) {
        operationRepository.withdrawal(amount)
    }

    fun printStatement() {
        statementPrinter.print(operationRepository.allOperations())
    }

}