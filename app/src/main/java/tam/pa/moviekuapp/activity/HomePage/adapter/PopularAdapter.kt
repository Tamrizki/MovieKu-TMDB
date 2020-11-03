package tam.pa.moviekuapp.activity.HomePage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.activity.DetailPage.DetailActivity
import tam.pa.moviekuapp.model.MovieListItem
import tam.pa.moviekuapp.network.ApiClient

class PopularAdapter (val ctx: Context, val list: List<MovieListItem>?):
    RecyclerView.Adapter<PopularAdapter.viewHolder>() {
    class viewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val apiCLient = ApiClient
        fun bind(get: MovieListItem?) {
            tvTitle.text = get?.title
            Picasso.get().load(apiCLient.BASE_URL_IMAGE+get?.backdrop_path).fit().into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list_big, parent, false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(list?.get(position))
        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra("idMovie", list?.get(position)?.id)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}