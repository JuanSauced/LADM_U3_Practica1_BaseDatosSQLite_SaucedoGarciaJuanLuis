package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class Propietario (este:Context) {

    var curp=""
    var nombre=""
    var telefono=""
    var edad=0
    private var este=este
    private var err=""
    var listaCurp=ArrayList<String>()


    fun insertar():Boolean{
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        err=""
        try {
            val tabla =baseDatos.writableDatabase

            var datos = ContentValues()
            datos.put("CURP",curp)
            datos.put("NOMBRE",nombre)
            datos.put("TELEFONO",telefono)
            datos.put("EDAD",edad)

            var resultado=tabla.insert("PROPIETARIO",null,datos)
            if (resultado==-1L){
                return false
            }

        }catch (err:SQLiteException){
            this.err=err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }
    //Lista
    fun mostrarLista(): ArrayList<String> {
        val baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        try {
            val tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT CURP,NOMBRE FROM PROPIETARIO"
            var cursor= tabla.rawQuery(SQL_SELECT, null)
            if (cursor.moveToFirst()){
            do{
                arreglo.add("${cursor.getString(0)}   ${cursor.getString(1)}")
                listaCurp.add(cursor.getString(0))
            }while (cursor.moveToNext())
        }

        } catch (err:SQLiteException){
            AlertDialog.Builder(este)
                .setMessage(err.message)
                .show()
        }  finally {
            baseDatos.close()
        }
        return arreglo
    }


    fun mostrarTodos():ArrayList<Propietario>{
            err=""
            var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
            var arreglo= ArrayList<Propietario>()
            try {
                var tabla=baseDatos.readableDatabase
                var SQL_SELECT= "SELECT * FROM PROPIETARIO"

                var cursor= tabla.rawQuery(SQL_SELECT, null)
                if(cursor.moveToFirst()){
                    do {
                        val propietario=Propietario(este)
                        propietario.curp=cursor.getString(0)
                        propietario.nombre=cursor.getString(1)
                        propietario.telefono=cursor.getString(2)
                        propietario.edad=cursor.getInt(3)
                        arreglo.add(propietario)
                    }while (cursor.moveToNext())
                }
            }catch (err:SQLiteException){
                this.err=err.message!!
            }finally {
                baseDatos.close()
            }
        return arreglo
    }
    fun mostrarCurp(curpBuscar:String): ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
            var arreglo=ArrayList<String>()

        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM PROPIETARIO  WHERE CURP=?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(curpBuscar))
            if(cursor.moveToFirst()){
                arreglo.add(cursor.getString(0))
                arreglo.add(cursor.getString(1))
                arreglo.add(cursor.getString(2))
                arreglo.add(cursor.getInt(3).toString())
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
//Mostrar por Nombre
fun mostrarNombre(nombreBuscar:String):ArrayList<String>{
    err=""
    var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
    val propietario=Propietario(este)
    var arreglo=ArrayList<String>()
    listaCurp.clear()
    try {
        var tabla=baseDatos.readableDatabase
        var SQL_SELECT= "SELECT * FROM PROPIETARIO  WHERE NOMBRE=?"

        var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(nombreBuscar))
        if(cursor.moveToFirst()){
            arreglo.add("CURP: ${cursor.getString(0)}   ${cursor.getString(1)}  TELEFONO: ${cursor.getString(2)} EDAD: ${cursor.getInt(3)}")
        }
    }catch (err:SQLiteException){
        this.err=err.message!!
    }finally {
        baseDatos.close()
    }
    return arreglo
}
    //Mostrar por Nombre
    fun mostrarTelefono(telefonoBuscar:String):ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        val propietario=Propietario(este)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM PROPIETARIO  WHERE NOMBRE=?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(telefonoBuscar))
            if(cursor.moveToFirst()){
                arreglo.add("CURP: ${cursor.getString(0)}   ${cursor.getString(1)}  TELEFONO: ${cursor.getString(2)} EDAD: ${cursor.getInt(3)}")
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    //Buscar en un intervalo
    fun mostrarEdad(edadBuscar1:String,edadBuscar2:String):ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        val propietario=Propietario(este)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM PROPIETARIO  WHERE EDAD BETWEEN ? AND ?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(edadBuscar1,edadBuscar2))
            if(cursor.moveToFirst()){
                do{
                arreglo.add("CURP: ${cursor.getString(0)}   ${cursor.getString(1)}  TELEFONO: ${cursor.getString(2)} EDAD: ${cursor.getInt(3)}")
                }while (cursor.moveToNext())
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }


    //Actualizar
    fun actualizar():Boolean {
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        try {
            var tabla=baseDatos.writableDatabase
            val datosActualizados=ContentValues()
            datosActualizados.put("NOMBRE",nombre)
            datosActualizados.put("TELEFONO",telefono)
            datosActualizados.put("EDAD",edad)

            val resultado=tabla.update("PROPIETARIO",datosActualizados,"CURP=?", arrayOf(curp))

            if(resultado==0){
                return false
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

    fun eliminar(curpEliminar:String):Boolean{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        try {
            var tabla=baseDatos.writableDatabase

            val respuesta=tabla.delete("PROPIETARIO","CURP=?", arrayOf(curpEliminar))
            if (respuesta==0){
                return false
                }
        }catch (err:SQLiteException){
            this.err=err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

    fun mostrarNombreLike(nombreBuscar:String):ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        val propietario=Propietario(este)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT CURP,NOMBRE FROM PROPIETARIO  WHERE NOMBRE LIKE '%' || ? || '%'"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(nombreBuscar))
            if(cursor.moveToFirst()){
                arreglo.add("CURP: ${cursor.getString(0)}   ${cursor.getString(1)}  ")
                listaCurp.add(cursor.getString(0))
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
}

/*
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        try {

        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
* */