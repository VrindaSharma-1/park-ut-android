package com.example.parkut

class Garage_Model {

    var name: String? = null
    var address: String? = null

    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }

    fun getAddresses(): String {
        return address.toString()
    }

    fun setAddresses(name: String) {
        this.address = name
    }

}