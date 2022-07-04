package com.example.lostpet.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
import com.google.android.material.snackbar.Snackbar
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
        viewModel.getPets()
        viewModel.getUser()
        lifecycleScope.launchWhenResumed {
            viewModel.pets.collect{
                petAdapter.setList(it)
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
            Log.d("DelUser", "В AccountFragment ${petId}")
        }
        binding.btnEdit.setOnClickListener {
//            val contextView = binding.btnEdit
//            val snackBar =  Snackbar.make(contextView, R.string.editMark, Snackbar.LENGTH_LONG)
//                .setAnchorView(binding.petList)
//                .setAction("Позвонить"){
//                    val intent = Intent(
//                        Intent.ACTION_DIAL, Uri.parse("tel:" + "+79514462853"))
//                    startActivity(intent)
//                }
//            snackBar.show()
            jokeDialog.show(MAIN.supportFragmentManager, "jokeDialog")
        }
        binding.btnDelete.setOnClickListener {
            viewModel.outOfAccount()
            Log.d("User", "${MAIN.prefs.getInt("currentUserId", 0)}")
            activityNavController().navigateSafely(R.id.action_global_signFlowFragment)
        }
    }

}