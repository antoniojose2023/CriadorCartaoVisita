package br.com.antoniojose.criadorcartaovisita.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cartao(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var nome: String,
    var email: String,
    var telefone: String,
    var nomeEmpresa: String,
    var cor: String
) {
}