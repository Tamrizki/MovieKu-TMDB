package tam.pa.moviekuapp.activity.HomePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.model.MovieListItem

class HomeActivity : AppCompatActivity(), IViewHome{
    lateinit var iview: IViewHome
    lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iview = this
        presenter = HomePresenter(iview, this)
        presenter.onGetListMovie("1")
    }

    override fun onGetListMovie(list: List<MovieListItem>?) {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }
}