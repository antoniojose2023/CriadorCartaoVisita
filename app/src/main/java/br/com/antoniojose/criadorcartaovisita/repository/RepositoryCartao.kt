package br.com.antoniojose.criadorcartaovisita.repository

import android.app.Application
import br.com.antoniojose.criadorcartaovisita.data.AppDatabase
import br.com.antoniojose.criadorcartaovisita.model.Cartao


class RepositoryCartao(context: Application) {

    private val cartaoDao = AppDatabase.getDatabase(context).cartaoDao()

    fun getListaCartao() = cartaoDao.getListCartao()

    fun insert(cartao: Cartao) = cartaoDao.insert( cartao )

}