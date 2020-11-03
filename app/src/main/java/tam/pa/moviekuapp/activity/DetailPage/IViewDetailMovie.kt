package tam.pa.moviekuapp.activity.DetailPage

import tam.pa.moviekuapp.model.MovieListItem
import tam.pa.moviekuapp.model.response.DetailMovieResponse

interface IViewDetailMovie {
    fun onGetDetail(data: DetailMovieResponse)
    fun onGetTopRated(list: List<MovieListItem>)
    fun onError(msg: String)
    fun init()
}