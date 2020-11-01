package tam.pa.moviekuapp.model.response

import com.google.gson.annotations.SerializedName
import tam.pa.moviekuapp.model.MovieListItem

data class DataResponse(
    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("results") val results : List<MovieListItem>
)
