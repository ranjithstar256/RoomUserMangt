package tm.ranjith.roomusermangt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorldViewModel(private val repository: Repository) : ViewModel() {

    // Function to insert a user
    fun insertUser(user: World) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    // Function to get all users
    suspend fun getAllWorld(): List<World> {
        return withContext(Dispatchers.IO) {
            repository.getAllUsers()
        }
    }

    // Function to update a user
    fun updateUser(user: World) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    // Function to delete a user
    fun deleteUser(user: World) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    // Function to get password by mobile
    suspend fun getPassword(mobile: String): String {
        return withContext(Dispatchers.IO) {
            repository.getPassword(mobile) ?: "Password not found"
        }
    }

    // Function to get ID by name
    suspend fun getId(name: String): Int {
        return withContext(Dispatchers.IO) {
            repository.getId(name)
        }
    }
}
