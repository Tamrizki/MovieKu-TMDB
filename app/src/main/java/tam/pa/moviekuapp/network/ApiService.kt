package tam.pa.moviekuapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tam.pa.moviekuapp.model.response.DataResponse
import tam.pa.moviekuapp.model.response.DetailMovieResponse

interface ApiService {
    @GET("movie/popular?api_key=a450ec5bde7ce12656697f09286d96ea&page=[page]")
    fun getPopularMovie(@Query("page")page: String): Observable<DataResponse>

    @GET("movie/upcoming?api_key=a450ec5bde7ce12656697f09286d96ea")
    fun getUpcomingMovie(): Observable<DataResponse>

    @GET("movie/top_rated?api_key=a450ec5bde7ce12656697f09286d96ea")
    fun getTopRatedMovie(): Observable<DataResponse>

    @GET("search/movie?api_key=a450ec5bde7ce12656697f09286d96ea&query=[query]")
    fun getSearchMovie(@Query("query")query: String): Observable<DataResponse>

    @GET("movie/{id}?api_key=a450ec5bde7ce12656697f09286d96ea")
    fun getDetailMovie(@Path("id")id: String): Observable<DetailMovieResponse>

}