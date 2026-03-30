package com.smartflow.fcmbtest.data.repository

import com.smartflow.fcmbtest.data.model.Names

class NamesRepository {
    private val names = listOf(
        Names(1, "John Doe"),
        Names(2, "Akeem Rotimi"),
        Names(3, "Terry Oluwatobi"),
        Names(4, "Amos Adamu"),
        Names(5, "Brown Egbo"),
        Names(6, "Joshua Zirkze"),
        Names(7, "Josh Maja"),
        Names(8, "Alex Iwobi"),
        Names(9, "Manuel Kimmick"),
        Names(10, "Bruno Fernandes"),
        Names(11, "Melvin Okubo"),
        Names(12, "Esther Innocent"),
        Names(13, "Vincent Akanji"),
    )

    fun getNames(): List<Names> = names

    fun filterNames(query: String): List<Names> {
        return names.filter {
            it.fullName.contains(query, ignoreCase = true)
        }
    }
}
