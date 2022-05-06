package com.example.labibliapr
//importar librerias
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//Metodos constructores
class Supperclub : AppCompatActivity() {

    //Declaracion de variables de botones y textos
    var botonvolversupperclub: Button? = null
    var tvnombre: EditText? = null
    var tvmetodo: EditText? = null
    var tvdescripcion: EditText? = null
    var tvdecoracion: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supperclub)

        //Vinculacion de botones y editText
        botonvolversupperclub = findViewById(R.id.botonvolverchiringuito)
        botonvolversupperclub?.setOnClickListener {
            val botonvolversupperclub = Intent(this, MainActivity::class.java)
            startActivity(botonvolversupperclub)
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
                "select nombre,descripcion,metodo,decoracion from cocteles where restaurante = 'supperclub' and nombre = '$nombre' ",
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

