package com.example.labibliapr
//importar librerias
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    //Declaracion de variables de botones y textos
    var botonchiringuito: Button? = null
    var botonseagrill: Button? = null
    var botonsupperclub: Button? = null
    var txtNombre: EditText? = null
    var txtMetodo: EditText? = null
    var txtDescripcion: EditText? = null
    var txtRestaurante: EditText? = null
    var txtDecoracion: EditText? = null

    //Metodos constructores
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Vinculacion de botones y editText
        botonchiringuito = findViewById(R.id.btnChiringuito)
        botonseagrill = findViewById(R.id.btnSeagrill)
        botonsupperclub = findViewById(R.id.btnSupperclub)

        txtNombre = findViewById(R.id.tvnombre)
        txtMetodo = findViewById(R.id.txtMetodo)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtRestaurante = findViewById(R.id.txtRestaurante)
        txtDecoracion = findViewById(R.id.txtDecoracion)

        botonchiringuito?.setOnClickListener {
            val chiringuito = Intent(this, Chiringuito::class.java)
            startActivity(chiringuito)
        }
        botonseagrill?.setOnClickListener {
            val seagrill = Intent(this, Seagrill::class.java)
            startActivity(seagrill)
        }
        botonsupperclub?.setOnClickListener {
            val supperclub = Intent(this, Supperclub::class.java)
            startActivity(supperclub)
        }
    }

    //creacion de funcion insertar datos sqlite
    fun insertar(view: View) {
        val con = SQLite(this, "cocteles", null, 1)
        val baseDatos = con.writableDatabase

        val Nombre = txtNombre?.text.toString()
        val Metodo = txtMetodo?.text.toString()
        val Descripcion = txtDescripcion?.text.toString()
        val Decoracion = txtDecoracion?.text.toString()
        val Restaurante = txtRestaurante?.text.toString()

        if (!Nombre.isEmpty() && !Metodo.isEmpty() && !Descripcion.isEmpty() && !Decoracion.isEmpty() && !Restaurante.isEmpty()) {
            val registro = ContentValues()
            registro.put("nombre", Nombre)
            registro.put("descripcion", Descripcion)
            registro.put("metodo", Metodo)
            registro.put("decoracion", Decoracion)
            registro.put("restaurante", Restaurante)
            baseDatos.insert("cocteles", null, registro)

            txtNombre?.setText("")
            txtMetodo?.setText("")
            txtDescripcion?.setText("")
            txtDecoracion?.setText("")
            txtRestaurante?.setText("")
            Toast.makeText(this, "Su coctel se ha registrado correctamente", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(
                this,
                "Introduce todos los campos para registrar el coctel",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //creacion de funcion editar datos sqlite
    fun editar(view: View) {
        val con = SQLite(this, "cocteles", null, 1)
        val baseDatos = con.writableDatabase

        val Nombre = txtNombre?.text.toString()
        val Metodo = txtMetodo?.text.toString()
        val Descripcion = txtDescripcion?.text.toString()
        val Decoracion = txtDecoracion?.text.toString()
        val Restaurante = txtRestaurante?.text.toString()
        if (!Nombre.isEmpty() && !Metodo.isEmpty() && !Descripcion.isEmpty() && !Decoracion.isEmpty() && !Restaurante.isEmpty()) {

            val registro = ContentValues()
            registro.put("nombre", Nombre)
            registro.put("descripcion", Descripcion)
            registro.put("metodo", Metodo)
            registro.put("decoracion", Decoracion)
            registro.put("restaurante", Restaurante)

            val cant = baseDatos.update("cocteles", registro, "nombre='$Nombre'", null)
            if (cant > 0) {
                Toast.makeText(this, "El coctel se actualizo exitosamente", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "El coctel no se actualizo correctamente", Toast.LENGTH_LONG)
                    .show()
            }

        } else {
            Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_LONG).show()
        }
    }

    //creacion de funcion borrar datos sqlite
    fun borrar(view: View) {
        val con = SQLite(this, "cocteles", null, 1)
        val baseDatos = con.writableDatabase

        val Nombre = txtNombre?.text.toString()

        if (!Nombre.isEmpty()) {
            val cant = baseDatos.delete("cocteles", "nombre='$Nombre'", null)
            if (cant > 0) {
                Toast.makeText(this, "El coctel se ha borrado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El coctel no se ha borrado", Toast.LENGTH_LONG).show()
            }
            txtNombre?.setText("")
        } else {
            Toast.makeText(this, "El nombre tiene que ser valido para borrar", Toast.LENGTH_LONG)
                .show()
        }
    }

}




