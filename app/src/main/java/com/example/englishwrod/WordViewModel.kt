package com.example.englishwrod

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishwrod.data.WordEntity
import com.example.englishwrod.data.WordRepository
import kotlinx.coroutines.launch
import java.util.Observable

class WordViewModel (val wordRepository: WordRepository) : ViewModel() , androidx.databinding.Observable  {
    val allWord = wordRepository.allWord


    @Bindable
    var inputWord_English = MutableLiveData<String>()!!
    @Bindable
    var inputWord_Persian = MutableLiveData<String>()!!
    @Bindable
    var save_update_Button = MutableLiveData<String>()



    fun saveOrUpdate(){
        if (inputWord_English.value?.isNotEmpty()!! && inputWord_Persian.value?.isNotEmpty()!!){
            val englishWord:String = inputWord_English.value!!
            val persianWord:String = inputWord_Persian.value!!
            insert(WordEntity(0 , englishWord , persianWord))
            inputWord_English.value = null
            inputWord_Persian.value = null
        }else{
            Log.ERROR
        }


    }

    fun insert(word:WordEntity){
        viewModelScope.launch {
            wordRepository.insert(word)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            wordRepository.deleteAll()
        }
    }
    fun delete(word: WordEntity){
        viewModelScope.launch {
            wordRepository.delete(word)
        }
    }


    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }


}