package com.example.englishwrod.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.concurrent.Flow

@Dao
interface WordDao {


    @Insert()
    suspend fun insert(wordEntity: WordEntity)

    @Update()
    suspend fun update(wordEntity: WordEntity)

    @Delete()
    suspend fun delete(wordEntity: WordEntity)

    @Query("SELECT * FROM word_table")
    fun getAlphabetizedWords(): LiveData<List<WordEntity>>

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}