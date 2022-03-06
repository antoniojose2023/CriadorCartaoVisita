package br.com.antoniojose.criadorcartaovisita.ui


import android.content.ContentValues
import android.content.Context
import android.content.Intent

import android.graphics.Bitmap

import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log

import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception


class Image {

    companion object {

        fun share(context: Context, view: View) {
            var bitmap = createBitmap(view)
//            bitmap?.let {
//                saveStorageDevice(context, bitmap)
//            }

            if (bitmap != null) {
                saveStorageDevice(context, bitmap)
            }
        }

        private fun createBitmap(view: View): Bitmap? {
            var screenShot: Bitmap? = null

            screenShot = Bitmap.createBitmap(
                view.measuredWidth,
                view.measuredHeight,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(screenShot)
            view.draw(canvas)

//            try {
//                screenShot = Bitmap.createBitmap(
//                    view.measuredWidth,
//                    view.measuredHeight,
//                    Bitmap.Config.ARGB_8888
//                )
//
//                val canvas = Canvas(screenShot)
//                view.draw(canvas)
//
//            } catch (ex: Exception) {
//                Log.i("bitmap", "createBitmap: ${ex.message}")
//            }

            return screenShot
        }

        private fun saveStorageDevice(context: Context, bitmap: Bitmap) {
            var filename = "${System.currentTimeMillis()}.jpg"
            var outputStream: OutputStream? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    val image = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    outputStream = image?.let {
                        sharedCard(context, it)
                        resolver.openOutputStream(it)
                    }
                }

            } else {
                val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(directory, filename)
                val uri = Uri.fromFile(image)

                sharedCard(context, uri)

                outputStream = FileOutputStream(image)
            }

            outputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }

        }

        private fun sharedCard(context: Context, uri: Uri) {

            var intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_STREAM, uri)

            context.startActivity(
                Intent.createChooser(
                    intent,
                    "Em que app você deseja abrir esta imagem?"
                )
            )

        }
    }
}