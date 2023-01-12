package com.example.englishwrod

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.englishwrod.data.WordRepository
import com.example.englishwrod.data.WordRoomDatabase
import com.example.englishwrod.databinding.ActivityNewWordBindingImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewWordActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewWordBindingImpl
    lateinit var wordViewModel: WordViewModel
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_word)


        //fab button
        var floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        floatingActionButton.setOnClickListener(View.OnClickListener {
            intentActivity()
        })


        //room database
        val dao = WordRoomDatabase.getInstance(application).wordDao
        val repository = WordRepository(dao)
        val factory = WordViewModelFactory(repository)

        wordViewModel = ViewModelProvider(this , factory).get(WordViewModel::class.java)
        binding.myViewModel = wordViewModel
        binding.lifecycleOwner = this

    }
    fun intentActivity(){
        var intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}