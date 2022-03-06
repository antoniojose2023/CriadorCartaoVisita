package br.com.antoniojose.criadorcartaovisita.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.antoniojose.criadorcartaovisita.R
import br.com.antoniojose.criadorcartaovisita.adapter.AdapterCartao
import br.com.antoniojose.criadorcartaovisita.databinding.ActivityMainBinding

import br.com.antoniojose.criadorcartaovisita.viewmodel.ViewModelCartao

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapterCartao: AdapterCartao
    private val viewModelCartao by viewModels<ViewModelCartao>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterCartao = AdapterCartao(this)

        caputarView()
        setupComponents()

        binding.rvCartoes.layoutManager = LinearLayoutManager(this)

        viewModelCartao.getListaCartao().observe(this, Observer {
            adapterCartao.lista = it
            adapterCartao.notifyDataSetChanged()
        })
        binding.rvCartoes.adapter = adapterCartao
    }


    fun setupComponents(){
        binding.floatButton.setOnClickListener {
            var intent = Intent(this, NovoCartaoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }

    fun caputarView(){

        adapterCartao.onClickItem = { view ->
            createDialogGeraCompartilhaImagem(this, view)

        }

    }

    fun createDialogGeraCompartilhaImagem(context: Context, view: View){

        var dialog = AlertDialog.Builder(this)
        dialog.setTitle("Compartilhar imagem")
        dialog.setIcon(R.drawable.ic_image)
        dialog.setMessage("Você deseja compartilhar esta imagem?")
        dialog.setPositiveButton("Compartilhar"){ wich, views ->
            Image.share(context, view)
        }
        dialog.setNegativeButton("Cancelar"){ wich, view ->

        }
        dialog.setCancelable(false)
        dialog.create()
        dialog.show()
    }


}