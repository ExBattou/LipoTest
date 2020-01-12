package net.adrote.lipotest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_personaje.*
import net.adrote.lipotest.model.Personaje
import net.adrote.lipotest.ui.MovieListViewModel
import net.adrote.lipotest.ui.PersonajeAdapter

/**
 * A simple [Fragment] subclass.
 */
class PersonajeFragment : Fragment() {

    private val viewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_personaje, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.personajeListtoObserve().observe(viewLifecycleOwner, Observer {
            prepareRecycler(it,personajeRecycler)
        })
        viewModel.getPersonajes()
    }

    private fun prepareRecycler(itemList: List<Personaje>, recycler: RecyclerView) {
        if(itemList.isNotEmpty()) {
            var recyclerAdapter = PersonajeAdapter(itemList)
            recycler.adapter = recyclerAdapter
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.itemAnimator = DefaultItemAnimator()
            recyclerAdapter.notifyDataSetChanged()
        }
    }

}
