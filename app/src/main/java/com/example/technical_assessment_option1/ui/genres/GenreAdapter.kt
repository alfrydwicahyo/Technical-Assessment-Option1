package com.example.technical_assessment_option1.ui.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_assessment_option1.databinding.ItemGenreBinding
import com.example.technical_assessment_option1.domain.genres.entity.GenresEntity

class GenreAdapter(private var genresResponse: MutableList<GenresEntity>, private var onCallback: OnCallback) :
    RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    interface OnCallback {
        fun onItemClick(id: Int)
    }

    inner class GenreHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val binding = ItemGenreBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        with(holder) {
            binding.genre.text = genresResponse[position].name
            binding.layoutCvImpian.setOnClickListener {
                onCallback.onItemClick(genresResponse[position].id)
            }
        }
    }

    override fun getItemCount(): Int {
        return genresResponse.size
    }
}