package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String> = mutableListOf()
) : Parcelable {
    fun getFormattedShoeSize() = "US ${String.format("%.1f", size)}"

    // text size for 2-way data binding for detail form
    @IgnoredOnParcel
    var textSize: String = ""
        get() = if (size == 0.0) "" else size.toString()
        set(value) {
            field = value
            size = value.toDoubleOrNull() ?: 0.0
        }
}

fun getEmptyShoe() = Shoe(
    name = "",
    size = 0.0,
    company = "",
    description = ""
)