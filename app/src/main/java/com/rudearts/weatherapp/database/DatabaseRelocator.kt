package com.rudearts.weatherapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.rudearts.weatherapp.model.greendao.DaoMaster
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DatabaseRelocator(private val context: Context, private val name: String, cursorFactory: SQLiteDatabase.CursorFactory?) : DaoMaster.DevOpenHelper(context, name, cursorFactory) {

    companion object {
        private val DATABASE_MIN_SIZE = 500000
    }

    private val path = "/data/data/" + context.packageName + "/databases/"

    override fun getWritableDatabase(): SQLiteDatabase {
        if (!checkDataBase()) {
            try {
                makeDatabaseDirIfDoesNotExist()
                copyDataBase()
            } catch (e: IOException) {
                Log.e("Database Error", "Error copying database " + e.toString())
            }

        }

        return super.getWritableDatabase()
    }

    @Throws(IOException::class)
    private fun copyDataBase() {
        val myInput = context.assets.open(name)

        val outFileName = path + name
        val myOutput = FileOutputStream(outFileName)
        val buffer = ByteArray(1024)
        var length: Int = 1

        while (length > 0) {
            length = myInput.read(buffer)
            if(length > 0) myOutput.write(buffer, 0, length)
        }

        myOutput.flush()
        myOutput.close()
        myInput.close()
    }

    private fun makeDatabaseDirIfDoesNotExist() {
        val databaseFile = File(path)
        if (!databaseFile.exists()) {
            databaseFile.mkdir()
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(path + name)
        return dbFile.exists() && dbFile.length() > DATABASE_MIN_SIZE

    }


}