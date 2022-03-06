package br.com.antoniojose.criadorcartaovisita.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.antoniojose.criadorcartaovisita.daos.CartaoDao
import br.com.antoniojose.criadorcartaovisita.model.Cartao

@Database(entities = [Cartao::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cartaoDao(): CartaoDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Application): AppDatabase{
               return INSTANCE ?: synchronized(context){
                   var instance = Room.databaseBuilder(context, AppDatabase::class.java, "cartao.db").allowMainThreadQueries().build()

                   INSTANCE = instance
                   instance
               }


        }

    }
}