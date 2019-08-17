package com.example.parkut

import android.location.Address

class Post {
    var address: String? = null
    var id: String? = null
    var name: String? = null
    var spots: List<String>? = null

    constructor() : super() {}

    constructor(address: String, id: String, name: String, spots: List<String>) : super() {
        this.address = address
        this.id = id
        this.name = name
        this.spots = spots
    }
}
