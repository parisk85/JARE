package gr.parisk85.jare.core

import spock.lang.Specification
import spock.lang.Unroll

class BasicRuleTest extends Specification {

    @Unroll
    def "expects to run then clause only when condition is true"() {
        given:
        def actual = new StringBuilder("This is");

        def tester = RuleBuilder.given(StringBuilder)
                .when { it -> condition }
                .then { it -> it << " a test." }

        when:
        tester.run(actual)

        then:
        expected == actual.toString()

        where:
        condition | expected
        true      | "This is a test."
        false     | "This is"
    }

    def "expects to throw when condition is true"() {
        given:
        def tester = RuleBuilder.given(Boolean.class)
                .when { it }
                .thenThrow { RuntimeException }

        when:
        tester.run(true)

        then:
        thrown(RuntimeException)
    }

    def "expects not to throw when condition is false"() {
        given:
        def tester = RuleBuilder.given(Boolean.class)
                .when { it }
                .thenThrow { RuntimeException }

        when:
        tester.run(false)

        then:
        noExceptionThrown()
    }

    def "expects to run then clause without when clause"() {
        given:
        def actual = new StringBuilder("This")

        def tester = RuleBuilder.given(StringBuilder).then { it -> it.append(" is a test.") }

        when:
        tester.run(actual)

        then:
        "This" != actual.toString()
        "This is a test." == actual.toString()
    }

    //more tests
    //expect to throw without when
    //don't crush on null objects
}
