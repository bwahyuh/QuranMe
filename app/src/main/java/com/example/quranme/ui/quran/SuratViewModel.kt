package com.example.quranme.ui.quran

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quranme.data.model.Surat
import com.example.quranme.repo.remote.network.ApiService
import com.example.quranme.compose.state.UiState
import kotlinx.coroutines.launch

class SuratViewModel(private val context: Context) : ViewModel() {
    private val _suratListResponse = MutableLiveData<UiState<List<Surat>>>()
    val suratListResponse: LiveData<UiState<List<Surat>>> = _suratListResponse

    private val _selectedSurat = MutableLiveData<UiState<Surat>>()
    val selectedSurat: LiveData<UiState<Surat>> = _selectedSurat

    init {
        getAllSurahs()
    }

    private fun getAllSurahs() {
        viewModelScope.launch {
            _suratListResponse.value = UiState.Loading
            try {
                val response = ApiService.getInstance(context).getAllSurat()
                _suratListResponse.postValue(UiState.Success(data = response.data))
            } catch (e: Exception) {
                _suratListResponse.postValue(UiState.Error(error = e.message))
            }
        }
    }

//    fun getSuratByNumber(number: Int) {
//        viewModelScope.launch {
//            try {
//                val surat = ApiService.getInstance().getSuratByNumber(number)
//                _selectedSurat.postValue(UiState.Success(data = surat))
//            } catch (e: Exception) {
//                _selectedSurat.postValue(UiState.Error(error = e.message))
//            }
//        }
//    }



}

class SuratViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuratViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuratViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
