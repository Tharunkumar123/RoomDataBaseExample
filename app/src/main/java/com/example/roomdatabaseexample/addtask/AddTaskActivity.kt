package com.example.roomdatabaseexample.addtask

import android.content.Intent
import android.os.Bundle
import com.example.roomdatabaseexample.database.DataBaseEntity
import com.example.roomdatabaseexample.main.MainActivity
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_task.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class AddTaskActivity : BaseActivity() {
    override fun setLayout(): Int {
        return R.layout.activity_add_task
    }

    override fun initView(savedInstanceState: Bundle?) {
        btnSave.setOnClickListener {
            insertNewNotes()
        }
    }


    private fun insertNewNotes() {
        val insertTitle = edtTaskTitle.text.toString()
        val insertDescription = edtTaskDescription.text.toString()
        val insertFinishBy = edtTaskFinishBy.text.toString()
        when {
            insertTitle.isEmpty() -> {
                showSnackBar("Please Enter Title")
                edtTaskTitle.error = "Title required"
            }
            insertDescription.isEmpty() -> {
                showSnackBar("Please Enter Description")
                edtTaskDescription.error = "Description required"
            }
            insertFinishBy.isEmpty() -> {
                showSnackBar("Please Enter Finishing Time ")
                edtTaskFinishBy.error = "Finish by required"
            }
            else -> {

                doAsync {
                    getDAO().insert(DataBaseEntity(task = insertTitle, desc = insertDescription, finishBy = insertFinishBy,isFinished = false))

                    uiThread{
                        startActivity(Intent(this@AddTaskActivity, MainActivity::class.java))
                        finish()
                        showSnackBar("New Note Saved")
                    }
                }
            }
        }
    }
}
