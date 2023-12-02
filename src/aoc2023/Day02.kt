package aoc2023

import utils.println
import utils.readInput
import kotlin.math.max

fun main() {
    val (year, day) = "2023" to "Day02"
    data class Cubes(val red: Int, val green: Int, val blue: Int)
    data class Game(val gameId: Int, val contents: List<Cubes>)

    fun mapColor(game: String): Game {
        val (gameId, gameValue) = game.split(":")
        val id = gameId.removePrefix("Game ").toInt()

        operator fun List<String>.get(color: String) =
            find { it.endsWith(color) }
                ?.removeSuffix(color)?.trim()?.toInt()
                ?: 0

        val contents = gameValue.split(";").map { set ->
            val pieces = set.trim().split(",")
            Cubes(pieces["red"], pieces["green"], pieces["blue"])
        }
        return Game(
            gameId = id,
            contents = contents
        )
    }

    fun Cubes.isPossibleDraw(supply: Cubes) = red <= supply.red && green <= supply.green && blue <= supply.blue
    fun Cubes.power() = red * green * blue
    fun maxOfCubes(cube1: Cubes, cube2: Cubes) = Cubes(max(cube1.red, cube2.red), max(cube1.green, cube2.green), max(cube1.blue, cube2.blue))
    fun part1(input: List<String>): Int {
        val supply = Cubes(red = 12, green = 13, blue = 14)
        return input.map { mapColor(it) }
            .filter {
                it.contents.all { cubes -> cubes.isPossibleDraw(supply)}
            }
            .sumOf { it.gameId }
    }

    fun part2(input: List<String>): Int {
        return input.map { mapColor(it) }.sumOf {
            it.contents
                .reduce { left, right -> maxOfCubes(left, right) }
                .power()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
