package org.example

fun main(args: Array<String>) {
    println("Args length: ${args.size}")
    args.forEach {
        println("Argument: $it")
    }
}