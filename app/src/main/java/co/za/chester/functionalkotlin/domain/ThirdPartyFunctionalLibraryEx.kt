package co.za.chester.functionalkotlin.domain

import arrow.core.*
import arrow.syntax.function.andThen
import arrow.syntax.function.pipe
import arrow.syntax.function.curried
import arrow.syntax.function.uncurried

object ThirdPartyFunctionalLibraryEx {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}", arrowTryUsage())
                .replace("{result2}", arrowCompositionUsage())
                .replace("{result3}", arrowCurryingUsage())
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

    private fun arrowCompositionUsage(): String {
        val composeText: () -> String = {
            "The `compose`, `forwardCompose` (alias `andThen`)," +
                    " takes the result of invoking the right-hand or left-hand function as the parameter for the left-hand or right-hand function, when returns a composed function"
        }
        val pipeText: (String) -> String = { it -> it }
        val bold: (String) -> String = { it -> "**$it**" }

        val andThen = composeText andThen bold

        val pipe = pipeText("You can also use the `pipe` which takes value from the executed function and passing it in to next function and executes it") pipe bold

        return "* ${andThen()}\n* $pipe"
    }



    private fun arrowCurryingUsage(): String {
        val textCombiner: (String, String) -> String = { text1: String, text2: String -> "$text1$text2"}
        val curryingDefinition = "* Currying allows transforming a given function that takes multiple arguments into a sequence of functions, each having a single argument, \n"
        val usage = "you can use `curried` function to convert a non-curried function into curried function or you can convert a curried function to normal function."
        textCombiner(curryingDefinition, usage)
        val curriedTextCombiner: (String) -> (String) -> String = textCombiner.curried()
        val uncurriedTextCombiner: (String, String) -> String = curriedTextCombiner.uncurried()
        uncurriedTextCombiner(curryingDefinition, usage)
        return  curriedTextCombiner(curryingDefinition)(usage)
    }
}