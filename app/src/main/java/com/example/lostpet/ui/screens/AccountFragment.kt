package com.example.lostpet.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentAccountBinding
import com.example.lostpet.ui.adapters.PetCardAdapter
import com.example.lostpet.ui.adapters.decorators.FeedVerticalDividerItemDecoration
import com.example.lostpet.ui.dialogfragments.JokeDialog
import com.example.lostpet.ui.viewmodels.AccountViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.Consts.MAIN
import com.example.lostpet.utils.activityNavController
import com.example.lostpet.utils.appComponent
import com.example.lostpet.utils.navigateSafely
import javax.inject.Inject


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var jokeDialog: DialogFragment = JokeDialog()

    private val viewModel by viewModels<AccountViewModel> { viewModelFactory }
    private val petAdapter: PetCardAdapter by lazy { PetCardAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
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
        val progressBar = binding.progressBar
        progressBar.isVisible = true
        viewModel.getPets()
        viewModel.getUser()
        lifecycleScope.launchWhenResumed {
            viewModel.pets.collect{
                petAdapter.setList(it)
                progressBar.isVisible = false
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.user.collect{
                with(binding){
                    userName.text = viewModel.user.value.userName
                    userPhone.text = viewModel.user.value.userPhone
                }
            }
        }

        with(binding){
            userName.text = viewModel.user.value.userName
            userPhone.text = viewModel.user.value.userPhone
            petList.adapter = petAdapter
            petList.layoutManager = LinearLayoutManager(requireContext())
            petList.addItemDecoration(
                FeedVerticalDividerItemDecoration(16, petAdapter.itemCount)
            )
        }
        petAdapter.cardButtonDelClickListener = { petId ->
            viewModel.deletePet(petId)
        }
        binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("userLogin", viewModel.user.value.userLogin)
            bundle.putString("userName", viewModel.user.value.userName)
            bundle.putString("userPhone", viewModel.user.value.userPhone)
            findNavController().navigate(R.id.action_accountFragment_to_editUserFragment, bundle)
            //jokeDialog.show(MAIN.supportFragmentManager, "jokeDialog")
        }
        binding.btnDelete.setOnClickListener {
            viewModel.outOfAccount()
            //Log.d("Nyama", " ?????????????????? ???????????????? ID ${MAIN.prefs.getInt("currentUserId", 0)}")
            activityNavController().navigateSafely(R.id.action_global_signFlowFragment)
        }
    }

}