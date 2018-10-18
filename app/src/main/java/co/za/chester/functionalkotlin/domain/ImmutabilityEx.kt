package co.za.chester.functionalkotlin.domain

object ImmutabilityEx {
    val examples: (content: String) -> String = { content ->
        content.replace("{result1}",
                " * ${immutableDTObject()}\n * ${immutableDataStructures()}"
        )
    }

    data class Text(val txt: String){
        override fun toString(): String {
            return txt
        }
    }

    private fun immutableDTObject(): String {
        val text = Text("Original")
        return "${text.copy(txt = "Data classes can be used to make objects immutable and " +
                "to create a new version of the object use `copy` it will return a new object from the original object")}"
    }

    private fun immutableDataStructures(): String{
        val list = listOf("A", "B", "C")
        val arrayList = ArrayList<String>()
        arrayList.add("The")
        val map = mapOf(Pair(1, "A"),Pair(2, "B"), Pair(3,"C"))
        val set = setOf(1, 2, 3)
        val newList = (list + "D") - "A"
        return "To initialise a list use `listOf`: $newList, a map use `mapOf`: $map and a set use `setOf`: $set" +
        "The `plus` (`+`) and `minus` (`-`) methods will allow for adding and removing from list, map and set, but it will return the new version of each type." +
        "High order functions are used when dealing with these data structures such as `map`, `filter` and `fold` "
    }
}