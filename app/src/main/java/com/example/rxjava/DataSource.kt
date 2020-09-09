package com.example.rxjava

class DataSource {
    companion object {
        fun createTasksList(): List<Task> {
            return listOf(
                Task("Take out the trash", true, 3),
                Task("Walk the dog", false, 2),
                Task("Make my bed", true, 1),
                Task("Unload the dishwasher", false, 0),
                Task("Make dinner", true, 5)
            )
        }
    }
}