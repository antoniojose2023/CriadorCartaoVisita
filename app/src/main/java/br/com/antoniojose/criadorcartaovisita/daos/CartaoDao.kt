package br.com.antoniojose.criadorcartaovisita.daos

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import br.com.antoniojose.criadorcartaovisita.model.Cartao
import org.jetbrains.annotations.NotNull

@Dao
interface CartaoDao {

    @Query("SELECT * FROM Cartao")
    fun getListCartao(): LiveData<List<Cartao>>

    @Insert
    fun insert(cartao: Cartao)

    @Update
    fun update(cartao: Cartao)

    @Delete
    fun delete(cartao: Cartao)
}