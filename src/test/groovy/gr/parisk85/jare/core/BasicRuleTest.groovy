package gr.parisk85.jare.core

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class BasicRuleTest extends Specification {

    @Shared
    StringBuilder actual

    void setup() {
        actual = new StringBuilder("This");
    }

    @Unroll
    def "expects to run then clause only when condition is true"() {
        when:
        RuleBuilder.given(StringBuilder)
                .when { it -> condition }
                .then { it -> it << " is a test." }.run(actual)

        then:
        expected == actual.toString()

        where:
        condition | expected
        true      | "This is a test."
        false     | "This"
    }

    def "expects to throw when condition is true"() {
        when:
        RuleBuilder.given(Boolean.class)
                .when { it }
                .thenThrow { RuntimeException }
                .run(true)

        then:
        thrown(RuntimeException)
    }

    def "expects not to throw when condition is false"() {
        when:
        RuleBuilder.given(Boolean.class)
                .when { it }
                .thenThrow { RuntimeException }
                .run(false)

        then:
        noExceptionThrown()
    }

    def "expects to run then clause without when clause"() {
        when:
        RuleBuilder.given(StringBuilder)
                .then { it -> it << " is a test." }
                .run(actual)

        then:
        "This" != actual.toString()
        "This is a test." == actual.toString()
    }

    def "expects to run thenThrow without clause"() {
        when:
        RuleBuilder.given(String)
                .thenThrow { RuntimeException }
                .run("")

        then:
        thrown(RuntimeException)
    }

    def "expects to run successfully on empty feed"() {
        when:
        RuleBuilder.given(StringBuilder)
                .when { it -> true }
                .then { it -> it << " is a test." }
                .run()

        then:
        "This" == actual.toString()
        noExceptionThrown()
    }

    @Unroll
    def "expects not to throw exception on empty feed"() {
        when:
        RuleBuilder.given(StringBuilder)
                .when { it -> condition }
                .thenThrow { RuntimeException }
                .run()

        then:
        noExceptionThrown()

        where:
        condition | _
        true      | _
        false     | _
    }

    def "expects to run successfully on null feed"() {
        when:
        RuleBuilder.given(StringBuilder)
                .when { it -> true }
                .then { it -> it << " is a test." }
                .run(null)

        then:
        "This" == actual.toString()
        noExceptionThrown()
    }

    @Unroll
    def "expects not to throw exception on null feed"() {
        when:
        RuleBuilder.given(StringBuilder)
                .when { it -> condition }
                .thenThrow { RuntimeException }
                .run(null)

        then:
        noExceptionThrown()

        where:
        condition | _
        true      | _
        false     | _
    }
}
