package com.example.roomdatabaseexample.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.database.DataBaseEntity
import com.example.roomdatabaseexample.widgets.MyTextView

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val noteList: ArrayList<DataBaseEntity> = ArrayList()
    var notesClickListener: NotesClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_layout, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder:NotesViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    fun updateList(noteList: ArrayList<DataBaseEntity>) {
        clearList()
        this.noteList.addAll(noteList)
        notifyDataSetChanged()
    }



    private fun clearList() {
        this.noteList.clear()
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(noteList: DataBaseEntity) {

            title.text = noteList.task
            description.text = noteList.desc
            finishBy.text = noteList.finishBy

            listCardMenu.setOnClickListener {
                notesClickListener?.onClick(noteList)
            }

            if (noteList.isFinished) {
                status.text = " Completed "
                status.setBackgroundResource(R.color.green)
            }

            else {
                status.text = " Not Completed "
                status.setBackgroundResource(R.color.lightOrange)
            }


        }

        private val title: MyTextView = itemView.findViewById(R.id.textViewTask)
        private val description: MyTextView = itemView.findViewById(R.id.textViewDesc)
        private val finishBy: MyTextView = itemView.findViewById(R.id.textViewFinishBy)
        private val listCardMenu: CardView = itemView.findViewById(R.id.listCardView)
        private val status: MyTextView = itemView.findViewById(R.id.textViewStatus)

    }
    fun setOnClickListener(notesClickListener: NotesClickListener) {
        this.notesClickListener = notesClickListener
    }
    interface NotesClickListener {
        fun onClick(noteList: DataBaseEntity)


    }
}