package lucasveras.com.br.projetofinalandroid.activities

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.adapters.BannerImagesAdapter
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var images: Array<Int> = arrayOf(R.drawable.one, R.drawable.two, R.drawable.three)
    var bannerAdapter: PagerAdapter = BannerImagesAdapter(context = this, images = images)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        nav_view.getHeaderView(0).userEmailTxt.text = FirebaseBO.instance.mAuth?.currentUser?.email ?: ""

        imageBanner.adapter = bannerAdapter
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_exit -> {
                FirebaseBO.instance.mAuth?.signOut()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_list_users -> {
                val intent = Intent(this, ListUsersActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_update_perfil -> {
                val intent = Intent(this, UpdateAccount::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
