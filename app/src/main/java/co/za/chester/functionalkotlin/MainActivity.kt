package co.za.chester.functionalkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import co.za.chester.functionalkotlin.domain.AboutMeSlide
import co.za.chester.functionalkotlin.domain.BaseSlide
import es.dmoral.markdownview.MarkdownView

class MainActivity : AppCompatActivity() {
    private var currentSlide: BaseSlide = AboutMeSlide
    private lateinit var markdownView: MarkdownView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        markdownView = this.findViewById(R.id.markdown_view)
        updateTitleAndContent(currentSlide)
    }

    fun next(view: View) {
        currentSlide = currentSlide.next()
        updateTitleAndContent(currentSlide)
    }

    fun back(view: View) {
        currentSlide = currentSlide.back()
        updateTitleAndContent(currentSlide)
    }

    private fun updateTitleAndContent(slide: BaseSlide) {
        this.title = slide.title
        markdownView.loadFromText(slide.content())
    }
}
