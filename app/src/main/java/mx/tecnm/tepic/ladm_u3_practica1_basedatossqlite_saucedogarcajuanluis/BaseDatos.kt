package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE PROPIETARIO (CURP VARCHAR(50) PRIMARY KEY NOT NULL,NOMBRE VARCHAR(200),TELEFONO VARCHAR(50),EDAD INTEGER)")
        db.execSQL("CREATE TABLE MASCOTA(IDMASCOTA INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE VARCHAR(200),RAZA VARCHAR(50),CURP VARCHAR(50), FOREIGN KEY(CURP) REFERENCES PROPIETARIO(CURP))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}