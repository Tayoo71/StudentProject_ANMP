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


data class Car(
    val id: Int,
    val make: String,
    val model: String,
    val year: Int,
    val color: String,
    val price: Int,
    val features: List<String>,
    val specs: CarSpecifications
)

data class CarSpecifications(
    val engine: String,
    val transmission: String,
    val fuel_type: String
)
