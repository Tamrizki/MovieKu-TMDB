package tam.pa.moviekuapp.activity.HomePage

import tam.pa.moviekuapp.model.MovieListItem

interface IViewHome {
    fun onGetListMovie(list: List<MovieListItem>?)
    fun onError(msg: String)
}