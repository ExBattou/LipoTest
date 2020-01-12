package net.adrote.lipotest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_character.view.*
import net.adrote.lipotest.R
import net.adrote.lipotest.model.Personaje

/**
 * Created by Adrian Narducci on 1/11/2020.
 */
class PersonajeAdapter (private val list:List<Personaje>) : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return PersonajeViewHolder(
            inflater,
            parent
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: PersonajeViewHolder, position: Int) {
        var data: Personaje =list[position]
        viewHolder.bind(data)

    }

    class PersonajeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_character, parent, false)) {

        fun bind(data: Personaje) {
            itemView.nombre_textview.text = data.name
            itemView.genero_textview.text = data.gender
            itemView.fechaNac_textview.text = data.birthYear
        }

    }
}