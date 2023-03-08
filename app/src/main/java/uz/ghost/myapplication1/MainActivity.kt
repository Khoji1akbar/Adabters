package uz.ghost.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import uz.ghost.myapplication1.adabters.Rvaction
import uz.ghost.myapplication1.adabters.UserAdabters
import uz.ghost.myapplication1.databinding.ActivityMainBinding
import uz.ghost.myapplication1.models.User
import uz.ghost.myapplication1.utils.Mysherefrens

class MainActivity : AppCompatActivity(),Rvaction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var list:ArrayList<User>
     private lateinit var userAdabters: UserAdabters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

      binding.btnAdd.setOnClickListener {
         val intent = Intent(this , MainActivity2::class.java)
          intent.putExtra("key" , true)
          startActivity(intent)

    }
}
     override fun onResume () {
         super.onResume()
        Mysherefrens.init(this)
         list = Mysherefrens.userList
        userAdabters = UserAdabters(list, this)
        binding.rv.adapter = userAdabters
    }
    override fun mypopapmenu(user: User, position: Int, imageView: ImageView) {
        val popupmenu = PopupMenu(this,imageView)
        popupmenu.inflate(R.menu.my)
        popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.menu_edit -> {
                        val intent = Intent(this , MainActivity2::class.java)
                        intent.putExtra("key" , false)
                    intent.putExtra("position",position)
                        startActivity(intent)
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.menu_delete -> {
                    list.remove(user)
                    Mysherefrens.userList = list
                    userAdabters.list = list
                    userAdabters.notifyItemRemoved(position)
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                }
            }

            true
        })
        popupmenu.show()
    }
}