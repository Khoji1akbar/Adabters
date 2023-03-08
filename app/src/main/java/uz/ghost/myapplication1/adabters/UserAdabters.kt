package uz.ghost.myapplication1.adabters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ghost.myapplication1.databinding.ItemrvBinding
import uz.ghost.myapplication1.models.User

class UserAdabters (var list:ArrayList<User>,val Rvaction: Rvaction ) : RecyclerView.Adapter<UserAdabters.Vh>(){
    inner class Vh(val itemrvBinding: ItemrvBinding) : ViewHolder(itemrvBinding.root){
        fun Onbind(user: User,position: Int){
            itemrvBinding.tvName.text = user.name
            itemrvBinding.tvNumber.text = user.number
            itemrvBinding.imageView.setOnClickListener {
                Rvaction.mypopapmenu(user, position,itemrvBinding.imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemrvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.Onbind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
}

interface Rvaction{
    fun mypopapmenu(user: User,position: Int,imageView: ImageView)
}