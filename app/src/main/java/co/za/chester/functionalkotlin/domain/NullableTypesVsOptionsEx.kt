package co.za.chester.functionalkotlin.domain

import arrow.core.None
import arrow.core.Some
import arrow.core.getOrElse

object NullableTypesVsOptionsEx {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}", "* ${nullableTypeSafeCallOperatorAndLetOperator()}\n* ${nullableTypeElvisOperator()}")
                .replace("{result2}", "* ${optionsMapHighOrderFunction()}\n* ${optionsGetOrElseMethod()}")
    }

    private fun nullableTypeSafeCallOperatorAndLetOperator(): String {
        val nullableWithNull: String? = null
        val nullableWithValue: String? =
                "You declare a nullable type with `?`, e.g. `val x: String?`." +
                        "Access it safely we use `?.` safe-call operator which will return `null` if value is null." +
                        "The `let` operator allows us to work with values that are there"

        nullableWithNull?.let {
            it
        }
        val result: String? = nullableWithValue?.let {
            it
        }
        return result!!
    }

    private fun optionsMapHighOrderFunction(): String {
        val none = None
        val some = Some("Options work the same as in Scala. You map on it, if `Some` use it or if `None` do nothing")
        none.map { it -> it }
        some.map { it -> it }

        return some.map { it -> it }.getOrElse { "" }
    }

    private fun nullableTypeElvisOperator(): String {
        val nullableWithNull: String? = null
        return nullableWithNull
                ?: "The elvis operator `?:` allows you to set a default when the value is `null`."
    }

    private fun optionsGetOrElseMethod(): String {
        val none = None
        return none.getOrElse { "The `getOrElse` method sets default when `None`" }
    }
}