package br.pro.evslopes.maedaprole

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationApp.setupWithNavController(findNavController(R.id.navHostFragment))

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationApp)
        bottomNavigationView.visibility = View.GONE

        MobileAds.initialize(this)

        val mAdView = this.findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()

        mAdView.loadAd(adRequest)
    }
}