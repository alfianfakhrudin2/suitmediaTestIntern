package com.example.suitmediaapp.presentation.thirdScreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediaapp.data.api.ApiConfig
import com.example.suitmediaapp.data.model.User
import com.example.suitmediaapp.data.response.ResponseUser
import com.example.suitmediaapp.databinding.ActivityThirdBinding
import com.example.suitmediaapp.presentation.adapter.UserAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Third_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var userAdapter: UserAdapter
    private val users = mutableListOf<User>()
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchUsers()

        binding.rvThird.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    fetchUsers()
                }
            }
        })

        binding.btnBackThird.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(users) { user ->
            onUserSelected(user)
        }
        binding.rvThird.layoutManager = LinearLayoutManager(this)
        binding.rvThird.adapter = userAdapter
    }

    private fun fetchUsers() {
        val apiService = ApiConfig.getApiService()
        val call = apiService.getUsers(currentPage, 10)

        call.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                response.body()?.let {
                    if (it.data.isNotEmpty()) {
                        users.addAll(it.data)
                        userAdapter.notifyDataSetChanged()
                        currentPage++
                    } else {
                        showEmptyState()
                    }
                } ?: run {
                    showEmptyState()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@Third_Activity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onUserSelected(user: User) {
        val resultIntent = Intent().apply {
            putExtra("SELECTED_USER_NAME", "${user.first_name} ${user.last_name}")
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun showEmptyState() {
        Toast.makeText(this, "No more users available", Toast.LENGTH_SHORT).show()
    }
}
