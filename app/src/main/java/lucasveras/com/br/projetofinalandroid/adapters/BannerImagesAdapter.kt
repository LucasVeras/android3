package lucasveras.com.br.projetofinalandroid.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.banner_image_item.view.*
import lucasveras.com.br.projetofinalandroid.R

class BannerImagesAdapter(var context: Context, var images: Array<Int>) : PagerAdapter() {

    private lateinit var inflater: LayoutInflater

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object` as LinearLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image: ImageView
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = inflater.inflate(R.layout.banner_image_item, container, false)
        image = view.sliderImage
        image.setBackgroundResource(images[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}