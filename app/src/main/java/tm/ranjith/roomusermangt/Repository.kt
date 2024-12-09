package tm.ranjith.roomusermangt

class Repository(private val worldDao: WorldDAO) {
    // Function to insert a user
    suspend fun insertUser(user: World) {
        worldDao.insert(user)
    }

    // Function to get all users
    suspend fun getAllUsers(): List<World> {
        return worldDao.getAllWorld()
    }

    // Function to update a user
    suspend fun updateUser(user: World) {
        worldDao.update(user)
    }

    // Function to delete a user
    suspend fun deleteUser(user: World) {
        worldDao.delete(user)
    }

    // Function to get password by mobile number
    suspend fun getPassword(mobile: String): String {
        return worldDao.getPasswordByMobile(mobile) ?: "Password not found"
    }

    // Function to get user ID by name
    suspend fun getId(name: String): Int {
        return worldDao.getId(name)
    }
}
