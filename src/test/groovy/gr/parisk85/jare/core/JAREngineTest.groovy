package gr.parisk85.jare.core

import spock.lang.Specification

class JAREngineTest extends Specification {

    StringBuilder actual

    def setup() {
        actual = new StringBuilder("This")
    }

    def "expects to run then clauses only when conditions are true"() {
        when:
        JAREngine.feed(actual)
                .addRule { rule -> rule.when { false }.thenThrow { RuntimeException } }
                .addRule { rule -> rule.when { true }.then { it << " is" } }
                .addRule { rule -> rule.when { false }.then { it << " not" } }
                .addRule { rule -> rule.when { true }.then { it << " a test." } }
                .fire()

        then:
        "This is a test." == actual.toString()
    }

    def "expects to throw only when condition is true"() {
        when:
        JAREngine.feed(actual)
                .addRule { rule -> rule.when { false }.thenThrow { ArithmeticException } }
                .addRule { rule -> rule.when { false }.thenThrow { ArrayIndexOutOfBoundsException } }
                .addRule { rule -> rule.when { true }.thenThrow() { RuntimeException } }
                .addRule { rule -> rule.when { false }.thenThrow() { IllegalArgumentException } }
                .fire()

        then:
        thrown(RuntimeException)
    }

    def "expects to skip on empty feed"() {
        when:
        JAREngine.feed()
                .addRule { rule -> rule.when { it -> false }.thenThrow { ArithmeticException } }
                .addRule { rule -> rule.when { it -> false }.thenThrow { ArrayIndexOutOfBoundsException } }
                .addRule { rule -> rule.when { it -> true }.thenThrow() { RuntimeException } }
                .addRule { rule -> rule.when { it -> false }.thenThrow() { IllegalArgumentException } }
                .fire()

        then:
        noExceptionThrown()
    }

    def "expects to skip on null feed"() {
        when:
        JAREngine.feed(null)
                .addRule { rule -> rule.when { it -> false }.thenThrow { ArithmeticException } }
                .addRule { rule -> rule.when { it -> false }.thenThrow { ArrayIndexOutOfBoundsException } }
                .addRule { rule -> rule.when { it -> true }.thenThrow() { RuntimeException } }
                .addRule { rule -> rule.when { it -> false }.thenThrow() { IllegalArgumentException } }
                .fire()

        then:
        noExceptionThrown()
    }
}
