package co.za.chester.functionalkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import org.funktionale.option.Option

data class Slide(val title: String, val sections: Set<Section>)
data class Section(val content: String, val actions: Option<() -> Unit> = Option.None)

class MainActivity : AppCompatActivity() {

    private val slides: Set<Slide> = setOf(
            Slide("About Me", setOf(Section(resources.getString(R.string.about_me_content)))),
            Slide("What is Kotlin?", setOf(Section(resources.getString(R.string.what_is_kotlin_content)))),
            Slide("Why Kotlin?", setOf(Section(resources.getString(R.string.why_kotlin_content)))),
            Slide("Functional Kotlin aspects", setOf(Section(resources.getString(R.string.functional_kotlin_aspects)))),
            Slide("Immutability", setOf(
                    Section(resources.getString(R.string.immutability_content), Option.Some({

                    }))
            )),
            Slide("Higher-Order Functions", setOf(
                    Section(resources.getString(R.string.high_order_functions_content), Option.Some({

                    }))
            )),
            Slide("FunKTionale", setOf(
                    Section(resources.getString(R.string.funKTionale_content), Option.Some({

                    }))
            ))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentSlide = slides.first()
        updateTitleAndContent(currentSlide)
    }

    private fun updateTitleAndContent(currentSlide: Slide) {
        title = currentSlide.title
        val textView: TextView = findViewById(R.id.textView)
        textView.text = currentSlide.sections.fold("", { acc, it -> acc + it.content })
    }

    fun next(view: View) {
        val currentSlide = slides.first()
        val updatedSlides = slides.minusElement(currentSlide)
        updateTitleAndContent(updatedSlides.first())
    }

    fun back(view: View) {
        val currentSlide = slides.first()
        val updatedSlides = slides.minusElement(currentSlide)
        updateTitleAndContent(updatedSlides.first())
    }
}
