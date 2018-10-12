package co.za.chester.functionalkotlin.domain

import arrow.core.None
import arrow.core.Some
import arrow.core.getOrElse

object NullableTypesVsOptionsEx {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}", "${nullableTypeSafeCallOperatorAndLetOperator()}\n${nullableTypeElvisOperator()}")
                .replace("{result2}", "${optionsMapHighOrderFunction()}\n${optionsGetOrElseMethod()}")
    }

    private fun nullableTypeSafeCallOperatorAndLetOperator() = {
        val nullableWithNull: String? = null
        val nullableWithValue: String? =
                "You declare a nullable type with `?`, e.g. `val x: String?`.\n " +
                "Access it safely we use `?.` safe-call operator which will return `null` if value is null.\n" +
                "The `let` operator allows us to work will values that are there"

        nullableWithNull ?. let{
            it
        }

         nullableWithValue ?. let {
            it
        }
    }

    private fun optionsMapHighOrderFunction() ={
        val none = None
        val some = Some("Options work the same as in Scala. You map on it, if `Some` use it or if `None` do nothing")

        none.map { it -> it }

        some.map { it -> it }
    }

    private fun nullableTypeElvisOperator() = {
        val nullableWithNull: String? = null
        nullableWithNull ?: "The elvis operator `?:` allows you to set a default when the value is `null`."
    }

    private fun optionsGetOrElseMethod() ={
        val none = None
        none.getOrElse { "The `getOrElse` method sets default when `None`" }
    }
}