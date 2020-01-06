package com.example.roomdatabaseexample.main


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.addtask.AddTaskActivity
import com.example.roomdatabaseexample.base.BaseActivity
import com.example.roomdatabaseexample.database.DataBaseEntity
import com.example.roomdatabaseexample.updatenotes.UpdateTaskActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : BaseActivity() {
    val REQUEST_CODE = 1001
    companion object {
        const val BUNDLE_FROM = "from"
        const val BUNDLE_VALUE_NEW = "new"
        const val BUNDLE_VALUE_EDIT = "edit"
        const val BUNDEL_NOTES_DETAILS = "Notes Details"
    }
    lateinit var myAdapter: NotesAdapter
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
val dataNotes=DataBaseEntity().toString().length
        if (dataNotes<=0){
            txtNoDataFound.visibility =View.VISIBLE
        }
        else{
            txtNoDataFound.visibility =View.GONE
        }
        getNotesList()
        roomDbRecyclerView.layoutManager =
            LinearLayoutManager( this)
        myAdapter = NotesAdapter()
        roomDbRecyclerView.adapter = myAdapter

        myAdapter.notifyDataSetChanged()
        myAdapter.setOnClickListener(object : NotesAdapter.NotesClickListener {

            override fun onClick(noteList: DataBaseEntity) {
                val myIntent = Intent(this@MainActivity, UpdateTaskActivity::class.java)
                myIntent.putExtra(BUNDLE_FROM, BUNDLE_VALUE_EDIT)
                myIntent.putExtra(BUNDEL_NOTES_DETAILS, noteList)
                startActivity(myIntent)
                myAdapter.notifyDataSetChanged()
                finish()
            }



        })

        floatingActionButton.setOnClickListener {
            val myIntent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivityForResult(myIntent, REQUEST_CODE)
            finish()
        }

    }

    private fun getNotesList() {
        doAsync {
            val list = getDAO().getNotesDetails()
            uiThread {
                myAdapter.updateList(list as ArrayList<DataBaseEntity>)
                myAdapter.notifyDataSetChanged()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    getNotesList()
                }
            }
        }
    }


}
