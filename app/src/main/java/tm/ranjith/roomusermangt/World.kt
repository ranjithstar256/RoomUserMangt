package tm.ranjith.roomusermangt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "World")
data class World (

    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,

    @ColumnInfo(name = "Name")  // column name 1
    var Name : String,


    @ColumnInfo(name = "Mobile")  // column name 2
    var Mobile : String,


    @ColumnInfo(name = "Password")  // column name 3
    var Password : String

    )