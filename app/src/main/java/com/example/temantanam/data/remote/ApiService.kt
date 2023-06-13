package com.example.temantanam.data.remote

import com.example.temantanam.model.AnalyzeEnvironmentModel
import com.example.temantanam.model.AnalyzeEnvironmentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("ranfors")
    fun postAnalyzedData(
        @Body analyzeEnvironmentData : AnalyzeEnvironmentModel
    ) : Call<AnalyzeEnvironmentResponse>
}