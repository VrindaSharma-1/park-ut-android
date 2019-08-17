package com.example.parkut


    public class Model{
        lateinit var address:String
        lateinit var id:String
        lateinit var name:String

        lateinit var spots:String


        constructor(address:String,id: String,name:String,spots:String) {
            this.address = address
            this.id = id
            this.name = name

            this.spots = spots
                   }

        constructor()



}