package tam.pa.moviekuapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tam.pa.moviekuapp.model.response.DataResponse

interface ApiService {
    @GET("movie/popular?api_key=a450ec5bde7ce12656697f09286d96ea&page=[page]")
    fun getPopularMovie(@Query("page")page: String): Observable<DataResponse>

    @GET("movie/upcoming?api_key=a450ec5bde7ce12656697f09286d96ea?page={page}")
    fun getUpcomingMovie(): Observable<DataResponse>

    @GET("movie/top_rated?api_key=a450ec5bde7ce12656697f09286d96ea?page={page}")
    fun getTopRatedMovie(): Observable<DataResponse>

    @GET("search/movie?api_key=a450ec5bde7ce12656697f09286d96ea?page={page}")
    fun getSearchMovie(): Observable<DataResponse>

}