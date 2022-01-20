package com.example.apptoko.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptoko.R
import com.example.apptoko.responses.transaksi.Transaksi
import java.text.NumberFormat
import java.util.*

class LaporanAdapter (val listTransaksi: List<Transaksi>):RecyclerView.Adapter<LaporanAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_laporan, parent,false)
        return LaporanAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaksi = listTransaksi[position]
        holder.txtTglTransaksi.text = transaksi.tanggal
        holder.txtNoNota.text = "#0000"+transaksi.id

        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        holder.txtTotalItemTransaksi.text = numberFormat.format(transaksi.total.toDouble()).toString()
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val txtTglTransaksi = itemView.findViewById<TextView>(R.id.txtTglTransaksi)
        val txtNoNota = itemView.findViewById<TextView>(R.id.txtNoNota)
        val txtTotalItemTransaksi = itemView.findViewById<TextView>(R.id.txtTotalItemTransaksi)
    }
}