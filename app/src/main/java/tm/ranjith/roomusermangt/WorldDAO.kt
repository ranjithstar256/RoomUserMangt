package tm.ranjith.roomusermangt

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorldDAO {


    @Insert
    suspend fun insertNewData(world: World) // paused and resume // co routine


    @Query("select Password from WORLD where Mobile = :name")
    suspend fun getPasswordByMobile(name: Int): String



}