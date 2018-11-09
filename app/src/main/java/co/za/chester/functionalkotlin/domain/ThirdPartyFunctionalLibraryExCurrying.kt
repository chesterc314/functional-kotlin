package co.za.chester.functionalkotlin.domain

import arrow.syntax.function.curried
import arrow.syntax.function.uncurried

object ThirdPartyFunctionalLibraryExCurrying {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}", arrowCurryingUsage())
    }

    private fun arrowCurryingUsage(): String {
        val textCombiner: (String, String) -> String = { text1: String, text2: String -> "$text1$text2"}
        val curryingDefinition = "* Currying allows transforming a given function that takes multiple arguments into a sequence of functions, each having a single argument, \n"
        val usage = "you can use `curried` function to convert a non-curried function into curried function or you can convert a curried function to normal function."
        textCombiner(curryingDefinition, usage)
        val curriedTextCombiner: (String) -> (String) -> String = textCombiner.curried()
        val uncurriedTextCombiner: (String, String) -> String = curriedTextCombiner.uncurried()
        uncurriedTextCombiner(curryingDefinition, usage)
        return curriedTextCombiner(curryingDefinition)(usage)
    }
}