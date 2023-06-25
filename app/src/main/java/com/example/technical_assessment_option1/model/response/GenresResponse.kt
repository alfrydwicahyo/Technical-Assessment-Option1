package com.example.technical_assessment_option1.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres") @Expose var genres: MutableList<GenreResponse>
)

data class GenreResponse(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String
)