###JARE

######Just Another Rules Engine
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)


#### What is JARE?

JARE is just another rules engine! It is minimalistic library to provide a rules engine (JAREngine) to software clients. Clients are able to run individual rules or multiple rules using JAREngine.

#### Examples

##### 1. Using simple rules

Provide the class to the given method and feed the rule when you run it:

```
RuleBuilder.given(String.class)
        .then(s -> System.out.println("Hello " + s))
        .run("JARE"); 
```

Using a when clause:

```
RuleBuilder.given(String.class)
    .when(s -> "JARE".equals(s))
    .then(s -> System.out.println("Hello " + s))
    .run("JARE");
```

Another example:

```
StringBuilder test = new StringBuilder();

RuleBuilder.given(StringBuilder.class)
    .when(s -> s.toString().isEmpty())
    .then(s -> s.append("Hello JARE"))
    .run(test);

System.out.println(test);
```

Let's throw an exception:

```
RuleBuilder.given(Boolean.class)
    .when(Boolean::booleanValue)
    .thenThrow(RuntimeException::new)
    .run(true);
```

##### 2. Using JAREngine

You can chain multiple rules and fire the engine:

```
JAREngine.feed("JARE")
    .addRule(r -> r.when(String::isEmpty).then(s -> System.out.println("Oh no!")))
    .addRule(r -> r.when(s -> !s.isEmpty()).then(s -> System.out.println("Hello " + s)))
    .fire();
```

You can also feed the engine with multiple inputs using a List or variable arguments:

```
JAREngine.feed("JARE", "", "me")
    .addRule(r -> r.when(String::isEmpty).then(s -> System.out.println("Oh no!")))
    .addRule(r -> r.when(s -> !s.isEmpty()).then(s -> System.out.println("Hello " + s)))
    .fire();
```

Thank you for using JARE!
***