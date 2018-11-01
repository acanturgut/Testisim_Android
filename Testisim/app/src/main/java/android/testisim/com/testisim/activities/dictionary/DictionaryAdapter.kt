package android.testisim.com.testisim.activities.dictionary

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.testisim.com.testisim.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_dictionary.view.*


class DictionaryAdapter() : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

    private val itemList: ArrayList<DictionaryEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DictionaryViewHolder(layoutInflater.inflate(R.layout.item_dictionary, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.onBindData(itemList[position])
    }

    fun updateList(itemList: List<DictionaryEntity>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class DictionaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindData(item: DictionaryEntity) {

            itemView.itemDictionaryTitle.text = item.word
            itemView.itemDictionaryContent.text = item.ack

        }
    }
}