package com.example.empowher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Grid(private val context: Context, private val buttonLabels: Array<String>, private val buttonImages: IntArray) : BaseAdapter() {

    override fun getCount(): Int {
        return buttonLabels.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.grid_item, parent, false)
        }

        val imageView = view!!.findViewById<ImageView>(R.id.grid_item_image)
        val textView = view.findViewById<TextView>(R.id.grid_item_label)

        imageView.setImageResource(buttonImages[position])
        textView.text = buttonLabels[position]

        return view
    }
}
