package dev.einsjannis.acacia.protocol

enum class Bound {
    CLIENT,
    SERVER;

    val opposite get() = if (this == CLIENT) SERVER else CLIENT
}
