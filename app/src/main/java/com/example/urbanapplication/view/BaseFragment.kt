package com.example.urbanapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.urbanapplication.R
import com.example.urbanapplication.extensions.isTablet
import com.example.urbanapplication.model.Definition

abstract class BaseFragment : Fragment() {
    companion object {
        const val DEFINITION_KEY = "DEFINITION_KEY"
    }

    open val tabletController: NavController? = null

    fun navigateToDetailScreen(definition: Definition) {
        val bundle = Bundle().apply { putParcelable(DEFINITION_KEY, definition) }
        when (isTablet()) {
            true -> tabletController?.navigate(R.id.detail_fragment, bundle)
            false -> findNavController().navigate(R.id.detail_fragment, bundle)
        }
    }

    open inner class ClickHandler {
        fun onBack() {
            when (isTablet()) {
                true -> tabletController?.navigateUp()
                false -> findNavController().navigateUp()
            }
        }

        open var filter: () -> Unit = {}
        open var clearSearch: () -> Unit = {}
    }
}