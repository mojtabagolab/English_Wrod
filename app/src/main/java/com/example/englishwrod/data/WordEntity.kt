package com.example.englishwrod.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class WordEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    var id : Int,

    @ColumnInfo(name = "english_word")
    var english_word : String,

    @ColumnInfo(name = "persian_word")
    var persian_word : String
)
