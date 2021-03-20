package com.techtask.breakingbadcharacters.data.remote.api.schema

import com.google.gson.annotations.SerializedName

data class CharacterSchema(
    @SerializedName("char_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("occupation") val occupation: Array<String>,
    @SerializedName("img") val imageUrl: String,
    @SerializedName("status") val status: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("appearance") val appearance: Array<Int>,
    @SerializedName("portrayed") val portrayed: String,
    @SerializedName("category") val category: String,
    @SerializedName("better_call_saul_appearance") val betterCallSaulAppearance: Array<Int>
)
