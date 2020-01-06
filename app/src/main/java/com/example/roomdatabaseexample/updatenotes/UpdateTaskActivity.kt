package com.example.roomdatabaseexample.updatenotes


import android.content.Intent
import android.os.Bundle
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.base.BaseActivity
import com.example.roomdatabaseexample.database.DataBaseEntity
import com.example.roomdatabaseexample.main.MainActivity
import kotlinx.android.synthetic.main.activity_update_task.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class UpdateTaskActivity : BaseActivity() {
    private var update: Boolean = false
    private var oldData: DataBaseEntity? = null
    private var from = MainActivity.BUNDLE_VALUE_EDIT

    override fun setLayout(): Int {
        return R.layout.activity_update_task
    }

    override fun initView(savedInstanceState: Bundle?) {
        getNoteDetails()
        button_update.setOnClickListener {
            updateDetails()
        }
        button_delete.setOnClickListener {

            onNoteDelete()

        }
    }

    private fun updateDetails() {
        if (update) {
            oldData?.task=  editTextTask?.text.toString()
            oldData?.desc = editTextDesc?.text.toString()
            oldData?.finishBy = editTextFinishBy?.text.toString()
            oldData?.isFinished = checkBoxFinished.isChecked
            doAsync {
                getDAO().update(oldData!!)
                uiThread {
                    startActivity(Intent(this@UpdateTaskActivity, MainActivity::class.java))
                    finish()
                }
            }

        }

    }
    private fun getNoteDetails() {
        if (intent.hasExtra(MainActivity.BUNDLE_FROM)) {
            from = intent!!.getStringExtra(MainActivity.BUNDLE_FROM)
        }

        if (intent.hasExtra(MainActivity.BUNDEL_NOTES_DETAILS)) {
            update = true
            oldData =
                intent.getSerializableExtra(MainActivity.BUNDEL_NOTES_DETAILS) as? DataBaseEntity
            editTextTask.setText(oldData?.task)
            editTextDesc.setText(oldData?.desc)
            editTextFinishBy.setText(oldData?.finishBy)
            if (oldData?.isFinished==true){
                checkBoxFinished.isChecked=true
            }

        }
    }
    private fun onNoteDelete(){
        doAsync {
            getDAO().delete(oldData!!)
            uiThread {
                startActivity(Intent(this@UpdateTaskActivity, MainActivity::class.java))
                finish()
            }
        }
    }

}
