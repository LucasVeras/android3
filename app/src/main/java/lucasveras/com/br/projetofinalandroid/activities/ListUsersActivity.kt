package lucasveras.com.br.projetofinalandroid.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_list_users.*
import lucasveras.com.br.projetofinalandroid.adapters.UserListAdapter

class ListUsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        getUsers()
    }

    private fun getUsers() {
        FirebaseBO.instance.getUsers(this, {
            userListRecyclerview.adapter = UserListAdapter(it, this@ListUsersActivity)
            val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            userListRecyclerview.layoutManager = layoutManager
        })
    }
}
