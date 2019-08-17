package com.example.parkut

class Garage_Model {

    var name: String? = null
    var address: String? = null
    var spots: String? = null
    var id: String? = null

    fun getAddresses(): String {
        return address.toString()
    }

    fun setAddresses(address: String) {
        this.address = address
    }
    fun getIds(): String {
        return id.toString()
    }

    fun setIds(id: String) {
        this.id = id
    }
    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }



    fun getSpotss(): String {
        return spots.toString()
    }

    fun setSpotss(spots: String) {
        this.spots = spots
    }
}