package tam.pa.moviekuapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SendListItem(
    @SerializedName("id") val id : Int,
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("video") val video : Boolean,
    @SerializedName("original_language") val original_language : String?)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(backdrop_path)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeString(original_language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SendListItem> {
        override fun createFromParcel(parcel: Parcel): SendListItem {
            return SendListItem(parcel)
        }

        override fun newArray(size: Int): Array<SendListItem?> {
            return arrayOfNulls(size)
        }
    }
}