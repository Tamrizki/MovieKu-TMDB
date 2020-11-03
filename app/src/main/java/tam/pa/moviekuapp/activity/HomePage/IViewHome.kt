package tam.pa.moviekuapp.activity.HomePage

import tam.pa.moviekuapp.model.MovieListItem

interface IViewHome {
    fun onGetListMovie(list: List<MovieListItem>?)
    fun onGetTopRated(list: List<MovieListItem>?)
    fun onGetUpcoming(list: List<MovieListItem>?)
    fun onGetSearch(list: List<MovieListItem>?)
    fun onError(msg: String)
    fun init()
}