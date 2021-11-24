package com.wasilyk.mycoroutine

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class CommentViewModel(
    private val dataSource: DataSource = DataSourceImpl()
) : ViewModel() {

    private val _liveData = MutableLiveData<List<Comment>>()
    val liveData = _liveData as LiveData<List<Comment>>

    init {
        viewModelScope.launch {
            getComments()
        }
    }

    suspend fun getComments() =
        dataSource.getData().collect {
            _liveData.postValue(it)
        }

    fun insert(comment: Comment) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.insert(comment)
            }
        }

    fun delete(comment: Comment) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.delete(comment)
            }
        }

    fun selectMaxId(): LiveData<Int> {
        return liveData(Dispatchers.IO) {
            dataSource.selectMaxId()
        }
    }

}

