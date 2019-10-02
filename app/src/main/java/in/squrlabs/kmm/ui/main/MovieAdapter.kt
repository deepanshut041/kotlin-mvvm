package `in`.squrlabs.kmm.ui.main

import `in`.squrlabs.kmm.R
import `in`.squrlabs.kmm.data.remote.dto.MovieDto
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie_card.view.*
import kotlin.properties.Delegates

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieList: List<MovieDto> by Delegates.observable(emptyList()){
        _,_,_ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val movieDto: MovieDto = movieList[position]
            holder.bind(movieDto)
        }
    }

    // Update recyclerView's data
    fun updateData(newMovieList: List<MovieDto>) {
        movieList = newMovieList
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movieDto: MovieDto){
            itemView.fiAvatr.setImageURI("https://image.tmdb.org/t/p/w200${movieDto.posterPath}")
            itemView.txTitle.text = movieDto.title
            itemView.txTime.text = movieDto.releaseDate
            itemView.txAuthors.text = movieDto.originalLanguage
        }
    }
}
