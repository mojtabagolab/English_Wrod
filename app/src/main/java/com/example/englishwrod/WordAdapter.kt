package com.example.englishwrod

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.englishwrod.data.WordEntity
import com.example.englishwrod.databinding.ActivityNewWordBinding
import com.example.englishwrod.databinding.RecyclerviewItemBinding
import com.example.englishwrod.generated.callback.OnClickListener

class WordAdapter (val word : List<WordEntity> , val clickListener: (WordEntity) ->Unit) : RecyclerView.Adapter<WordViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : RecyclerviewItemBinding = DataBindingUtil.inflate(layoutInflater ,
            R.layout.recyclerview_item , parent , false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(word[position] , clickListener)
    }

    override fun getItemCount(): Int {
        return word.size
    }

}
class WordViewHolder (val binding: RecyclerviewItemBinding  ): RecyclerView.ViewHolder(binding.root){
    fun bind(word : WordEntity , clickListener: (WordEntity) ->Unit){
        binding.textView.text = word.english_word
        binding.Linear.setOnClickListener {
            clickListener(word)
        }
    }
}

