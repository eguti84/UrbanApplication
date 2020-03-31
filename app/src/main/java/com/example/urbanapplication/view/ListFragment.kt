package com.example.urbanapplication.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbanapplication.R
import com.example.urbanapplication.adapter.DefinitionAdapter
import com.example.urbanapplication.databinding.FragmentListBinding
import com.example.urbanapplication.extensions.toggleVisibility
import com.example.urbanapplication.model.Definition
import com.example.urbanapplication.model.UrbanResponse
import com.example.urbanapplication.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar.*
import kotlinx.android.synthetic.main.snippet_toolbar.view.*
import javax.inject.Inject

class ListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment() {

    override val tabletController: NavController? by lazy {
        (activity?.frag_detail_container as InjectingNavHostFragment).navController
    }
    private val vm by navGraphViewModels<MyViewModel>(R.id.list_fragment) { viewModelFactory }
    private lateinit var binding: FragmentListBinding
    private lateinit var definitionAdapter: DefinitionAdapter

    var query: CharSequence? = ""

    private val definitionClicked = View.OnClickListener {
        val definition = it.tag as Definition
        navigateToDetailScreen(definition)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            // NO-OP
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // NO-OP
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val isBackspace = query?.length ?: -1 >= p0?.length ?: -1
            query = p0.toString()
            if (p0?.length == 0)
                binding.noQueryView.root.toggleVisibility(definitionAdapter.currentList.isEmpty())

            vm.showClear.set(!p0.isNullOrEmpty())
            p0?.let {
                if (it.isNotEmpty() && !isBackspace)
                    vm.fetchDefinition(it.toString())
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentListBinding.inflate(inflater, container, false).apply {
        definitionAdapter = DefinitionAdapter(definitionClicked)
        rvDefinition.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = definitionAdapter
        }
    }.also { binding = it }.root

    override fun onStart() {
        super.onStart()
        vm.urbanResponse.observe(viewLifecycleOwner) { loadDefinitions(it) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).apply {
            binding?.viewModel = vm
            binding?.clickHandler = ClickHandler().apply {
                filter = { binding?.root?.listToolbarFilter?.let { showSortPopup(it) } }
                clearSearch = { listToolbarQuery.text?.clear() }
            }

            activity?.listToolbarQuery?.addTextChangedListener(textWatcher)
        }
    }

    private fun showSortPopup(filter: AppCompatImageView) {
        vm.urbanResponse.value?.list?.let {
            PopupMenu(filter.context, filter).apply {
                menuInflater.inflate(R.menu.popup_filter, menu)
                setOnMenuItemClickListener { menuItem ->
                    val sortedList: List<Definition> = when (menuItem.itemId) {
                        R.id.action_sort_by_date -> {
                            it.toMutableList().apply { sortBy { it.writtenOn } }.toList()
                        }
                        R.id.action_sort_by_up_votes_ascending -> {
                            it.toMutableList().apply { sortBy { it.thumbsUp } }.toList()
                        }
                        R.id.action_sort_by_up_votes_descending -> {
                            it.toMutableList().apply { sortByDescending { it.thumbsUp } }.toList()
                        }
                        R.id.action_sort_by_down_votes_ascending -> {
                            it.toMutableList().apply { sortBy { it.thumbsDown } }.toList()
                        }
                        R.id.action_sort_by_down_votes_descending -> {
                            it.toMutableList().apply { sortByDescending { it.thumbsDown } }.toList()
                        }
                        else -> it
                    }
                    definitionAdapter.submitList(sortedList)
                    true
                }
            }.show()
        }
    }

    private fun loadDefinitions(urbanResponse: UrbanResponse) {
        val definitions = urbanResponse.list
        definitionAdapter.submitList(definitions)
        binding.noResultView.root.toggleVisibility(definitions.isNullOrEmpty())
        binding.noQueryView.root.toggleVisibility(show = false)
        binding.noResultView.tvTitle.text = getString(R.string.no_results_found_for_s, query)
    }
}
