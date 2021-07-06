package com.gmail.progstrl.vetrf2.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.progstrl.vetrf2.database.BaseName
import com.gmail.progstrl.vetrf2.database.DatabaseVetrf
import kotlinx.coroutines.launch

class BaseNameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BaseNameRepository

    init {
        val baseDao = DatabaseVetrf.getDatabase(application,viewModelScope).baseDao()
        repository = BaseNameRepository(baseDao)
    }

    fun getAllBaseName(): LiveData<List<BaseName>> {
        return repository.getAllBaseName();
    }

    fun insertBaseName(baseName: BaseName) = viewModelScope.launch {
        repository.insertBaseName(baseName)
    }
}