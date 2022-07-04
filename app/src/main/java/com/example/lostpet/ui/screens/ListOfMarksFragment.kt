package com.example.lostpet.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentAccountBinding
import com.example.lostpet.databinding.FragmentListOfMarksBinding
import com.example.lostpet.ui.adapters.CommonPetAdapter
import com.example.lostpet.ui.adapters.decorators.FeedVerticalDividerItemDecoration
import com.example.lostpet.ui.viewmodels.ListOfMarksViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.appComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class ListOfMarksFragment : Fragment() {

    private var _binding: FragmentListOfMarksBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<ListOfMarksViewModel> { viewModelFactory }
    private val commonPetAdapter: CommonPetAdapter by lazy { CommonPetAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfMarksBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getUsers()
        //viewModel.getPets()
        viewModel.getX()
        with(binding){
            commonPetList.adapter = commonPetAdapter
            commonPetList.layoutManager = LinearLayoutManager(requireContext())
            commonPetList.addItemDecoration(
                FeedVerticalDividerItemDecoration(16, commonPetAdapter.itemCount))
        }
        val progressBar = binding.progressBar
        progressBar.isVisible = true
//        lifecycleScope.launchWhenResumed {
//            viewModel.users.collect{
//                viewModel.pets.collect{
//                    commonPetAdapter.setList(it)
//                    progressBar.isVisible = false
//                }
//            }
//        }
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
//                launch {
//                    viewModel.users.collect{}
//                }
//                launch {
//                    viewModel.pets.collect{
//                        commonPetAdapter.setList(it)
//                        progressBar.isVisible = false
//
//                    }
//                }
//            }
//        }
        lifecycleScope.launch{
            viewModel.pets.collect{
                commonPetAdapter.setList(it)
                delay(1900)
                progressBar.isVisible = false
            }
        }

        //commonPetAdapter.setList(viewModel.pets.value)
    }

}