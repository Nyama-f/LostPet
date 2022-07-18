package com.example.lostpet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import com.example.lostpet.databinding.CommonPetCardViewBinding

class CommonPetAdapter: RecyclerView.Adapter<CommonPetAdapter.CommonPetCardViewHolder>() {

    private val pets: MutableList<Pet> = mutableListOf()
    private val users: MutableList<User> = mutableListOf()

    var cardImageClickListener: ((latitude: String, longitude: String) -> Unit)? = null

    fun setList(listPet: List<Pet>) {
        pets.clear()
        pets.addAll(listPet)
        notifyDataSetChanged()
    }

    fun setListUsers(listUsers: List<User>){
        users.clear()
        users.addAll(listUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonPetCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommonPetCardViewBinding.inflate(inflater, parent, false)

        return CommonPetCardViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CommonPetCardViewHolder,
        position: Int
    ) {
        val item = pets[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = pets.size

    inner class  CommonPetCardViewHolder(private val binding: CommonPetCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(pet: Pet) {
            //TODO Надо бы исправить данное безобразие, сообразив как добавить Pet имя Автора
            for(user in users){
                if (pet.petUserId.toString() == user.userId) binding.textAuthorPet.text = user.userName
            }
            with(binding) {
                textTypePet.text = pet.petType
                textColorPet.text = pet.petColor
                //textAuthorPet.text = pet.petUserId.toString()
                containerCard.setOnClickListener { cardImageClickListener?.invoke(pet.petLatitude, pet.petLongitude) }
            }
        }
    }
}