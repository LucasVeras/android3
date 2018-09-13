package lucasveras.com.br.projetofinalandroid.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO
import lucasveras.com.br.projetofinalandroid.model.User
import android.content.Context
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_users.*
import kotlinx.android.synthetic.main.item_list_user.view.*

class ListUsersActivity : AppCompatActivity() {

    private val users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        userListRecyclerview.adapter = UserListAdapter(users, this@ListUsersActivity)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        userListRecyclerview.layoutManager = layoutManager

        getUsers()
    }

    private fun getUsers() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                users.clear()

                dataSnapshot.children.mapNotNullTo(users) { it.getValue<User>(User::class.java) }

                userListRecyclerview.adapter = UserListAdapter(users, this@ListUsersActivity)
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                userListRecyclerview.layoutManager = layoutManager
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ListUsersActivity, "Falha ao buscar usuários.", Toast.LENGTH_LONG).show()
            }
        }

        FirebaseBO.instance.mDatabase?.reference?.child("users")?.addListenerForSingleValueEvent(menuListener)
    }
}

class UserListAdapter(private val users: List<User>, private val context: Context) : Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(user: User) {
            val email = itemView.user_item_email

            email.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_user, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.bindView(user)
    }

}