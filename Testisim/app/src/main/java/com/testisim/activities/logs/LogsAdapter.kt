package com.testisim.activities.logs

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.testisim.com.testisim.R
import com.testisim.models.TestModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_test.view.*

class LogsAdapter() : RecyclerView.Adapter<LogsAdapter.LogsViewHolder>() {

    private val itemList: ArrayList<TestModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LogsViewHolder(layoutInflater.inflate(R.layout.item_test, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        holder.onBindData(itemList[position])
    }

    fun updateList(itemList: List<TestModel>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class LogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindData(item: TestModel) {

            if (item.isSuccess) {
                itemView.testItemImage.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.yes))
            } else {
                itemView.testItemImage.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.no))
            }

            itemView.testItemDate.text = item.date

        }
    }
}