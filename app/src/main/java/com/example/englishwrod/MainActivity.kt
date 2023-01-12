package com.example.englishwrod

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwrod.data.WordEntity
import com.example.englishwrod.data.WordRepository
import com.example.englishwrod.data.WordRoomDatabase
import com.example.englishwrod.databinding.ActivityMainBinding
import com.example.englishwrod.databinding.ActivityNewWordBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var wordViewModel: WordViewModel
    var tempClick:tempClick = tempClick()
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)


        //FAB menu
        var floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        var fab1 = findViewById<FloatingActionButton>(R.id.fab1)
        var fab2 = findViewById<FloatingActionButton>(R.id.fab2)
        var fab3 = findViewById<FloatingActionButton>(R.id.fab3)
        var isFABOpen = false
        floatingActionButton.setOnClickListener(View.OnClickListener {
            if (!isFABOpen){

                isFABOpen = true
                fab1.animate().translationY(-resources.getDimension(R.dimen.standard_55))
                fab2.animate().translationY(-resources.getDimension(R.dimen.standard_105))
                fab2.setOnClickListener {
                    tempClick.temp = true
                    if (tempClick.temp == true){
                        Toast.makeText(this, "edit is enable. \n please click on word and delete ", Toast.LENGTH_LONG).show()
                    }

                }
                fab3.animate().translationY(-resources.getDimension(R.dimen.standard_155))
                fab3.setOnClickListener {
                    wordViewModel.deleteAll()
                }
                floatingActionButton.setImageResource(R.drawable.ic_baseline_close_24)
                fab1.setOnClickListener {
                    intentActivity()
                }

            }else{

                isFABOpen = false
                fab1.animate().translationY(0F)
                fab2.animate().translationY(0F)
                fab3.animate().translationY(0F)
                floatingActionButton.setImageResource(R.drawable.ic_baseline_home_24)
            }
        })

        //room Database
        val dao = WordRoomDatabase.getInstance(applicationContext).wordDao
        val repository = WordRepository(dao)
        val factory = WordViewModelFactory(repository)

        wordViewModel = ViewModelProvider(this , factory).get(WordViewModel::class.java)
        binding.lifecycleOwner = this
        initRecyclerView()


    }


    fun intentActivity(){
        var intent = Intent(this , NewWordActivity::class.java)
        startActivity(intent)

    }

    fun initRecyclerView(){
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        loadWord()
    }
    fun loadWord(){
        wordViewModel.allWord.observe(this , Observer {
            binding.recyclerview.adapter = WordAdapter(it , {wordItem : WordEntity -> recyclerviewItemClick_showToast(wordItem)})
        })
    }
    fun recyclerviewItemClick_showToast (word:WordEntity ){
        if (tempClick.temp == true){
            wordViewModel.delete(word)
            tempClick.temp = false
        }else{
            Toast.makeText(this, word.persian_word, Toast.LENGTH_SHORT).show()
        }




    }

}