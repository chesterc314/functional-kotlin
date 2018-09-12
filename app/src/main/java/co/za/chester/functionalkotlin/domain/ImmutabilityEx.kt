package co.za.chester.functionalkotlin.domain

object ImmutabilityEx {
    val examples: (content: String) -> String = { content ->
        content + "R"
    }
}