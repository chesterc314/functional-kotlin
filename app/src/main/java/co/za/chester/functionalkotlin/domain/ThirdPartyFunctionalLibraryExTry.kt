package co.za.chester.functionalkotlin.domain

import arrow.core.*

object ThirdPartyFunctionalLibraryExTry {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}", arrowTryUsage())
    }

    open class GeneralException : Exception()

    class NoConnectionException : GeneralException()

    class AuthorizationException : GeneralException()

    private fun checkPermissions() {
        throw AuthorizationException()
    }

    private fun getLotteryNumbersFromCloud(): List<String> {
        throw NoConnectionException()
    }

    private fun getLotteryNumbers(): List<String> {
        checkPermissions()

        return getLotteryNumbersFromCloud()
    }

    private fun arrowTryUsage(): String {
        val tryGetLotteryNumbers = Try { getLotteryNumbers() }
        val tryList = Try { listOf(1, 2, 3, 4, -1) }
        val failedCondition = tryList.filter { list -> list.all { it > 0 } }
        val filterCondition = if (failedCondition.isFailure()) {
            "You can use `filter` to cause a failure when the predicate is not satisfied, this when return `Failure`"
        } else {
            ""
        }
        val status = if (tryGetLotteryNumbers.isSuccess()) {
            "Success"
        } else {
            "Failure"
        }
        tryGetLotteryNumbers.getOrDefault { emptyList() }
        tryGetLotteryNumbers.getOrElse { ex: Throwable ->
            when (ex) {
                is AuthorizationException -> emptyList()
                else -> throw ex
            }
        }
        return when (tryGetLotteryNumbers) {
            is Success -> "Success"
            is Failure -> "* The `Try` object is used to handle exceptions in a more graceful manner," +
                    " when the execution is successful it returns `Success` (you can check the status of the Try `isSuccess`)" +
                    "or an exception occurs it returns `$status` (you can check the status of the Try `isFailure`)\n" +
                    "* $filterCondition\n" +
                    "* The `getOrDefault` function allows you to set a failure when occurs \n" +
                    "* The `getOrElse` allows you to check for a particular exception when a failure occurs\n"
        }
    }
}