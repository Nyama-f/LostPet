package com.example.lostpet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lostpet.data.model.Pet
import com.example.lostpet.databinding.PetCardViewBinding

class PetCardAdapter: RecyclerView.Adapter<PetCardAdapter.PetCardViewHolder>() {
    private val pets: MutableList<Pet> = mutableListOf()

    var cardImageClickListener: (() -> Unit)? = null

    fun setList(list: List<Pet>) {
        pets.clear()
        pets.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PetCardViewBinding.inflate(inflater, parent, false)

        return PetCardViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PetCardViewHolder,
        position: Int
    ) {
        val item = pets[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = pets.size

    inner class  PetCardViewHolder(private val binding: PetCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(pet: Pet) {
            with(binding) {
                textTypePet.text = pet.petType
                textColorPet.text = pet.petColor
                containerCard.setOnClickListener { cardImageClickListener?.invoke() }
            }
        }
    }
}