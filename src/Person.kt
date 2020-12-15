import java.util.ArrayList

data class Person(
    val name: String,
    val age: Int,
    var mother: Person? = null,
    var father: Person? = null,
    val children: MutableList<Person> = ArrayList<Person>()
) {

    val siblings: Array<Person>
        get() {
            return if (mother != null)
                requireNotNull(mother).children.filter { it != this }.toTypedArray()
            else
                emptyArray()
        }

    val numberOfRelatives
        get() = countRelatives(this)

    private fun countRelatives(person: Person): Int {
        var i = 0
        person.siblings?.let { i += it.size }

        person.mother?.let {
            i++
            i += requireNotNull(person.mother).numberOfRelatives
        }

        person.father?.let {
            i++
            i += requireNotNull(person.father).numberOfRelatives
        }

        return i
    }

    override fun toString(): String {
        return "Person(name=$name, age=$age, mother=${mother ?: "UNKNOWN PERSON"}, father=${father ?: "UNKNOWN PERSON"})"
    }
}