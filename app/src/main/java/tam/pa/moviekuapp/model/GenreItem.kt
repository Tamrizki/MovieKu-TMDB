package tam.pa.moviekuapp.model

import com.google.gson.annotations.SerializedName

data class GenreItem(
        @SerializedName("id") val id : Int,
        @SerializedName("name") val name : String
)