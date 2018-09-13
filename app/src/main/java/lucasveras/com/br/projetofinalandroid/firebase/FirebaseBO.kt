package lucasveras.com.br.projetofinalandroid.firebase

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import lucasveras.com.br.projetofinalandroid.model.News
import lucasveras.com.br.projetofinalandroid.model.User

class FirebaseBO private constructor() {

    var mAuth: FirebaseAuth? = null
    var mDatabase: FirebaseDatabase? = null

    init {
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
    }

    private object Holder { val INSTANCE = FirebaseBO() }

    companion object {
        val instance: FirebaseBO by lazy { Holder.INSTANCE }
    }

    fun updateUser(username: String, callback: (() -> Unit)) {
        val user = mAuth?.currentUser

        val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build()

        user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task -> if (task.isSuccessful) callback() }
    }

    fun getUsers(context: Context, callback: ((MutableList<User>) -> Unit)) {
        val users = mutableListOf<User>()

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                users.clear()

                dataSnapshot.children.mapNotNullTo(users) { it.getValue<User>(User::class.java) }

                callback(users)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Falha ao buscar usuários.", Toast.LENGTH_LONG).show()
            }
        }

        FirebaseBO.instance.mDatabase?.reference?.child("users")?.addListenerForSingleValueEvent(menuListener)
    }

    fun getNews(context: Context, callback: ((MutableList<News>) -> Unit)) {
        val news = mutableListOf<News>()

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                news.clear()

                dataSnapshot.children.mapNotNullTo(news) { it.getValue<News>(News::class.java) }

                callback(news)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Falha ao buscar as notícias.", Toast.LENGTH_LONG).show()
            }
        }

        FirebaseBO.instance.mDatabase?.reference?.child("news")?.addListenerForSingleValueEvent(menuListener)
    }

}