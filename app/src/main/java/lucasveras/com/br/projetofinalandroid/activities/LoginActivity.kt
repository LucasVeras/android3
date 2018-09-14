package lucasveras.com.br.projetofinalandroid.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener { login() }
        registerBtn.setOnClickListener { registerNewUser() }
        forgotPasswordTextView.setOnClickListener { forgotPasssword() }
    }

    private fun login() {
        if (!emailEditText.text.isEmpty() && !passwordEditText.text.isEmpty()) {
            FirebaseBO.instance.mAuth?.signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@LoginActivity, "Falha no login", Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    private fun registerNewUser() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun forgotPasssword() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

}
