package com.example.technical_assessment_option1.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.technical_assessment_option1.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}