package com.example.labibliapr
//importar librerias
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//creacion de constructor
class SQLite(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    //Metodos constructores
    override fun onCreate(db: SQLiteDatabase?) {
        //creacion de query
     db?.execSQL("create table cocteles(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre text, descripcion text,metodo text, decoracion text, restaurante text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion : Int, newVersion: Int) {

    }


}
