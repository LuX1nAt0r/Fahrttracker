package com.llprdctn.fahrttracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import kotlinx.android.synthetic.main.row_passengers.view.*
import timber.log.Timber

class PassengersAdapter(): RecyclerView.Adapter<PassengersAdapter.PassengersViewHolder>() {

    inner class PassengersViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val diffCallBack = object : DiffUtil.ItemCallback<MitFahrer>() {
        override fun areItemsTheSame(oldItem: MitFahrer, newItem: MitFahrer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MitFahrer, newItem: MitFahrer): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private var onItemClickListener: ((MitFahrer) -> Unit)? = null

    private val differ = AsyncListDiffer(this, diffCallBack)

    var passengers: List<MitFahrer>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengersViewHolder {
        return PassengersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_passengers,
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val passenger = passengers[position]
        holder.itemView.apply {
            tvPassengerTitle.text = passenger.name
            tvPassengerRides.text = passenger.rides.toString()
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
                click(passenger)
            }
        }
    }

    override fun getItemCount(): Int {
        return passengers.size
    }

    fun setOnItemClickListener(onItemClick: (MitFahrer) -> Unit) {
        this.onItemClickListener = onItemClick
    }

}