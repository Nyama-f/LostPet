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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostpet.R
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
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.getPets()
//            }
//        }

        val progressBar = binding.progressBar
        val rv = binding.commonPetList
        rv.visibility = View.GONE
        progressBar.isVisible = true

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.users.collect{
                    viewModel.getUsers()
                    commonPetAdapter.setListUsers(it)
                    Log.d("Nyama", "???????????? ???????????? ${viewModel.users.value}")
                }
            }
        }
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.pets.collect{
                    delay(1900)
                    //TODO ???????????????????? ?????? ?????????? ?????????????? ??????????(?????????????????? ?? ??????)
                    viewModel.getPets()
                    Log.d("Nyama", "???????????? ???????????????? ${viewModel.pets.value}")
                    commonPetAdapter.setList(it)
                    rv.visibility = View.VISIBLE
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
            commonPetAdapter.cardImageClickListener = { latitude, longitude ->
                val bundle = Bundle()
                bundle.putString("longitude", longitude)
                bundle.putString("latitude", latitude)
                bundle.putBoolean("fromListOfMarks", true)
                findNavController().navigate(R.id.action_listOfMarksFragment_to_mapFragment, bundle)
            }
        }

    }

}