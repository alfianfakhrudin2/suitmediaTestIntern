package com.example.suitmediaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediaapp.R
import com.example.suitmediaapp.data.model.User
import com.example.suitmediaapp.databinding.ItemUserBinding

class UserAdapter(
    private val users: List<User>,
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvName.text = getUserFullName(user)
                tvEmail.text = user.email
                loadUserAvatar(user.avatar)
                root.setOnClickListener { onItemClick(user) }
            }
        }

        private fun getUserFullName(user: User): String {
            return "${user.first_name} ${user.last_name}"
        }

        private fun loadUserAvatar(avatarUrl: String?) {
            Glide.with(itemView.context)
                .load(avatarUrl)
                .placeholder(R.drawable.avatar)
                .into(binding.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}
