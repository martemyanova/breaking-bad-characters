package com.techtask.breakingbadcharacters.domain.model

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val birthday: String,
    val occupation: List<String>,
    val status: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String,
    val category: String,
    val betterCallSaulAppearance: List<Int>
)
