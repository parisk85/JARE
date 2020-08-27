package gr.parisk85.jare.core

import spock.lang.Specification

class JAREngineTest extends Specification {

    def "expects to run then clauses only when conditions are true"() {
        given:
        def actual = new StringBuffer("This")

        when:
        JAREngine.feed(actual)
                .addRule { rule -> rule.when { false }.thenThrow { RuntimeException } }
                .addRule { rule -> rule.when { true }.then { it -> it.append(" is") } }
                .addRule { rule -> rule.when { false }.then { it -> it.append(" not") } }
                .addRule { rule -> rule.when { true }.then { it -> it.append(" a test.") } }
                .fire()

        then:
        "This is a test." == actual.toString()
    }
}
