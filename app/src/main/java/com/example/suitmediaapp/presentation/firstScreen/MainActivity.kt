package com.example.suitmediaapp.presentation.firstScreen

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediaapp.R
import com.example.suitmediaapp.databinding.ActivityMainBinding
import com.example.suitmediaapp.presentation.secondScreen.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Access the views using binding
        val etName = binding.tvName
        val etPalindrome = binding.tvPalindrome
        val checkButton = binding.btnCheck
        val nextButton = binding.btnNext

        checkButton.setOnClickListener {
            val inputText = etPalindrome.text.toString()

            if (inputText.isEmpty()) {
                Toast.makeText(this, "Please fill the palindrome field", Toast.LENGTH_SHORT).show()
            } else {
                val result = if (isPalindrome(inputText)) "isPalindrome" else "not palindrome"

                val dialog = AlertDialog.Builder(this)
                    .setMessage(result)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()

                dialog.show()
            }
        }

        nextButton.setOnClickListener {
            val userName = etName.text.toString()
            if (userName.isEmpty()) {
                // for showing toast message when name field is empty
                Toast.makeText(this, "Please fill the name field", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("USER_NAME", userName) // for showing user name in second activity
                }
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanedText = text.replace("\\s".toRegex(), "")
        return cleanedText.equals(cleanedText.reversed(), ignoreCase = true)
    }
}
