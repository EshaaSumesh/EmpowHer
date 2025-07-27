package com.example.empowher

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import java.io.IOException

class Album : AppCompatActivity() {

    private lateinit var buttonUpload: Button
    private lateinit var buttonSubmit: Button
    private lateinit var editTextDescription: EditText
    private lateinit var gridView: GridView
    private val imageList = mutableListOf<Pair<Bitmap, String>>()
    private val imageAdapter by lazy { ImageAdapter(this, imageList) }
    private var selectedImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        buttonUpload = findViewById(R.id.buttonUpload)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        editTextDescription = findViewById(R.id.editTextDescription)
        gridView = findViewById(R.id.gridView)

        gridView.adapter = imageAdapter

        buttonUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        buttonSubmit.setOnClickListener {
            val description = editTextDescription.text.toString()
            if (selectedImage != null && description.isNotEmpty()) {
                imageList.add(Pair(selectedImage!!, description))
                imageAdapter.notifyDataSetChanged()
                editTextDescription.text.clear()
                selectedImage = null
                Toast.makeText(this, "Image and description submitted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select an image and enter a description", Toast.LENGTH_SHORT).show()
            }
        }

        gridView.setOnItemClickListener { _, _, position, _ ->
            val description = imageList[position].second
            Toast.makeText(this, description, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val uri: Uri? = data.data
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}

class ImageAdapter(private val context: Activity, private val imageList: List<Pair<Bitmap, String>>) : BaseAdapter() {

    override fun getCount(): Int = imageList.size

    override fun getItem(position: Int): Any = imageList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = if (convertView == null) {
            ImageView(context).apply {
                layoutParams = AbsListView.LayoutParams(300, 300)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        } else {
            convertView as ImageView
        }
        imageView.setImageBitmap(imageList[position].first)
        return imageView
    }
}
