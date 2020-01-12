package net.adrote.lipotest.model


import com.google.gson.annotations.SerializedName
import net.adrote.lipotest.model.Films

data class FilmQuery(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: Any?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val films: List<Films?>?
)