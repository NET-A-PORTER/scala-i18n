# scala-i18n

[![Build Status](https://secure.travis-ci.org/Net-A-Porter/scala-i18n.png?branch=master)](https://travis-ci.org/Net-A-Porter/scala-i18n)
<!--
[![Coverage Status](https://coveralls.io/repos/Net-A-Porter/scala-i18n/badge.png?branch=master)](https://coveralls.io/r/Net-A-Porter/scala-i18n)
-->

`scala-i18n` is a *very* thin Scala wrapper around Java's `java.util.ResourceBundle` to make it a bit nicer to use from Scala code.

# Getting Started

Provided you have you resource bundle files like so:

```
src/main/resources/messages/
├── messages.properties
├── messages_fr.properties
└── messages_fr_CA.properties
```

You can create a `ResourceBundle` reference like so:

```scala
val messages = ResourceBundle("messages/messages")
```

Then you can call methods like so:


Returns the value for the message key `my.greeting.message` from the `messages_fr_CA.properties`.

```scala
val message = messages.get("my.greeting.message", Locale.CANADA_FRENCH)
```

If the message key does not exist in the `messages_fr_CA.properties` file, it will fallback and try the
`messages_fr.properties`. If the message still doesn't exist, it will fallback and try the root `messages.properties`
file.

If the message key does not exist in any files, then `get` will throw a `MissingResourceException`. Alternatively you
can use the `find` method that will either return a `Some(message)` when a message with the specified key exists
or a `None` when NO message with the specified key exists.

```scala
messages.find("my.greeting.message", Locale.ENGLISH) == Some("Hello!")
messages.find("my.non.existent.message", Locale.ENGLISH) == None
```

# Defaults

`getOrElse` lets you specify a default in the instance that no message exists for the specified key:

```scala
messages.getOrElse("my.greeting.message", Locale.ENGLISH, "Default!") == "Hello!"
messages.getOrElse("my.non.existent.message", Locale.ENGLISH, "Default!") == "Default!"
```

# Parameters

You can specify parameters in a resource bundle file like so:

```
my.greeting.message=Hello {0}, you look {1}
```

The you may use `getWithParams` or `findWithParams` like so:

```scala
val message = messages.getWithParams("my.greeting.message", Locale.ENGLISH, "Joe Bloggs", "awful")
message == Some("Hello Joe Bloggs, you look awful")
```

# License

`scala-i18n` is open source software released under the [Apache 2 License](http://www.apache.org/licenses/LICENSE-2.0).