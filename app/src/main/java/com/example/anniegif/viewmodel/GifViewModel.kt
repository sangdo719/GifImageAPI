package com.example.anniegif.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.anniegif.model.GifInfo
import com.example.anniegif.model.GifModel
import com.example.anniegif.repo.GifRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class GifViewModel: ViewModel() {
    private val _gifModelObj = MutableLiveData<List<GifInfo>>()
    val gifModelObj: MutableLiveData<List<GifInfo>> get() = _gifModelObj

    fun fetchData(path: String, amount: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = GifRepo.getGif(path, amount)
            val fetchedData = if (response.isSuccessful && response.body() != null)
                response.body()
            else GifModel(listOf(GifInfo("https://nekos.best/api/v1/baka/001.gif",
                "Failed to fetch")))
            fetchedData.let{ _gifModelObj.postValue(it?.url) }
        }
    }

    val categories = liveData {
        try{
            emit(GifRepo.getCategories())
        } catch(ex: Exception){
            Log.e("GifViewModel","Error: ${ex.message}")
        }
    }
}