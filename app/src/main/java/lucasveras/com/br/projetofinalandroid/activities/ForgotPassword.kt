package lucasveras.com.br.projetofinalandroid.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forgot_password.*
import android.widget.Toast
import lucasveras.com.br.projetofinalandroid.R
import lucasveras.com.br.projetofinalandroid.firebase.FirebaseBO

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        resetPassowordBtn.setOnClickListener { resetPasswordUser() }
    }

    private fun resetPasswordUser() {
        FirebaseBO.instance.mAuth?.sendPasswordResetEmail(emailEditText.text.toString())
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@ForgotPassword, "Um e-mail foi enviado para redefinir a senha.",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ForgotPassword, "Falha no redefinir senha.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
