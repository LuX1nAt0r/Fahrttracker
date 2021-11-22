package com.llprdctn.fahrttracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.Drive
import kotlinx.android.synthetic.main.row_drives.view.*

class DriversAdapter(): RecyclerView.Adapter<DriversAdapter.DriversViewHolder>() {

    inner class DriversViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val diffCallback = object : DiffUtil.ItemCallback<Drive>() {
        override fun areItemsTheSame(oldItem: Drive, newItem: Drive): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drive, newItem: Drive): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private var onItemClickListener: ((Drive) -> Unit)? = null

    private val differ = AsyncListDiffer(this, diffCallback)

    var drives: List<Drive>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        return DriversViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_drives,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        val drive = drives[position]
        holder.itemView.apply {
            tvDriveMitFahrer.text = drive.mitDriver.toString().drop(1).dropLast(1)
            tvHinRueckFahrt.text = drive.hinRueckFahrt
        }

        setOnItemClickListener {
            onItemClickListener?.let { click ->
                click(drive)
            }
        }
    }

    override fun getItemCount(): Int {
        return drives.size
    }

    fun setOnItemClickListener(onItemClick: (Drive) -> Unit) {
        this.onItemClickListener = onItemClick
    }

}