package com.example.labibliapr
//importar librerias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Chiringuito : AppCompatActivity() {
    //Declaracion de variables de botones y textos
    var botonvolver: Button? = null
    var tvnombre: EditText? = null
    var tvmetodo: EditText? = null
    var tvdescripcion: EditText? = null
    var tvdecoracion: EditText? = null

    //Metodos constructores
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chiringuito)
        //Vinculacion de botones y editText
        botonvolver = findViewById(R.id.botonvolverchiringuito)
        botonvolver?.setOnClickListener {
            val botonvolver = Intent(this, MainActivity::class.java)
            startActivity(botonvolver)
        }
        tvnombre = findViewById(R.id.tvnombre)
        tvmetodo = findViewById(R.id.tvmetodo)
        tvdescripcion = findViewById(R.id.tvdescripcion)
        tvdecoracion = findViewById(R.id.tvdecoracion)

    }

    //creacion de funcion buscar datos sqlite
    fun buscar(view: View) {
        val con = SQLite(this, "cocteles", null, 1)
        val baseDatos = con.writableDatabase
        val nombre = tvnombre?.text.toString()
        val descripcion = tvmetodo?.text.toString()
        val metodo = tvdescripcion?.text.toString()
        val decoracion = tvdecoracion?.text.toString()

        if (nombre.isEmpty() == false) {
            val fila = baseDatos.rawQuery(
                "select nombre,descripcion,metodo,decoracion from cocteles where restaurante = 'chiringuito' and nombre = '$nombre' ",
                null
            )
            if (fila.moveToFirst() == true) {
                tvnombre?.setText(fila.getString(0))
                tvmetodo?.setText(fila.getString(2))
                tvdescripcion?.setText(fila.getString(1))
                tvdecoracion?.setText(fila.getString(3))
                baseDatos.close()

            } else {
                tvnombre?.setText("")
                tvmetodo?.setText("")
                tvdescripcion?.setText("")
                tvdecoracion?.setText("")

                Toast.makeText(this, "No se encontro el coctel", Toast.LENGTH_LONG).show()

            }
        }
    }
}
