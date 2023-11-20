package com.example.astonfragments.contacts

import io.github.serpro69.kfaker.Faker

class UserRepository {
    private var dataList = mutableListOf<User>()
    private val faker = Faker()

    init {
        createListUser()
    }

    fun getDataList(): MutableList<User> {
        return dataList
    }

    fun updateInfoUser(newUser: User) {
        val indexPosition = dataList.indexOfFirst { newUser.id == it.id }
        dataList.removeAt(indexPosition)
        dataList.add(indexPosition, newUser)
    }

    private fun createListUser() {
        faker.unique.configuration {
            enable(faker::name)
            enable(faker::phoneNumber)
        }
        val listName = List(100) { faker.name.name() }
        val phoneNumber = faker.phoneNumber.phoneNumber()
        for (id in 1..100) {
            dataList.add(User(id, listName[id - 1], phoneNumber, false))
        }
    }
}