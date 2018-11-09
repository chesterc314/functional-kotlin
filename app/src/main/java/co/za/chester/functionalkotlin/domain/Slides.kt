package co.za.chester.functionalkotlin.domain

data class Section(val content: String, val action: (String) -> String = { it -> it })

abstract class BaseSlide(val title: String, private val section: Section) {
    fun content(): String = section.action(section.content)
    abstract fun next(): BaseSlide
    abstract fun back(): BaseSlide
}

object BeforeReleaseSlide : BaseSlide("Join us", Section("# Join us for the Meetup at Jemstep 12/11/2018")) {
    override fun next() = this
    override fun back() = this
}

object AboutMeSlide : BaseSlide("About Me", Section(Constants.ABOUT_ME_CONTENT)) {
    override fun next() = EvolutionImage
    override fun back() = ThirdPartyFunctionalLibraryCurrying
}

object EvolutionImage: BaseSlide("Programmers Evolution", Section(Constants.EVOLUTION_IMAGE)) {
    override fun next() = WhyKotlin
    override fun back() = AboutMeSlide
}

object WhyKotlin : BaseSlide("Why Kotlin?", Section(Constants.WHY_KOTLIN_CONTENT)) {
    override fun next() = WhatIsKotlinSlide
    override fun back() = EvolutionImage
}
object WhatIsKotlinSlide : BaseSlide("What is Kotlin?", Section(Constants.WHAT_IS_KOTLIN_CONTENT)) {
    override fun next() = FunctionalKotlinAspects
    override fun back() = WhyKotlin
}

object FunctionalKotlinAspects : BaseSlide("Functional Kotlin aspects", Section(Constants.FUNCTIONAL_KOTLIN_ASPECTS)) {
    override fun next() = Immutability
    override fun back() = WhatIsKotlinSlide
}

object Immutability : BaseSlide("Immutability", Section(Constants.IMMUTABILITY_CONTENT, ImmutabilityEx.examples)) {
    override fun next() = HigherOrderFunctions
    override fun back() = FunctionalKotlinAspects
}

object HigherOrderFunctions : BaseSlide("Higher-Order Functions", Section(Constants.HIGH_ORDER_FUNCTIONS_CONTENT, HigherOrderFunctionsEx.examples)) {
    override fun next() = NullableTypesVsOptions
    override fun back() = Immutability
}

object NullableTypesVsOptions : BaseSlide("Nullable Types vs Options", Section(Constants.NULLABLE_TYPES_VS_OPTIONS, NullableTypesVsOptionsEx.examples)) {
    override fun next() = KotlinVsScala
    override fun back() = HigherOrderFunctions
}

object KotlinVsScala : BaseSlide("Kotlin vs Scala", Section(Constants.KOTLIN_VS_SCALA)) {
    override fun next() = ThirdPartyFunctionalLibraryTry
    override fun back() = NullableTypesVsOptions
}

object ThirdPartyFunctionalLibraryTry : BaseSlide("Third Party Functional Library - Try", Section(Constants.THIRD_PARTY_FUNCTIONAL_LIBRARY_CONTENT_TRY, ThirdPartyFunctionalLibraryExTry.examples)) {
    override fun next() = ThirdPartyFunctionalLibraryComposition
    override fun back() = KotlinVsScala
}

object ThirdPartyFunctionalLibraryComposition : BaseSlide("Third Party Functional Library - Composition", Section(Constants.THIRD_PARTY_FUNCTIONAL_LIBRARY_CONTENT_COMPOSITION, ThirdPartyFunctionalLibraryExComposition.examples)) {
    override fun next() = ThirdPartyFunctionalLibraryCurrying
    override fun back() = ThirdPartyFunctionalLibraryTry
}

object ThirdPartyFunctionalLibraryCurrying : BaseSlide("Third Party Functional Library - Currying", Section(Constants.THIRD_PARTY_FUNCTIONAL_LIBRARY_CONTENT_CURRYING, ThirdPartyFunctionalLibraryExCurrying.examples)) {
    override fun next() = AboutMeSlide
    override fun back() = ThirdPartyFunctionalLibraryComposition
}