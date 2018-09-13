package lucasveras.com.br.projetofinalandroid.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import lucasveras.com.br.projetofinalandroid.R
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO
import lucasveras.com.br.projetofinalandroid.model.User


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        registerBtn.setOnClickListener { register() }
    }

    private fun register() {
        FirebaseBO.instance.mAuth?.createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val newUser = User()
                        newUser.email = task.result.user.email ?: ""
                        newUser.id = task.result.user.uid

                        FirebaseBO.instance.mDatabase?.reference?.child("users")?.child(newUser.id)?.setValue(newUser)

                        Toast.makeText(this@RegisterActivity, "Cadastro efetuado com sucesso.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Falha no cadastro.", Toast.LENGTH_SHORT).show()
                    }
                }

    }

}
