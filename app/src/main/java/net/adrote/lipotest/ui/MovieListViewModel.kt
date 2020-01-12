package net.adrote.lipotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.adrote.cabbieonetest.api.SWApi
import net.adrote.lipotest.model.FilmQuery
import net.adrote.lipotest.model.Personaje

class MovieListViewModel : ViewModel() {

    private val client = SWApi.create()
    private var filmList = MutableLiveData<FilmQuery>()
    private var personajesList = MutableLiveData<List<Personaje>>()
    private var listaDeUrls = mutableListOf<String>()

    // TODO: Implement the ViewModel

    fun getData() {
        viewModelScope.launch (Dispatchers.IO){
            val result = client.firstLoad()
            var responseBody = result.body()
            if(result.isSuccessful && responseBody != null){
                filmList.postValue(responseBody)
            }
        }
    }

    fun dataListToObserve(): LiveData<FilmQuery> {
        return filmList
    }

    fun getPersonajes(){
        var myList: MutableList<Personaje> = mutableListOf()
        personajesList.value = myList
        viewModelScope.launch (Dispatchers.IO){
        for(urlPersonaje in listaDeUrls) {
                urlPersonaje.dropLast(1)
                var toGO = urlPersonaje.takeLast(3)
                val result = client.getCharacter(toGO.replace("\\D+",""))
                var responseBody = result.body()
                if(result.isSuccessful && responseBody != null){
                    var personaje = responseBody
                    myList.add(personaje)

                }
            }
            personajesList.postValue(myList)
        }
//        if(myList.size>0){
//            personajesList.value = myList
//        }
    }

    fun personajeListtoObserve():LiveData<List<Personaje>> {
        return personajesList
    }

    fun saveUrlsFromPersonajes(laLista:List<String>) {
        if(laLista != null && laLista.isNotEmpty()) {
            listaDeUrls.addAll(laLista)
        }
    }
}

