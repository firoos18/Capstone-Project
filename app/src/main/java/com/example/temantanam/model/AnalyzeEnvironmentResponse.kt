package com.example.temantanam.model

import com.google.gson.annotations.SerializedName

data class AnalyzeEnvironmentResponse(

	@field:SerializedName("Climate")
	val climate: String,

	@field:SerializedName("Plant")
	val plant: String,

	@field:SerializedName("Description")
	val description: String,

	@field:SerializedName("Rainfall")
	val rainfall: String,

	@field:SerializedName("Altitudes")
	val altitudes: String,

	@field:SerializedName("Photo_Link")
	val photoLink: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("Harvest")
	val harvest: String,

	@field:SerializedName("Scientific_Name")
	val scientificName: String
)
