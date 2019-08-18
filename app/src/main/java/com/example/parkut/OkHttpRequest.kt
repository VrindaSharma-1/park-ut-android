package com.example.parkut



import okhttp3.*
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.util.HashMap

import java.util.Map



class OkHttpRequest(client: OkHttpClient) {
    internal var client = OkHttpClient()

    init {
        this.client = client
    }

    fun POST(url: String, parameters: HashMap<String, String>, callback: Callback): Call {
        val builder = FormBody.Builder()
        val it = parameters.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>
            builder.add(pair.key.toString(), pair.value.toString())
        }

        val formBody = builder.build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()


        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }
    //val JSON = MediaType.parse("application/json; charset=utf-8")
    fun POSTR(url: String, parameters: String, callback: Callback): Call {


        val body = RequestBody.create(MediaType.parse(parameters),parameters)
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()


        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }


    fun GET(url: String, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }



    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }
}