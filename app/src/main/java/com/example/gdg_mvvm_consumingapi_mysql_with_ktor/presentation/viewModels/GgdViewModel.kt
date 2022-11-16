package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgCommon.Resource
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.repository.DomainRepository
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GgdViewModel @Inject constructor(private val domainRepository: DomainRepository) : ViewModel() {

    private val _state = mutableStateOf(PostsState())
    val state: State<PostsState> = _state

    init {
        domainRepository.getgdgmembers().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostsState(posts = result.data!!, loading = false, error = null)
                }

                is Resource.Loading -> {
                    _state.value = PostsState(posts = null, loading = true, error = null)
                }

                is Resource.Error -> {
                    _state.value = PostsState(posts = null, loading = false, error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun add(name:String,description:String) = viewModelScope.launch { domainRepository.addgdgmembers(name,description) }
    fun update(id: String,name:String, description:String) = viewModelScope.launch { domainRepository.editgdgmembers(id,name,description) }
    fun remove(id:String) = viewModelScope.launch { domainRepository.Detelegdgmembers(id) }


}