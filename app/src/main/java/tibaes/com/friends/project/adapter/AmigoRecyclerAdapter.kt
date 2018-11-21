package tibaes.com.friends.project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_lista_amigo.view.*
import tibaes.com.friends.R
import tibaes.com.friends.project.db.Friend


class AmigoRecyclerAdapter internal constructor(context: Context) :
RecyclerView.Adapter<AmigoRecyclerAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var amigos = emptyList<Friend>() // cachear os elementos

    // infla o layout do item da lista para cada componente da lista
    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_lista_amigo, holder,
                false )
        return ViewHolder(view)
    }

    // retorna o tamanho da lista
    override fun getItemCount() = amigos.size

    // colocando os itens da lista nos itens de view da lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = amigos[position]
        holder.nomeAmigo.text = current.nome
    }

    // classe para mapear os componentes do item da lista
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nomeAmigo: TextView = itemView.txtFriendListName
    }

    // lista temporária com os dados a serem trabalhados
   /* private fun lista(): List<String>{
        return listOf(
                "Max",
                "Sabrina",
                "Alexandre",
                "Alberto"
        )
    }
    */

    fun setFriendList(friendList: List<Friend>){
        this.amigos = friendList
        notifyDataSetChanged()
    }
}