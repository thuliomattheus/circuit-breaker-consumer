package projects.medium.circuitbreakerconsumer

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

//@SpringBootTest
class CircuitBreakerConsumerApplicationTests {

	@Test
	fun contextLoads() {
		gradingStudents(arrayOf(73, 67, 38, 33))
	}

	fun gradingStudents(grades: Array<Int>): Array<Int> {
		var finalGrades = emptyArray<Int>()

		for (grade in grades) {
			finalGrades +=
				if (grade < 38) grade
				else if (nextMultipleOfFive(grade) - grade >= 3) grade
				else nextMultipleOfFive(grade)
		}

		for (value in finalGrades) {
			System.err.println(value)
		}

		return finalGrades
	}

	private fun nextMultipleOfFive(number: Int) =
		5 * (number/5 + 1)

}
