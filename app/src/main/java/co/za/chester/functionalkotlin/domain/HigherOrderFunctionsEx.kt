package co.za.chester.functionalkotlin.domain

object HigherOrderFunctionsEx {
    val examples: (content: String) -> String = { content ->
        content + "R"
    }
}