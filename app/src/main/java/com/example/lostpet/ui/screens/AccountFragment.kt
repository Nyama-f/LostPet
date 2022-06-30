package com.example.lostpet.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentAccountBinding
import com.example.lostpet.databinding.FragmentMapsBinding
import com.example.lostpet.ui.adapters.PetCardAdapter
import com.example.lostpet.ui.adapters.decorators.FeedVerticalDividerItemDecoration
import com.example.lostpet.ui.viewmodels.AccountViewModel
import com.example.lostpet.ui.viewmodels.MapViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.appComponent
import javax.inject.Inject

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<AccountViewModel> { viewModelFactory }
    private val petAdapter: PetCardAdapter by lazy { PetCardAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
        viewModel.getPets()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            petList.adapter = petAdapter
            petList.layoutManager = LinearLayoutManager(requireContext())
            petList.addItemDecoration(
                FeedVerticalDividerItemDecoration(16, petAdapter.itemCount)
            )
        }
        petAdapter.setList(viewModel.pets.value)
        for(i in viewModel.pets.value){
            Log.d("PetList", "${i}")
        }
    }

}