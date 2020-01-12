package net.adrote.lipotest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_film.view.*
import net.adrote.lipotest.R
import net.adrote.lipotest.model.Films

class FilmAdapter(private val list:List<Films>, private val clickListener: OnFilmClickListener): RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    interface OnFilmClickListener {
        fun onFilmClick(episodeId:String, listaPersonajes: List<String?>?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(
            inflater,
            parent
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: FilmViewHolder, position: Int) {
        var data: Films =list[position]
        viewHolder.bind(data,clickListener)

    }

    class FilmViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_film, parent, false)) {

        fun bind(data: Films,filmClickListener: OnFilmClickListener) {
            itemView.lanzamiento_textview.text = data.releaseDate
            itemView.director_textview.text = data.director
            itemView.titulo_textview.text = data.title
            itemView.setOnClickListener {
                filmClickListener.onFilmClick(data.episodeId.toString(),data.characters)
            }
        }

    }
}