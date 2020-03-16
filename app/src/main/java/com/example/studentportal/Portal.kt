package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    var portalTitle: String,
    var portalUrl:String

): Parcelable