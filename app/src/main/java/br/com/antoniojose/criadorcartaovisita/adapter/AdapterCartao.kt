package br.com.antoniojose.criadorcartaovisita.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojose.criadorcartaovisita.databinding.ItemCartaoBinding
import br.com.antoniojose.criadorcartaovisita.model.Cartao

class AdapterCartao(val context: Context): RecyclerView.Adapter<AdapterCartao.ViewHolderCartao>() {
    var lista: List<Cartao> = listOf()
    lateinit var onClickItem: (View) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCartao {
           var layout = LayoutInflater.from(parent.context)
           var binding = ItemCartaoBinding.inflate(layout, parent, false)
           return ViewHolderCartao( binding )
    }

    override fun onBindViewHolder(holder: ViewHolderCartao, position: Int) {
        var cartao = lista[position]

        holder.binding.tvItemNome.text = cartao.nome
        holder.binding.tvItemEmail.text = cartao.email
        holder.binding.tvItemTelefone.text = cartao.telefone
        holder.binding.tvItemEmpresa.text = cartao.nomeEmpresa

        //var corHex = cartao.cor.toUpperCase()
        holder.binding.cardItem.setCardBackgroundColor(Color.parseColor(cartao.cor))
        holder.binding.cardItem.setOnClickListener {
             onClickItem(it)
        }
    }

    override fun getItemCount() = lista.size

    class ViewHolderCartao(binding: ItemCartaoBinding): RecyclerView.ViewHolder(binding.root){
          var binding = binding
    }

}