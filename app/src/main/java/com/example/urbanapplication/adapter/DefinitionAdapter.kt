package com.example.urbanapplication.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.urbanapplication.R
import com.example.urbanapplication.adapter.util.DataBoundListAdapter
import com.example.urbanapplication.databinding.DefinitionItemBinding
import com.example.urbanapplication.extensions.bind
import com.example.urbanapplication.model.Definition

class DefinitionAdapter(private val onClick: View.OnClickListener) :
    DataBoundListAdapter<Definition>(
        diffCallback = object : DiffUtil.ItemCallback<Definition>() {
            override fun areItemsTheSame(oldItem: Definition, newItem: Definition): Boolean =
                oldItem.defid == newItem.defid

            override fun areContentsTheSame(oldItem: Definition, newItem: Definition): Boolean =
                oldItem == newItem
        }
    ) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        parent.bind(R.layout.definition_item)

    override fun bind(binding: ViewDataBinding, item: Definition) {
        when (binding) {
            is DefinitionItemBinding -> binding.definition = item
        }
        binding.root.tag = item
        binding.root.setOnClickListener(onClick)
    }
}