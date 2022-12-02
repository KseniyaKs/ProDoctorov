package com.example.prodoctorov.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.prodoctorov.R
import com.example.prodoctorov.presentation.main_app.user.SearchSpecialistFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SearchSpecialistFragment.newInstance())
            .commit()
    }
}

