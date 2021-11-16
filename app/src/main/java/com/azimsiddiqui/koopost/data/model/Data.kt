package com.azimsiddiqui.koopost.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val body: String,
    val id: Int,
    val title: String,
    val user_id: Int
) : Parcelable