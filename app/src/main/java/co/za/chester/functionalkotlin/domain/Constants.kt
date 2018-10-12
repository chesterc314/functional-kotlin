package co.za.chester.functionalkotlin.domain

object Constants {
     const val ABOUT_ME_CONTENT =
        "* My experience a year ago was in C# and JAVA\n" +
        "* Professionally I was a C# web developer and I build Android apps in my spare time using JAVA\n" +
        "* Now I use Scala on daily basis and Kotlin for my Android apps\n" +
        "* Clearly functional programming is where it's at now"
     const val  WHAT_IS_KOTLIN_CONTENT =
         "* Statically typed programming language for modern multi-platform applications\n" +
         "* 100% interoperable with Java™ and Android™\n" +
         "* Created by JetBrains\n" +
         "* It has both functional and imperative programming paradigms"
     const val WHY_KOTLIN_CONTENT =
         "* Concise - reduce boilerplate code\n" +
         "* Safe - null pointer exceptions (using nullable types)\n" +
         "* Interoperable - JAVA\n" +
         "* Tool-friendly - Intellisense"
    const val FUNCTIONAL_KOTLIN_ASPECTS =
        "* Immutability (no side effects) - var vs val, DTO and data structures(List, Map and Set)\n" +
        "* Higher-Order Functions - Functions are first class citizens (pass, return, initialise)\n" +
        "* A third-party library to make Kotlin more functional called Arrow (Option, Try, Composition, Currying)"
    const val IMMUTABILITY_CONTENT =
        "* var vs val\n" +
        "   - `var` - mutable (getter and setter) \n" +
        "   - `val` - is readonly (getter) can be immutable \n" +
        "* DTO and data structures(List, Map and Set)\n" +
        "      {result1}"
    const val HIGH_ORDER_FUNCTIONS_CONTENT =
        "* Functions are first class citizens\n" +
        "     {result1}"
    const val NULLABLE_TYPES_VS_OPTIONS =
            "* Nullable types\n" +
            "      {result1}\n" +
            "* Options\n" +
            "      {result2}"
    const val KOTLIN_VS_SCALA =
            "| Kotlin | Scala |" +
            "| ----------- | ----------- |" +
            "| Better tooling | Tooling is getting |" +
            "| Easier to learn | Steep learning curve |" +
            "| JAVA interoperablity | JAVA interoperablity |" +
            "| Community | Community |" +
            "| Nullable types | Options |" +
            "| No compile check? | Wartremover |" +
            "| Weak pattern matching | Strong pattern matching |"
    const val THIRD_PARTY_FUNCTIONAL_LIBRARY_CONTENT =
        "* Try\n" +
        "     {result1}\n" +
        "* Composition\n" +
        "     {result2}\n" +
        "* Currying\n" +
        "     {result3}"

    const val HTML_CONTENT = "<!doctype html>" +
            "<html>" +
            "<head>" +
            "  <meta charset='utf-8'/>" +
            "</head>" +
            "<body>" +
            "  <div id='content'></div>" +
            "  <script src='https://cdn.jsdelivr.net/npm/marked/marked.min.js'></script>" +
            "  <script>" +
            "    document.getElementById('content').innerHTML = marked('@{mark-down-content}');" +
            "  </script>" +
            "</body>" +
            "</html>"
 }