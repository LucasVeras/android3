package lucasveras.com.br.projetofinalandroid.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.UserProfileChangeRequest

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

}