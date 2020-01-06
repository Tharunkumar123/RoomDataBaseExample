package com.example.roomdatabaseexample.base

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabaseexample.database.DataBase
import com.example.roomdatabaseexample.database.DataBaseDAOInterface
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setLayout(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setLayout())
        initView(savedInstanceState)
    }

    fun makeFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    fun showSnackBar(message:String){
        val contentView = findViewById<View>(android.R.id.content)
        Snackbar.make(contentView,message, Snackbar.LENGTH_SHORT).show()
    }
    fun getDAO(): DataBaseDAOInterface {
        return DataBase.dataBase.dataBaseDaoInterface()
    }
}