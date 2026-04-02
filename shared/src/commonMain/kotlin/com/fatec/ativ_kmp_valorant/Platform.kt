package com.fatec.ativ_kmp_valorant

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform