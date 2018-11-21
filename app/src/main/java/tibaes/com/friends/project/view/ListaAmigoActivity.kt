package tibaes.com.friends.project.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_amigo.*
import tibaes.com.friends.R
import tibaes.com.friends.project.adapter.AmigoRecyclerAdapter
import tibaes.com.friends.project.db.Friend
import tibaes.com.friends.project.viewmodel.FriendViewModel

class ListaAmigoActivity : AppCompatActivity() {

    private lateinit var friendViewModel: FriendViewModel
    private val requestCodeAddAmigo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_amigo)




        val recyclerView = rvListaAmigo
        val adapter = AmigoRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        friendViewModel =
                ViewModelProviders.of(this).get(FriendViewModel::class.java)

        friendViewModel.allFriends.observe(this, Observer { friends ->
            friends?.let { adapter.setFriendList(it) }

        })
//novo
        fbAddAmigo.setOnClickListener {
            val intent = Intent(this@ListaAmigoActivity, NovoAmigoActivity::class.java)
            startActivityForResult(intent, requestCodeAddAmigo)


        }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == requestCodeAddAmigo && resultCode == Activity.RESULT_OK) {
                data.let {
                    val friend: Friend = data?.getSerializableExtra(NovoAmigoActivity.EXTRA_REPLY) as Friend
                    friendViewModel.insert(friend)

                }
            } else {

                Toast.makeText(applicationContext,
                        R.string.empty_not_saved, Toast.LENGTH_LONG).show()


            }


        }



}
















