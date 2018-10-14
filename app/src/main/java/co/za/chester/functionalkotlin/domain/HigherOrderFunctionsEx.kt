package co.za.chester.functionalkotlin.domain

object HigherOrderFunctionsEx {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}",
                "* ${functionsPassedAsArguments { it -> it }}\n" +
                        "* ${functionsDeclaredAsVariables()}\n" +
                        "* ${functionsReturnedFromOtherFunction()("")}" +
                        "* Examples of built in high order functions: \n${someBuiltInHigherOrderFunctions()}"
        )
    }

    private fun functionsPassedAsArguments(passThrough: (String) -> String) = {
        passThrough("Functions are passed through as arguments for functions")
    }

    private fun functionsDeclaredAsVariables() = {
        val declaredFunction = { text: String -> text }
        declaredFunction("Functions can be declared as variables")
    }

    private fun functionsReturnedFromOtherFunction(): (String) -> String {
        return { text: String -> "Functions can be returned by functions" }
    }

    private fun someBuiltInHigherOrderFunctions() = {
        val list = listOf("A", "B", "C")
        "We have `filter`: `${list.filter { it == "A" }}` \n" +
                "then there is `fold` which has an initial accumulator value and list is added from left to right to accumulator `${list.fold("") { acc, it -> acc + it }}`\n" +
                "A `map` transforms value(s) from one form to another (A -> B) `${list.map { it.toLowerCase() }}`\n " +
                "A `flatMap` returns single list (flatten) and transforms values(s) `${listOf(list).flatMap { it }}`"
    }
}