package com.example.suitmediaapp.presentation.secondScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import com.example.suitmediaapp.databinding.ActivitySecondBinding
import com.example.suitmediaapp.presentation.thirdScreen.Third_Activity


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val chooseUserLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        handleUserSelectionResult(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    private fun initializeUI() {
        displayUserName()
        setupListeners()
    }

    private fun displayUserName() {
        val userName = intent.getStringExtra("USER_NAME")
        binding.tvUnameSecond.text = userName
    }

    private fun setupListeners() {
        binding.btnBackSecond.setOnClickListener {
            finish()
        }

        binding.btnChooseUser.setOnClickListener {
            openThirdActivityForResult()
        }
    }

    private fun openThirdActivityForResult() {
        val intent = Intent(this, Third_Activity::class.java)
        chooseUserLauncher.launch(intent)
    }

    private fun handleUserSelectionResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val selectedUserName = result.data?.getStringExtra("SELECTED_USER_NAME")
            binding.tvSelectUser.text = selectedUserName
        }
    }
}
