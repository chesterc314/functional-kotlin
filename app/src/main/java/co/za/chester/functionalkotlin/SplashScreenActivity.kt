package co.za.chester.functionalkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import gr.net.maroulis.library.EasySplashScreen

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val easySplashScreenView: View = EasySplashScreen(this)
                .withFullScreen()
                .withTargetActivity(MainActivity::class.java)
                .withSplashTimeOut(3000)
                .withBackgroundResource(android.R.color.black)
                .withLogo(R.mipmap.lambda_luminaries)
                .create()

        setContentView(easySplashScreenView)
    }
}
