package lucasveras.com.br.projetofinalandroid.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_news.view.*
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.model.News
import android.graphics.BitmapFactory
import android.util.Base64
import com.squareup.picasso.Picasso


class NewsListAdapter(private val news: List<News>, private val context: Context) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(news: News) {
            val title = itemView.news_item_title
            val image = itemView.news_item_imageView

            title.text = news.title

            news.image.let {
                Picasso.get().load(it).into(image);
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_news, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = news[position]

        holder.bindView(news)
    }

}