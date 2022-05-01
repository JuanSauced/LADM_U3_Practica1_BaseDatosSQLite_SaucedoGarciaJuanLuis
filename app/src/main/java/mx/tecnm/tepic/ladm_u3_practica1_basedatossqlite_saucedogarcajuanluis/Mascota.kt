package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Mascota(este: Context) {

    var idMascota=0
    var nombre=""
    var raza=""
    var curp=""
    var este=este
    private var err=""
    var listaCurp=ArrayList<String>()
    var listaID=ArrayList<String>()

    fun insertar():Boolean{
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        err=""
        try {
            val tabla =baseDatos.writableDatabase

            var datos = ContentValues()
            datos.put("NOMBRE",nombre)
            datos.put("RAZA",raza)
            datos.put("CURP",curp)

            var resultado=tabla.insert("MASCOTA","IDMASCOTA",datos)
            if (resultado==-1L){
                return false
            }

        }catch (err: SQLiteException){
            this.err=err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

    //Obtiene ciertos campos para llenar la lista
    fun mostrarLista(): ArrayList<String> {
        val baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        var arreglo=ArrayList<String>()
        listaID.clear()
        listaCurp.clear()
        try {
            val tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT IDMASCOTA,NOMBRE,RAZA,CURP FROM MASCOTA"
            var cursor= tabla.rawQuery(SQL_SELECT, null)
            if (cursor.moveToFirst()){
                do{
                    arreglo.add("${cursor.getString(1)}  RAZA: ${cursor.getString(2)}  DE: ${cursor.getString(3)}")
                    listaID.add(cursor.getString(0))
                    listaCurp.add(cursor.getString(3))
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

    fun mostrarRaza(texRaza:String): ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        listaID.clear()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM MASCOTA  WHERE RAZA= ?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(texRaza))
            if(cursor.moveToFirst()){
                do{
                    arreglo.add("Nombre: ${cursor.getString(1)}   Raza: ${cursor.getString(2)}  De ${cursor.getString(3)}")
                    listaCurp.add(cursor.getString(3))
                    listaID.add(cursor.getInt(0).toString())
                }while (cursor.moveToNext())
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarPropietario(texProp:String): ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        listaID.clear()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM MASCOTA  WHERE CURP= ?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(texProp))
            if(cursor.moveToFirst()){
                do{
                    arreglo.add("Nombre: ${cursor.getString(1)}   Raza: ${cursor.getString(2)}  De ${cursor.getString(3)}")
                    listaCurp.add(cursor.getString(3))
                    listaID.add(cursor.getInt(0).toString())
                }while (cursor.moveToNext())
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun mostrarNombre(texNombre:String): ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        var arreglo=ArrayList<String>()
        listaCurp.clear()
        listaID.clear()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM MASCOTA  WHERE NOMBRE= ?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(texNombre))
            if(cursor.moveToFirst()){
                do{
                    arreglo.add("Nombre: ${cursor.getString(1)}   Raza: ${cursor.getString(2)}  De ${cursor.getString(3)}")
                    listaCurp.add(cursor.getString(3))
                    listaID.add(cursor.getInt(0).toString())
                }while (cursor.moveToNext())
            }
        }catch (err:SQLiteException){
            this.err=err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun mostrarId(texId:String): ArrayList<String>{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        var arreglo=ArrayList<String>()
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM MASCOTA  WHERE IDMASCOTA= ?"

            var cursor= tabla.rawQuery(SQL_SELECT,arrayOf(texId))
            if(cursor.moveToFirst()){
                    arreglo.add(cursor.getInt(0).toString())
                    arreglo.add(cursor.getString(1))
                    arreglo.add(cursor.getString(2))
                    arreglo.add(cursor.getString(3))

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
            datosActualizados.put("RAZA",raza)
            datosActualizados.put("CURP",curp)

            val resultado=tabla.update("MASCOTA",datosActualizados,"IDMASCOTA=?", arrayOf(idMascota.toString()))

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
    //Eliminar
    fun eliminar(IDEliminar:String):Boolean{
        err=""
        var baseDatos=BaseDatos(este,"VETERINARIA",null,1)
        try {
            var tabla=baseDatos.writableDatabase

            val respuesta=tabla.delete("MASCOTA","IDMASCOTA=?", arrayOf(IDEliminar))
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


}