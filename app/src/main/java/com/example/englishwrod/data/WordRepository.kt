package com.example.englishwrod.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository (private val wordDao: WordDao) {
    val allWord = wordDao.getAlphabetizedWords()


    suspend fun insert(wordEntity: WordEntity){
        wordDao.insert(wordEntity)
    }

    suspend fun update(wordEntity: WordEntity){
        wordDao.update(wordEntity)
    }

    suspend fun delete(wordEntity: WordEntity){
        wordDao.delete(wordEntity)
    }

    suspend fun deleteAll(){
        wordDao.deleteAll()
    }

}