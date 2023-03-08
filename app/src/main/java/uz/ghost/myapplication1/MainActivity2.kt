package uz.ghost.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.ghost.myapplication1.databinding.ActivityMain2Binding
import uz.ghost.myapplication1.databinding.ActivityMainBinding
import uz.ghost.myapplication1.models.User
import uz.ghost.myapplication1.utils.Mysherefrens

class MainActivity2 : AppCompatActivity() {
    var isAdd = true
    var position = -1
    private lateinit var list: ArrayList<User>
    private val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Mysherefrens.init(this)
        isAdd  = intent.getBooleanExtra("key",false)
        if (!isAdd){
            position = intent.getIntExtra("position", -1)
            list = Mysherefrens.userList
            binding.edtName.setText(list[position].name)
            binding.edtNumber.setText(list[position].number)
        }
        binding.apply {
            btnSave.setOnClickListener {
                if (isAdd) {
                    val user = User(edtName.text.toString(), edtNumber.text.toString())
                    val list = Mysherefrens.userList
                    list.add(user)
                    Mysherefrens.userList = list
                    Toast.makeText(this@MainActivity2, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    val user = User(edtName.text.toString(), edtNumber.text.toString())
                    list[position] = user
                    Mysherefrens.userList = list
                    Toast.makeText(this@MainActivity2,"Edit",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}