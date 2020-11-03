package tam.pa.moviekuapp.activity.HomePage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.activity.DetailPage.DetailActivity
import tam.pa.moviekuapp.model.MovieListItem
import tam.pa.moviekuapp.network.ApiClient

class ViewPagerAdapter(val ctx: Context, val list: List<MovieListItem>?): PagerAdapter() {
    override fun getCount(): Int {
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen: View = inflater.inflate(R.layout.custom_list_big, null)
        val ivImage: ImageView = layoutScreen.findViewById(R.id.ivImage)
        val tvTitle: TextView = layoutScreen.findViewById(R.id.tvTitle)
        val apiCLient = ApiClient
        tvTitle.text = list?.get(position)?.title
        Picasso.get().load(apiCLient.BASE_URL_IMAGE+list?.get(position)?.backdrop_path).fit().into(ivImage)
        container.addView(layoutScreen)
        return layoutScreen
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}