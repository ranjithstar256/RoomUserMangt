package tm.ranjith.roomusermangt

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [World::class], version = 1, exportSchema = false)
abstract class WorldDatabase :RoomDatabase()  {
    abstract fun worlddao(): WorldDAO
}