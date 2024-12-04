package tm.ranjith.roomusermangt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.Room
import kotlinx.coroutines.launch
import tm.ranjith.roomusermangt.ui.theme.RoomUserMangtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomUserMangtTheme {
                val db =
                    Room.databaseBuilder(
                        applicationContext,
                        WorldDatabase::class.java,
                        "worlddbexpl.db"
                    ).build()

                SaveData(db)

            }
        }
    }
}

@Composable
fun SaveData(db: WorldDatabase) {

    val scope = rememberCoroutineScope()
    var Name by remember { mutableStateOf("") }
    var Mobile by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    var reslt by remember { mutableStateOf("") }


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        TextField(value = Name, onValueChange = { Name = it })
        TextField(value = Mobile, onValueChange = { Mobile = it })
        TextField(value = Password, onValueChange = { Password = it })
        val worldOneData = World(
            0,
            Name,
            Mobile,
            Password
        )
        Button(onClick = {

            scope.launch {
                db.worlddao().insertNewData(worldOneData)
            }

        }) {
            Text(text = "save")
        }

        Button(onClick = {
            scope.launch {
                reslt=   db.worlddao().getPasswordByMobile(Mobile)

                if (reslt.equals(Password)){
                    reslt="success"
                }else{
                    reslt="invalid credentials"
                }
            }
        }) {
            Text(text = "sign in")
        }
        Text(text = reslt)

    }



}
