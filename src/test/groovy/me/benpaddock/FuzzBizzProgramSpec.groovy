package me.benpaddock

import spock.lang.Specification

/**
 * User: pads
 * Date: 07/03/12 
 */
class FuzzBizzProgramSpec extends Specification {

    FuzzBizzProgram program
    def rangeOfNumbers

    def setup() {
        program = new FuzzBizzProgram()
    }

    def "Should print One Hundred Numbers Given A Range Of One Hundred"() {
        given:
        rangeOfNumbers = 100

        when:
        def result = program.run(rangeOfNumbers)

        then:
        result.size() == 100
    }

    def "Should print the word 'Fuzz' for numbers that are multiples of three"()  {
        given:
        rangeOfNumbers = 100

        when:
        def result = program.run(rangeOfNumbers)

        then:
        numbers[indexToTest] == result[indexToTest]

        where:
        numbers = [1, 2, 'Fuzz', 4, 5, 'Fuzz', 7, 8, 'Fuzz', 10]
        indexToTest << [2, 5, 8]
    }

    def "Should print the word 'Bizz' for numbers that are multiples of five"()  {
        given:
        rangeOfNumbers = 100

        when:
        def result = program.run(rangeOfNumbers)

        then:
        numbers[indexToTest] == result[indexToTest]

        where:
        numbers = [1, 2, 3, 4, 'Bizz', 6, 7, 8, 9, 'Bizz']
        indexToTest << [4, 9]
    }

    def "Should print the word 'FuzzBizz' for numbers that are multiples of three and five"()  {
        given:
        rangeOfNumbers = 100

        when:
        def result = program.run(rangeOfNumbers)

        then:
        numbers[indexToTest] == result[indexToTest]

        where:
        numbers = [1, 2, 'Fuzz', 4, 'Bizz', 'Fuzz', 7, 8, 'Fuzz', 'Bizz', 11, 'Fuzz', 13, 14, 'FuzzBizz']
        indexToTest << [14]
    }

    def "Multiples of three print 'Fuzz' and multiples of five print 'Bizz' and multiples of three and five print 'FuzzBizz'"()  {
        given:
        rangeOfNumbers = 100

        when:
        def result = program.run(rangeOfNumbers)

        then:
        result[expectedFuzzIndex] == 'Fuzz'
        result[expectedBizzIndex] == 'Bizz'
        result[expectedFuzzBizzIndex] == 'FuzzBizz'

        where:
        expectedFuzzIndex << [2, 5, 8, 11, 17]
        expectedBizzIndex << [4, 9, 19, 24, 34]
        expectedFuzzBizzIndex << [14, 29, 44, 59, 74]
    }

}
