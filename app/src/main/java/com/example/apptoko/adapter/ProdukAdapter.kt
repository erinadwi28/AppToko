package com.example.apptoko.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apptoko.LoginActivity
import com.example.apptoko.R
import com.example.apptoko.api.BaseRetrofit
import com.example.apptoko.responses.produk.Produk
import com.example.apptoko.responses.produk.ProdukResponsePost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class ProdukAdapter(val listproduk: List<Produk>):RecyclerView.Adapter<ProdukAdapter.ViewHolder> () {

    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = listproduk[position]
        holder.txtNamaProduk.text = produk.nama

        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        holder.txtHarga.text = numberFormat.format(produk.harga.toInt()).toString()

        val token = LoginActivity.sessionManager.getString("TOKEN")
        holder.btnDelete.setOnClickListener{
            //Toast.makeText(holder.itemView.context, produk.nama.toString(), Toast.LENGTH_LONG).show()
            api.deleteProduk(token.toString(), produk.id.toInt()).enqueue(object :
                Callback<ProdukResponsePost> {
                override fun onResponse(
                    call: Call<ProdukResponsePost>,
                    response: Response<ProdukResponsePost>
                ) {
                    Log.d("Data", response.toString())
                    Toast.makeText(holder.itemView.context, "Data dihapus", Toast.LENGTH_LONG).show()

                    holder.itemView.findNavController().navigate(R.id.produkFragment)
                }

                override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                    Log.e("Data", t.toString())
                }

            })

        }

        holder.btnEdit.setOnClickListener{
            val bundle = Bundle()
            bundle.putParcelable("produk", produk)
            bundle.putString("status", "edit")

            holder.itemView.findNavController().navigate(R.id.produkFormFragment, bundle)
        }

    }

    override fun getItemCount(): Int {
        return listproduk.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val txtNamaProduk = itemView.findViewById<TextView>(R.id.txtNamaProduk)
        val txtHarga = itemView.findViewById<TextView>(R.id.txtHarga)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
        val btnEdit = itemView.findViewById<ImageButton>(R.id.btnEdit)


    }
}