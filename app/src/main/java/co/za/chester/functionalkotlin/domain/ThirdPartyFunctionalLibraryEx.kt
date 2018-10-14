package co.za.chester.functionalkotlin.domain

import arrow.core.*
import arrow.syntax.function.andThen
import arrow.syntax.function.pipe

object ThirdPartyFunctionalLibraryEx {
    //TODO: Currying
    val examples: (content: String) -> String = { content ->

         content.replace("{result1}", arrowTryUsage())
                 .replace("{result2}", arrowCompositionUsage())
                 .replace("{result3}", arrowTryUsage())
    }

    open class GeneralException: Exception()

    class NoConnectionException: GeneralException()

    class AuthorizationException: GeneralException()

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

    private fun arrowTryUsage() : String {
        val tryGetLotteryNumbers = Try { getLotteryNumbers()}
        val tryList = Try{listOf(1,2,3,4, -1)}
        val failedCondition = tryList.filter { list -> list.any { it > 0 }}
        val filterCondition = if(failedCondition.isFailure()){
            "You can use `filter` to cause a failure when the predicate is not satisfied, this when return `Failure`"
        }else {
            ""
        }
        val status = if(tryGetLotteryNumbers.isSuccess()){
            "Success"
        }else{
            "Failure"
        }
        tryGetLotteryNumbers.getOrDefault { emptyList() }
        tryGetLotteryNumbers.getOrElse { ex: Throwable ->
            when(ex){
                is AuthorizationException -> emptyList()
                else -> throw ex
            }
        }
        return when(tryGetLotteryNumbers){
            is Success ->  "Success"
            is Failure -> "* Ths `Try` object is used to handle exceptions in a more graceful manner,\n" +
                    " when the execution is successful it returns `Success` (you can check the status of the Try `isSuccess`)\n" +
                    "or an exception occurs it returns `$status` (you can check the status of the Try `isFailure`)\n " +
                    "* $filterCondition\n" +
                    "* The `getOrDefault` function allows you to set a failure when occurs \n" +
                    "* The `getOrElse` allows you to check for a particular exception when a failure occurs"
        }
    }

    private fun arrowCompositionUsage() : String {
        val composeText: () -> String = { "The `compose`, `forwardCompose` (alias `andThen`)," +
                " takes the result of invoking the right-hand or left-hand function as the parameter for the left-hand or right-hand function"}
        val pipeText : (String) -> String = {it -> "$it"}
        val bold: (String) -> String = {it -> "**$it**"}

        val andThen = composeText andThen bold

        val pipe = pipeText("You can also use the `pipe` which takes value from the executed function and passing it in to next function and executes it") pipe bold

        return "* $andThen()\n * $pipe"
    }
}