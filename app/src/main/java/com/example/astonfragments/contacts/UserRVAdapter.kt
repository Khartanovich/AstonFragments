package com.example.astonfragments.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfragments.databinding.ItemRvWithoutCheckBinding

class UserRVAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<UserRVAdapter.UserViewHolderWithoutCheck>() {
    private var data: List<User> = emptyList()

    fun setData(listUser: List<User>) {
        val diffCallback = UsersDiffUtilCallback(data, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data = listUser
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolderWithoutCheck {
        val binding = ItemRvWithoutCheckBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolderWithoutCheck(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolderWithoutCheck, position: Int) {
        val item = data[position]
        with(holder.binding) {
            tvId.text = item.id.toString()
            tvName.text = item.name
            tvPhoneNumber.text = item.phoneNumber
            root.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }

    class UserViewHolderWithoutCheck(val binding: ItemRvWithoutCheckBinding) :
        RecyclerView.ViewHolder(binding.root)
}

interface ClickListener {
    fun onItemClick(user: User)
}