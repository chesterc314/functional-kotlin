package co.za.chester.functionalkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

data class Slide(val title: String, val sections: Set<Section>) {
    fun getContent(): String = sections.fold("") { acc, it ->
        "$acc${it.action(it.content)}"
    }
}

data class Section(val content: String, val action: (String) -> String = { it -> it })

class MainActivity : AppCompatActivity() {
    private var index = 0
    private lateinit var slides: Set<Slide>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.slides = setOf(
                Slide("About Me", setOf(Section(resources.getString(R.string.about_me_content)))),
                Slide("What is Kotlin?", setOf(Section(resources.getString(R.string.what_is_kotlin_content)))),
                Slide("Why Kotlin?", setOf(Section(resources.getString(R.string.why_kotlin_content)))),
                Slide("Functional Kotlin aspects", setOf(Section(resources.getString(R.string.functional_kotlin_aspects)))),
                Slide("Immutability", setOf(Section(resources.getString(R.string.immutability_content), Immutability.examples))),
                Slide("Higher-Order Functions", setOf(Section(resources.getString(R.string.high_order_functions_content),
                        HigherOrderFunctions.examples))),
                Slide("FunKTionale", setOf(Section(resources.getString(R.string.funKTionale_content), FunKTionale.examples)))
        )
        updateTitleAndContent(this.slides.first())
    }

    fun next(view: View) {
        this.index = incrementNextSlide()
        updateTitleAndContent(this.slides.elementAt(this.index))
    }

    fun back(view: View) {
        this.index = decrementBackSlide()
        updateTitleAndContent(this.slides.elementAt(this.index))
    }

    private fun updateTitleAndContent(currentSlide: Slide) {
        this.title = currentSlide.title
        val textView: TextView = findViewById(R.id.textView)
        textView.text = currentSlide.getContent()
    }

    private fun incrementNextSlide(): Int {
        return if (this.index < this.slides.size - 1) {
            ++this.index
        } else {
            this.slides.size - 1
        }
    }

    private fun decrementBackSlide(): Int {
        return if (this.index > 0) {
            --this.index
        } else {
            0
        }
    }
}
