package co.za.chester.functionalkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import org.funktionale.option.Option
import org.funktionale.option.getOrElse

data class Slide(val title: String, val sections: Set<Section>)
data class Section(val content: String, val maybeAction: Option<(String) -> String> = Option.Some({ it -> it}))

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var slides: Set<Slide>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        slides = setOf(
                co.za.chester.functionalkotlin.Slide("About Me", kotlin.collections.setOf(co.za.chester.functionalkotlin.Section(resources.getString(co.za.chester.functionalkotlin.R.string.about_me_content)))),
                Slide("What is Kotlin?", setOf(Section(resources.getString(R.string.what_is_kotlin_content)))),
                Slide("Why Kotlin?", setOf(Section(resources.getString(R.string.why_kotlin_content)))),
                Slide("Functional Kotlin aspects", setOf(Section(resources.getString(R.string.functional_kotlin_aspects)))),
                Slide("Immutability", setOf(
                        Section(resources.getString(R.string.immutability_content), Option.Some({ it ->
                            it.replace("{result1}", "")
                        }))
                )),
                Slide("Higher-Order Functions", setOf(
                        Section(resources.getString(R.string.high_order_functions_content), Option.Some({ it ->
                            it.replace("{result1}", "")
                        }))
                )),
                Slide("FunKTionale", setOf(
                        Section(resources.getString(R.string.funKTionale_content), Option.Some({ it ->
                            it.replace("{result1}", "")
                        }))
                ))
        )
        val currentSlide = slides.first()
        updateTitleAndContent(currentSlide)
    }

    private fun updateTitleAndContent(currentSlide: Slide) {
        title = currentSlide.title
        val textView: TextView = findViewById(R.id.textView)
        val content = currentSlide.sections.fold("", { acc, it ->
            "$acc${it.maybeAction.map { action -> action(it.content) }.getOrElse { "" }}"
        })
        textView.text = content
    }

    fun next(view: View) {
        if (count < slides.size - 1) {
            updateTitleAndContent(slides.elementAt(++count))
        } else {
            count = slides.size - 1
            updateTitleAndContent(slides.elementAt(count))
        }
    }

    fun back(view: View) {
        if (count > 0) {
            updateTitleAndContent(slides.elementAt(--count))
        } else {
            count = 0
            updateTitleAndContent(slides.elementAt(count))
        }
    }
}
