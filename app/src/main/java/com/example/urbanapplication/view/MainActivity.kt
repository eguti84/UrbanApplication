package com.example.urbanapplication.view

import android.content.SharedPreferences
import android.os.Bundle
import com.example.urbanapplication.R
import com.example.urbanapplication.databinding.ActivityMainBinding
import com.example.urbanapplication.extensions.extendViewUnderStatusBar
import com.example.urbanapplication.extensions.isTablet
import com.example.urbanapplication.extensions.toggleVisibility
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(root)
        binding?.let {
            setContentView(it.root)
            extendViewUnderStatusBar()
        }
        setupToolBarStates()
    }

    private fun setupToolBarStates() {
        if (!isTablet()) {
            (frag_master_container as InjectingNavHostFragment).navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.list_fragment -> toggleToolBarVisibility(
                        showBack = false,
                        showSearch = true,
                        showFilter = true
                    )
                    R.id.detail_fragment -> toggleToolBarVisibility(true)
                }
            }
        }
    }

    private fun toggleToolBarVisibility(
        showBack: Boolean? = null,
        showSearch: Boolean? = null,
        showFilter: Boolean? = null
    ) {
        listToolbarClose.toggleVisibility(showBack)
        listToolbarQuery.toggleVisibility(showSearch)
        listToolbarSearch.toggleVisibility(showSearch)
        listToolbarFilter.toggleVisibility(showFilter)
    }
}
