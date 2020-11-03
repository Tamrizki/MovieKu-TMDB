package tam.pa.moviekuapp.model.response

import com.google.gson.annotations.SerializedName
import tam.pa.moviekuapp.model.GenreItem

data class DetailMovieResponse (
        @SerializedName("backdrop_path") val backdrop_path : String?,
        @SerializedName("genres") val genres : List<GenreItem>?,
        @SerializedName("id") val id : Int?,
        @SerializedName("original_language") val original_language : String?,
        @SerializedName("original_title") val original_title : String?,
        @SerializedName("overview") val overview : String?,
        @SerializedName("poster_path") val poster_path : String?,
        @SerializedName("release_date") val release_date : String?,
        @SerializedName("runtime") val runtime : Int?,
        @SerializedName("status") val status : String?,
        @SerializedName("tagline") val tagline : String?,
        @SerializedName("title") val title : String?,
        @SerializedName("video") val video : Boolean,
        @SerializedName("vote_average") val vote_average : Double?,
        @SerializedName("vote_count") val vote_count : Int?
)