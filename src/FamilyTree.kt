import kotlin.math.max

fun main() {
    val me = getMe()

    println(me)
    println("Number Of Relatives: ${me.numberOfRelatives}")

    println("Siblings: ")
    me.siblings.forEach { println(it) }
}

fun getMe(): Person {
    val me = Person("Andrii", 23)
    genRelatives(me)
    return me
}

// Arrays of names
val maleNames = arrayOf("Alex", "Victor", "Roma", "Vova")
val femaleNames = arrayOf("Vita", "Olena", "Svitlana", "Kate")
val allNames = femaleNames + maleNames

// Generate Relatives
fun genRelatives(person: Person, numberOfGenerations: Int = 2) {
    val mother = Person(femaleNames.random(), person.age + (5..15).random())
    val father = Person(maleNames.random(), person.age + (5..15).random())
    person.mother = mother
    person.father = father
    mother.children.add(person)
    father.children.add(person)

    genSiblings(mother, father)

    // go to next generation or exit
    if (numberOfGenerations == 1) {
        return
    } else {
        genRelatives(requireNotNull(person.father), numberOfGenerations - 1)
        genRelatives(requireNotNull(person.mother), numberOfGenerations - 1)
    }
}

// Generate siblings
fun genSiblings(mother: Person, father: Person) {

    // random(0..2) number of siblings
    val n = (0..2).random()

    if (n > 0) {
        // max age of children
        val maxAge = max(father.age, mother.age) - 2
        val siblings = MutableList(n) {
            Person(allNames.random(), (1..maxAge).random()).apply {
                this.mother = mother
                this.father = father
            }
        }
        mother.children.addAll(siblings)
        father.children.addAll(siblings)
    }
}