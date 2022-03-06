package br.com.antoniojose.criadorcartaovisita.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.antoniojose.criadorcartaovisita.model.Cartao
import br.com.antoniojose.criadorcartaovisita.repository.RepositoryCartao


class ViewModelCartao(context: Application): AndroidViewModel(context) {

    private val repositoryCartao = RepositoryCartao(getApplication())

    fun getListaCartao() = repositoryCartao.getListaCartao()

    fun insert(cartao: Cartao) = repositoryCartao.insert(cartao)

}