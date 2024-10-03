package com.example.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentproject.model.Car
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class CarViewModel(application: Application) : AndroidViewModel(application) {
    val carsLD = MutableLiveData<ArrayList<Car>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    // Fungsi untuk memuat data dari server (atau sumber data lainnya)
    fun refresh() {
        carLoadErrorLD.value = false
        loadingLD.value = true

        // URL di mana JSON mobil tersedia
        val url = "https://raw.githubusercontent.com/Tayoo71/StudentProject_ANMP/refs/heads/master/cars.json"

        try{
            queue = Volley.newRequestQueue(getApplication())
            val stringRequest = StringRequest(
                Request.Method.GET, url, { response ->
                    // Jika berhasil mengambil data
                    val sType = object : TypeToken<ArrayList<Car>>() {}.type
                    val result = Gson().fromJson<ArrayList<Car>>(response, sType)
                    carsLD.value = result
                    loadingLD.value = false
                }, { error ->
                    // Jika gagal mengambil data
                    Log.d("showVolley", error.toString())
                    carLoadErrorLD.value = true
                    loadingLD.value = false
                }
            )

            stringRequest.tag = TAG
            queue?.add(stringRequest)
        } catch (e: JsonSyntaxException) {
            // Handle single object or other response types
            Log.e("GsonError", "Error parsing JSON", e)
        }
    }

    // Fungsi ini akan dipanggil ketika ViewModel dihapus dari memori
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
