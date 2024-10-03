package com.example.studentproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var bod:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
) : Parcelable
