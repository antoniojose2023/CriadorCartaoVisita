package br.com.antoniojose.criadorcartaovisita.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import br.com.antoniojose.criadorcartaovisita.R
import br.com.antoniojose.criadorcartaovisita.databinding.ActivityNovoCartaoBinding
import br.com.antoniojose.criadorcartaovisita.model.Cartao
import br.com.antoniojose.criadorcartaovisita.viewmodel.ViewModelCartao
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

class NovoCartaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNovoCartaoBinding
    private val viewModelCartao by viewModels<ViewModelCartao>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovoCartaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEditColor()
        setupButton()

    }


    fun setupButton(){

        binding.ivFecharTela.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.buttonNovoCartao.setOnClickListener {
             var cartao = Cartao(
                nome = binding.editNome.text.toString(),
                email = binding.editEmail.text.toString(),
                telefone = binding.editTelefone.text.toString(),
                nomeEmpresa = binding.editEmpresa.text.toString(),
                cor = binding.editCor.text.toString(),
            )

            viewModelCartao.insert(cartao)
            showMessage("Cartão salvo com sucesso.")
            finish()
        }

    }


    fun showMessage(mensage: String){
        Toast.makeText(this, "$mensage", Toast.LENGTH_LONG).show()
    }


    fun setupEditColor(){

        binding.editCor.setOnClickListener {
            var cp = ColorPicker(this, 255,0,0,0)
            cp.show()

            var button_selecionar_cor: Button = cp.findViewById(R.id.okColorButton)
            button_selecionar_cor.setOnClickListener {
                var cor = cp.color
                var corHex = Integer.toHexString( cor )

                var corFormatada = corHex.uppercase()

                binding.editCor.setText("#${corFormatada}")

                cp.dismiss()

            }

        }

    }

}