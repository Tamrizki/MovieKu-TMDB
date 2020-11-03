package tam.pa.moviekuapp.activity.HomePage

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import tam.pa.moviekuapp.helper.LoadingHelper
import tam.pa.moviekuapp.model.response.DataResponse
import tam.pa.moviekuapp.network.ApiClient

class HomePresenter(view: IViewHome, ctx: Context) {
    val iView = view
    val loadingHelper = LoadingHelper(ctx)

    fun onGetListMovie(page: String){
        val apiCLient = ApiClient
        val disposable = CompositeDisposable()
        disposable.add(
            apiCLient.getMovie().getPopularMovie(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DataResponse>(){
                    override fun onNext(t: DataResponse) {
                        iView.onGetListMovie(t.results)
                    }
                    override fun onError(e: Throwable) {
                        Log.d("errorrr", e.message.toString())
                        iView.onError(e.message.toString())
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
                        iView.onGetTopRated(t.results)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("errorrr", e.message.toString())
                        iView.onError(e.message.toString())
                    }

                    override fun onComplete() {

                    }
                })
        )
    }

    fun onGetUpcoming(){
        val apiCLient = ApiClient
        val disposable = CompositeDisposable()
        disposable.add(
            apiCLient.getMovie().getUpcomingMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DataResponse>(){
                    override fun onNext(t: DataResponse) {
                        iView.onGetUpcoming(t.results)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("errorrr", e.message.toString())
                        iView.onError(e.message.toString())
                    }

                    override fun onComplete() {

                    }
                })
        )
    }
    fun onSearchMovie(caption: String){
        var query = caption
        val apiCLient = ApiClient
        val disposable = CompositeDisposable()
        disposable.add(
                apiCLient.getMovie().getSearchMovie(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<DataResponse>(){
                            override fun onNext(t: DataResponse) {
                                iView.onGetSearch(t.results)
                            }

                            override fun onError(e: Throwable) {
                                Log.d("errorrr", e.message.toString())
                                iView.onError(e.message.toString())
                            }

                            override fun onComplete() {

                            }
                        })
        )
    }

}