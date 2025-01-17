package adventofcode.aoc2023

fun main() {
    val test = false
    val verbose = false

    val runner = Runner()

    if (verbose) {
        (1..25).forEach { dayNr -> runner.runDay(dayNr, test=test, verbose=true) }
    } else {
        println("Day Name                            Puzzle 1   Puzzle 2")
        println("--------------------------------------------------------")
        (1..25).forEach { dayNr -> runner.runDay(dayNr, test=false, verbose=false) }
        println("--------------------------------------------------------")
    }
}

class Runner() {
    private val basePath = this.javaClass.name.substringBeforeLast(".")

    fun runDay(dayNr: Int, test: Boolean, verbose: Boolean) {
        val className = "Day%02d".format(dayNr)
        val packageName = basePath
        try {
            val kClass = Class.forName("$packageName.$className").kotlin
            val method = if (verbose)
                kClass.members.find { it.name == "showResultShort" }
            else
                kClass.members.find { it.name == "showResultTimeOnly" }

            val obj = kClass.constructors.first().call(test)
            method!!.call(obj)
        } catch(e: ClassNotFoundException) {
            if (verbose) {
                println("$className not implemented (yet)")
            }
        } catch (otherE: Exception) {
            println("$className runs with exception ${otherE.cause}")
        }
    }
}
