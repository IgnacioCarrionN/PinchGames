package dev.carrion.pinchgames.data.network

import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkDataSource(private val api: PinchGamesApi) {
    fun getGames(
        limit: Int,
        offset: Int,
        onSuccess: (data: List<NetworkDataEntity.Game>) -> Unit,
        onError: (error: String) -> Unit){

//        val requestBody = NetworkDataEntity.GameBodyRequest("*", limit, offset)
//
//        val retrofitRequestBody = RequestBody.create(MediaType.parse("text/plain"), requestBody.toRawRequest())
//
//        api.getGames(retrofitRequestBody).enqueue(
//            object : Callback<List<NetworkDataEntity.Game>> {
//                override fun onFailure(call: Call<List<NetworkDataEntity.Game>>, t: Throwable) {
//                    Log.d("NetworkDataSource", t.message)
//                    onError(t.message ?: "Unknown Error")
//                }
//
//                override fun onResponse(
//                    call: Call<List<NetworkDataEntity.Game>>,
//                    response: Response<List<NetworkDataEntity.Game>>
//                ) {
//                    if(response.isSuccessful){
//                        val games = response.body()
//                        Log.d("NetworkDataSource", games.toString())
//                        games?.let(onSuccess)
//                    }else{
//                        Log.d("NetworkDataSource", response.errorBody()?.toString())
//                        onError(response.errorBody()?.toString() ?: "Unknown Error")
//                    }
//                }
//
//            }
//        )

        // Query to get all fields and the cover details for each game
        val fields = "*,cover.*"
        api.getGamesLegacy(fields, limit, offset).enqueue(
            object : Callback<List<NetworkDataEntity.Game>> {
                override fun onFailure(call: Call<List<NetworkDataEntity.Game>>, t: Throwable) {
                    onError(t.message ?: "Unknown Error")
                }

                override fun onResponse(
                    call: Call<List<NetworkDataEntity.Game>>,
                    response: Response<List<NetworkDataEntity.Game>>
                ) {
                    if(response.isSuccessful){
                        val games = response.body()
                        Log.d("NetworkDataSource", games.toString())
                        games?.let(onSuccess)
                    }else{
                        onError(response.errorBody()?.toString() ?: "Unknown Error")
                    }
                }
            }
        )
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}