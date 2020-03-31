package com.example.urbanapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.urbanapplication.databinding.FragmentDetailBinding
import com.example.urbanapplication.model.Definition

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : BaseFragment() {

    val definition: Definition? by lazy {
        arguments?.getParcelable<Definition>(DEFINITION_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentDetailBinding.inflate(inflater, container, false).apply {
            definition = this@DetailFragment.definition
        }.root


}
