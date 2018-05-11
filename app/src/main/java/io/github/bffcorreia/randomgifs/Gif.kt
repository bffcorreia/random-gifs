package io.github.bffcorreia.randomgifs

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gif(var title: String, var url: String) : Parcelable