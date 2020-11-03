package tam.pa.moviekuapp.activity.DetailPage

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import tam.pa.moviekuapp.model.response.DataResponse
import tam.pa.moviekuapp.model.response.DetailMovieResponse
import tam.pa.moviekuapp.network.ApiClient

class DetailPresenter (val iview: IViewDetailMovie){
    fun onGetDetailMovie(idMovie: String){

        val apiCLient = ApiClient
        val disposable = CompositeDisposable()
        disposable.add(
                apiCLient.getMovie().getDetailMovie(idMovie)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<DetailMovieResponse>(){
                            override fun onNext(t: DetailMovieResponse) {
                                iview.onGetDetail(t)
                            }

                            override fun onError(e: Throwable) {
                                iview.onError(e.message.toString())
                            }

                            override fun onComplete() {

                            }
                        })
        )
    }

    fun onGetTopRated(){
        val apiCLient = ApiClient
        val disposable = CompositeDisposable()
        disposable.add(
                apiCLient.getMovie().getTopRatedMovie()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<DataResponse>(){
                            override fun onNext(t: DataResponse) {
                                iview.onGetTopRated(t.results)
                            }

                            override fun onError(e: Throwable) {
                                Log.d("errorrr", e.message.toString())
                                iview.onError(e.message.toString())
                            }

                            override fun onComplete() {

                            }
                        })
        )
    }

}