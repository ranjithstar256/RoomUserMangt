package tm.ranjith.roomusermangt

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorldDAO {


    @Insert
    suspend fun insertNewData(world: World) // paused and resume // co routine


    @Query("select Password from WORLD where Mobile = :name")
    suspend fun getPasswordByMobile(name: String): String

    @Insert
    suspend fun addNewUser(wrd: World)

    @Query("SELECT * from World")
    suspend fun getAllWorld(): List<World>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wrd: World)

    @Update
    suspend fun update(wrd: World)

    @Delete
    suspend fun delete(wrd: World)

    @Query("SELECT Mobile FROM World WHERE Name LIKE :namex")
    suspend fun getpw(namex: String): String

    //@Query("select * from World where name like name")
    // @Query("SELECT * FROM notes WHERE id=:noteId")
    //   @Query("SELECT * FROM task WHERE task LIKE :taskname ")

    @Query("SELECT itemId FROM World where Name = :itm")
    suspend fun getId(itm:String):Int


}