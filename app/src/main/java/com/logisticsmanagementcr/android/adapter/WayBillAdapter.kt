package com.logisticsmanagementcr.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.logisticsmanagementcr.android.databinding.WayBillItemBinding

class WayBillAdapter(private val wayBillList: List<WayBillDisplay>) :
    RecyclerView.Adapter<WayBillAdapter.ViewHolder>() {

    inner class ViewHolder(binding: WayBillItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val billNo: TextView = binding.billNoText
        val billTrace: TextView = binding.billTraceText
        val billName: TextView = binding.billNameText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WayBillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            val position = viewHolder.adapterPosition
            val bill = wayBillList[position]
            Toast.makeText(
                parent.context,
                "运单号:${bill.wayBillNo}\n${bill.wayBillName}",
                Toast.LENGTH_SHORT
            ).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bill = wayBillList[position]
        holder.billNo.text = bill.wayBillNo
        holder.billTrace.text = bill.wayBillTrace
        holder.billName.text = bill.wayBillName
    }

    override fun getItemCount() = wayBillList.size
}