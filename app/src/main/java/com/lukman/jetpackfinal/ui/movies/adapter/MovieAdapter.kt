package com.lukman.jetpackfinal.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lukman.jetpackfinal.Constants.IMAGE_URL
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.databinding.ItemMoviesBinding
import com.lukman.jetpackfinal.utilities.MyDiffUtil

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK)  {




    companion object{

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return  oldItem == newItem
            }

        }
    }


    inner class MovieViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                txtTitle.text = movieEntity.originalTitle
                txtRatting.text = movieEntity.voteAverage.toString()
                txtReleaseDate.text = movieEntity.releaseDate
                imgCover.load("$IMAGE_URL/${movieEntity.backdropPath}")
                txtOverview.text = movieEntity.overview
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null){
            holder.bind(movies)
        }

    }

//    fun setData(listUpdate: List<MovieEntity>) {
//        newList = listUpdate
//
//    }

}