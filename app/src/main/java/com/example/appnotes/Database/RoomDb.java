package com.example.appnotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appnotes.Model.Notes;


@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class RoomDb  extends RoomDatabase {

    private static RoomDb database;

    private static String DATABASE_NAME = "NotesApp";

    // Se utiliza el patrón Singleton para garantizar que solo haya una instancia de la BD en toda la aplicación.
    public synchronized  static RoomDb getInstance(Context context) {
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDb.class,DATABASE_NAME)
                    // Permite consultas en el hilo principal.
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    // El DAO define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la tabla de notas.
    public abstract MainDAO mainDAO();
}
