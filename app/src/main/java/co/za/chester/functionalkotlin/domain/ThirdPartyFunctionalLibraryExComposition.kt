package co.za.chester.functionalkotlin.domain

import arrow.syntax.function.andThen
import arrow.syntax.function.pipe

object ThirdPartyFunctionalLibraryExComposition {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}", arrowCompositionUsage())
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
}