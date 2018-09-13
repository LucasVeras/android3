package lucasveras.com.br.projetofinalandroid.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_account.*
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO

class UpdateAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_account)

        updateUserBtn.setOnClickListener { updateUser() }
    }

    private fun updateUser() {
        FirebaseBO.instance.updateUser(userNameEditText.text.toString())
    }
}
