package co.za.chester.functionalkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import co.za.chester.functionalkotlin.domain.AboutMeSlide
import co.za.chester.functionalkotlin.domain.BaseSlide
import co.za.chester.functionalkotlin.domain.Constants.HTML_CONTENT

class MainActivity : AppCompatActivity() {
    private var currentSlide: BaseSlide = AboutMeSlide
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        val webView: WebView = findViewById(R.id.webView)
        webView.loadData(HTML_CONTENT.replace("@{mark-down-content}", slide.content()), null, null)
    }
}
