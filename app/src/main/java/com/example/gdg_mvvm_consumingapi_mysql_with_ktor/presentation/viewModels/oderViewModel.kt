package com.prospect.fatuapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prospect.fatuapp.models.Oder_Product
import com.prospect.fatuapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class oderViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Oder_Product>>(emptyList())
    val noteList = _noteList.asStateFlow()
    //private var noteList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllOder_Products().distinctUntilChanged()
                .collect { listOfNotes ->
                     if (listOfNotes.isNullOrEmpty()) {
                         Log.d("Empty", ": Empty list")
                     }else {
                         _noteList.value = listOfNotes
                     }

                }

        }
       // noteList.addAll(NotesDataSource().loadNotes())
    }

     fun addNote(note: Oder_Product) = viewModelScope.launch { repository.addOder_Product(note) }
     fun updateNote(note: Oder_Product) = viewModelScope.launch { repository.updateOder_Product(note) }
     fun removeNote(note: Oder_Product) = viewModelScope.launch { repository.deleteOder_Product(note) }

}