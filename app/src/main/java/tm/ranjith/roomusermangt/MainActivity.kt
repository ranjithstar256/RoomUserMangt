package tm.ranjith.roomusermangt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import kotlinx.coroutines.launch
import tm.ranjith.roomusermangt.ui.theme.RoomUserMangtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomUserMangtTheme {
                val db = Room.databaseBuilder(
                    applicationContext,
                    WorldDatabase::class.java,
                    "worlddbexpl.db"
                ).build()

                val repository = Repository(db.worlddao())
                val viewModel = WorldViewModel(repository)
                UiCode(viewModel)
            }
        }
    }
}

@Composable
fun UiCode(viewModel: WorldViewModel) {
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var allUsers by remember { mutableStateOf(emptyList<World>()) }
    var retrievedPassword by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFDBE792))
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                scope.launch {
                    val newUser = World(0, name, mobile, password)
                    viewModel.insertUser(newUser)
                    result = "User saved successfully"
                }
            }) {
                Text(text = "Save")
            }
            Button(onClick = {
                scope.launch {
                    val fetchedPassword = viewModel.getPassword(mobile)
                    result = if (fetchedPassword == password) {
                        "Login successful"
                    } else {
                        "Invalid credentials"
                    }
                }
            }) {
                Text(text = "Sign In")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = result)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                retrievedPassword = viewModel.getPassword(mobile)
            }
        }) {
            Text(text = "Get Password by Mobile")
        }
        if (retrievedPassword.isNotEmpty()) {
            Text(text = "Password for $mobile: $retrievedPassword")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                scope.launch {
                    allUsers = viewModel.getAllWorld()
                }
            }) {
                Text(text = "Retrieve All")
            }
            Button(onClick = {
                scope.launch {
                    val userId = viewModel.getId(name)
                    viewModel.deleteUser(
                        World(userId, name, mobile, "")
                    )
                    result = "User deleted"
                }
            }) {
                Text(text = "Delete User")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (allUsers.isNotEmpty()) {
            Column() {
                Text("All Users:", fontSize = 24.sp, modifier = Modifier.padding(10.dp))
                allUsers.forEach { user ->
                    Text(text = "" +
                            "Name: ${user.Name}, " +
                            "Mobile: ${user.Mobile}, " +
                            "Password: ${user.Password} \n"
                    , fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}
