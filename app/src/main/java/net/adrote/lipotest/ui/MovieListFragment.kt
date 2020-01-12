package net.adrote.lipotest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_list.*
import net.adrote.lipotest.R
import net.adrote.lipotest.model.FilmQuery
import net.adrote.lipotest.model.Films

class MovieListFragment : Fragment(),FilmAdapter.OnFilmClickListener  {

    private val viewModel:MovieListViewModel by activityViewModels()

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_movies.visibility=View.VISIBLE
        mainRecycler.visibility = View.GONE

        viewModel.dataListToObserve().observe(viewLifecycleOwner, Observer {
            prepareRecycler(it,mainRecycler)
        })
        viewModel.getData()
    }

    private fun prepareRecycler(itemList: FilmQuery, recycler: RecyclerView) {
        if(itemList.films!!.isNotEmpty()) {
            var recyclerAdapter = FilmAdapter((itemList!!.films as List<Films>?)!!,this)
            recycler.adapter = recyclerAdapter
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.itemAnimator = DefaultItemAnimator()
            recyclerAdapter.notifyDataSetChanged()
            progress_movies.visibility=View.GONE
            recycler.visibility = View.VISIBLE
        }

    }

    override fun onFilmClick(episodeId: String, listaPersonajes: List<String?>?) {
        viewModel.saveUrlsFromPersonajes(listaPersonajes as List<String>)
        //viewModel.getPersonajes()

        findNavController().navigate(R.id.action_movieListFragment_to_personajeFragment)
    }


}