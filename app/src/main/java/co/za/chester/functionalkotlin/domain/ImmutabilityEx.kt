package co.za.chester.functionalkotlin.domain

object ImmutabilityEx {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}",
                "${immutableDTObject()}\n${immutableDataStructures()}"
        )
    }

    data class Text(val txt: String){
        override fun toString(): String {
            return txt
        }
    }

    private fun immutableDTObject() = {
        val text = Text("Original")
        "${text.copy(txt = "Data classes can be used to make objects immutable and \n" +
                "to create a new version of the object use `copy` it will return a new object from the original")}"
    }

    private fun immutableDataStructures() = {
        val list = listOf("A", "B", "C")
        val map = mapOf(Pair(1, "A"),Pair(2, "B"), Pair(3,"C"))
        val set = setOf(1, 2, 3)
        "To initial a list use `listOf`: $list, a map use `mapOf`: $map and a set use `setOf`: $set\n" +
        "The `plus` and `subtract` methods will allow for adding and removing from list, map and set, but it will return the new version of each type.\n"
        "High order functions used when dealing with these data structures such as `map`, `filter` and `fold` "
    }
}