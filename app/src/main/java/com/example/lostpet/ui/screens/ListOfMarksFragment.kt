package com.example.lostpet.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostpet.data.model.Pet
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
        viewModel.getUsers()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.getPets()
            }
        }

        val progressBar = binding.progressBar
        progressBar.isVisible = true

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED){
//                var listPet = mutableListOf<Pet>()
//                viewModel.pets.collect{
//                    for(pet in it){
//                        listPet.add(pet)
//                    }
//                    Log.d("Nyama", "Список животных ${listPet}")
//                    commonPetAdapter.setList(listPet)
//                    delay(1900)
//                    progressBar.isVisible = false
//                }
//            }
//        }

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.users.collect{
                    viewModel.getUsers()
                    Log.d("Nyama", "Список юзеров ${viewModel.users.value}")
                }
            }
        }
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.pets.collect{
                    delay(1900)
                    //TODO Сообразить как можно сделать лучше
                    viewModel.getPets()
                    delay(1900)
                    Log.d("Nyama", "Список питомцев ${viewModel.pets.value}")
                    commonPetAdapter.setList(it)
                    progressBar.isVisible = false
                }
            }
        }

        with(binding){
            commonPetList.adapter = commonPetAdapter
            commonPetList.layoutManager = LinearLayoutManager(requireContext())
            commonPetList.addItemDecoration(
                FeedVerticalDividerItemDecoration(16, commonPetAdapter.itemCount)
            )
        }

    }

}