package com.example.quranme.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranme.model.Surat
import com.example.quranme.model.SuratResponse
import com.example.quranme.network.ApiService
import com.example.quranme.utils.UiState
import kotlinx.coroutines.launch

class SuratViewModel : ViewModel() {
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
                val response = ApiService.getInstance().getAllSurat()
                _suratListResponse.postValue(UiState.Success(data = response.data))
            } catch (e: Exception) {
                _suratListResponse.postValue(UiState.Error(error = e.message))
            }
        }
    }

    fun getSuratByNumber(number: Int) {
        viewModelScope.launch {
            try {
                val surat = ApiService.getInstance().getSuratByNumber(number)
                _selectedSurat.postValue(UiState.Success(data = surat))
            } catch (e: Exception) {
                _selectedSurat.postValue(UiState.Error(error = e.message))
            }
        }
    }
}
