# Android CodeStyle

## Description
This document serves as the definition of Celerik’s Android coding standards for source code in the Kotlin Programming Language. The code style described below is inspired by Google's and Kotlin's style guides, as well as our own formed standard according to the experiences we have had and will have in our team (i.e. code reviews).

The issues covered span not only aesthetic issues of formatting, but other types of conventions or coding standards as well. However, this document focuses primarily on the hard-and-fast rules that we follow, and avoids giving advice that isn’t clearly enforceable (whether by human or tool).

## Inspiration
Let's see this code style like an extension of the language guidance drawn from:

- [Android Kotlin style guide](https://android.github.io/kotlin-guides/style.html).
- [Kotlin Coding Conventions](https://kotlinlang.org/docs/reference/coding-conventions.html).
- [Android contributors style guide](https://source.android.com/source/code-style.html).

## Notions
In below, you will find the notions:

(1). Strongly recommended.

(2). Recommended.

(3). Preferred.

(4). Suggested.

Where at (1) refers that it's mandatory taking these advices into account, and the rest ones, are also important but at a lower level. This stuff could depend on your context.

## Table of Contents

- [Nomenclature](#nomenclature)
  + [Packages](#packages)
  + [Classes & Interfaces](#classes--interfaces)
  + [Methods](#methods)
  + [Fields](#fields)
  + [Variables & Parameters](#variables--parameters)
- [Declarations](#declarations)
  + [Visibility Modifiers](#visibility-modifiers)
  + [Classes](#classes)
  + [Data Type Objects](#data-type-objects)
  + [Enum Classes](#enum-classes)
- [Spacing](#spacing)
  + [Indentation](#indentation)
  + [Enum Classes](#enum-classes)
  + [Line Length](#line-length-open-to-discussion)
  + [Enum Classes](#enum-classes)
  + [Vertical Spacing](#vertical-spacing)
- [Comments](#comments)
- [Semicolons](#semicolons)
- [Getters & Setters](#getters--setters)
- [Brace Style](#brace-style)
- [When Statements](#when-statements-open-to-discussion)
  + [Type Inference](#type-inference)
  + [Constants vs. Variables](#constants-vs-variables)
  + [Companion Objects](#companion-objects)
  + [Nullable Types](#nullable-types)
  + [Optional Types](#optional-types)
- [Language](#language-open-to-discussion)
- [Trailing Commas](#trailing-commas)

## Nomenclature

On the whole, naming should follow Java standards, as Kotlin is a JVM-compatible language.

### Packages
###### Strongly recommended
Package names are similar to Java: all __lower-case__, multiple words concatenated together, without hypens or underscores:

**WRONG:**

```kotlin
com.celerik.auth.use_cases
```

**RIGHT:**

```kotlin
com.celerik.auth.usecases
```

### Classes & Interfaces
###### Strongly recommended

Written in __UpperCamelCase__. For example `TermsAndConditionsDialogFragment`.

### Methods
###### Strongly recommended

Written in __lowerCamelCase__. For example `onViewActive`.

### Fields
###### Strongly recommended

Written in __lowerCamelCase__.

Example field names:

```kotlin
data class MyDataClass (
  var publicField: Int = 0,
  val person: Person = Person(),
  private var privateField: Int?
)
```

Constant values in the companion object should be written in __uppercase__, with an underscore separating words:

```kotlin
companion object {
  const val MAX_ALLOWABLE_RETRY_ATTEMPTS = 3
}  
```

### Variables & Parameters
###### Strongly recommended

Written in __lowerCamelCase__.

Single character values and abbreviations must be avoided.

In code, acronyms should be treated as words.

**WRONG:**

```kotlin
val phoneUIModel: PhoneUIModel

fun method(XMLHTTPRequest: Request)

URL: String? 

findItemByID

initView()
```
**RIGHT:**

```kotlin
val phoneUiModel: PhoneUiModel

fun method(xmlHttpRequest: Request)

url: String?

findItemById

initializeView()
```

## Declarations

### Visibility Modifiers
###### Recommended

Only include visibility modifiers if you need something other than the default of public.

**WRONG:**

```kotlin
public val wideOpenProperty = "I am public"
```

**RIGHT:**

```kotlin
val wideOpenProperty = "I am public too"
```

### Classes
###### Strongly recommended

Exactly one class per source file, although inner classes are encouraged where scoping appropriate.

### Data Type Objects
###### Strongly recommended

Prefer data classes for simple data holding objects.

**WRONG:**

```kotlin
class Person(private val name: String) {
  override fun toString() : String {
    return "Person(name=$name)"
  }
}
```

**RIGHT:**

```kotlin
data class Person(val name: String)
```

### Enum Classes
###### Suggested

Enum classes without methods may have its values formatted without line-breaks, as follows:

```kotlin
enum class CompassDirection { 
  EAST, NORTH, WEST, SOUTH 
}
```

## Spacing
###### Preferred

Spacing is especially important in our code, as code needs to be easily readable. Even, we can separate code into blocks related by its context.

**WRONG:**

```kotlin
binding.apply {
  textViewClientName.text = orderInfo.clientName
  textViewDeliveryDate.isGone = orderInfo.deliveryDate.isBlank()
  textViewDeliveryDate.text = "some text"
  textViewEarningsValue.text = orderInfo.earnings
  textViewOnDeliveryValue.text = orderInfo.orderOnDelivery
  textViewClientPayValue.text = orderInfo.orderTotal
  textViewStatus.text = orderInfo.status.getOrderStatusLabel(context)
  textViewStatus.setTextColor(orderInfo.status.getColor(context))
}
```

**RIGHT:**

```kotlin
binding.apply {
  textViewClientName.text = orderInfo.clientName
  
  textViewDeliveryDate.isGone = orderInfo.deliveryDate.isBlank()
  textViewDeliveryDate.text = "some text"
  
  textViewEarningsValue.text = orderInfo.earnings
  textViewOnDeliveryValue.text = orderInfo.orderOnDelivery
  textViewClientPayValue.text = orderInfo.orderTotal
  
  textViewStatus.text = orderInfo.status.getOrderStatusLabel(context)
  textViewStatus.setTextColor(orderInfo.status.getColor(context))
}
```

### Indentation
###### Preferred

Indentation is using spaces - never tabs.

#### Blocks
###### Strongly recommended

Indentation for blocks uses 2 spaces (not the default 4):

**WRONG:**

```kotlin
for (i in 0..9) {
    Log.i(TAG, "index=" + i)
}
```

**RIGHT:**

```kotlin
for (i in 0..9) {
  Log.i(TAG, "index=" + i)
}
```

#### Line Wraps
###### Recommended

Indentation for line wraps should use 2 spaces (not the default 8):

**WRONG:**

```kotlin
val widget: CoolUiWidget =
        someIncrediblyLongExpression(that, reallyWouldNotFit, on, aSingle, line)
```

**RIGHT:**

```kotlin
val widget: CoolUiWidget =
    someIncrediblyLongExpression(that, reallyWouldNotFit, on, aSingle, line)
```

### Line Length (open to discussion)
###### Strongly recommended

Lines should be no longer than 140 characters long.

### Vertical Spacing
###### Preferred

There should be exactly one blank line between and within methods to aid in visual clarity and organization.

## Comments
###### Preferred

Comments are only allowed in test files methods, i.e.

```kotlin
@Test
  fun `Should add comments when you are implementing a test method`() {
    // given
    . . .
 
    // when
    . . .
 
    // then
    . . .
  }
```

Or whenever the method needs to be declared but without content:

```kotlin
private val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    onChangeListener?.invoke(getQueryString())
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {
    // no-op by default
  }
}
```

The code should be as self-documenting as possible.

## Semicolons
###### Strongly recommended

Semicolons should be avoided wherever possible in Kotlin.

## Getters & Setters
###### Strongly recommended

Unlike Java, direct access to fields in Kotlin is preferred.

If custom getters and setters are required, they should be declared [following Kotlin conventions](https://kotlinlang.org/docs/reference/properties.html) rather than as separate methods.

## Brace Style
###### Strongly recommended

Only trailing closing-braces are awarded their own line. All others appear the same line as preceding code:

**WRONG:**

```kotlin
class MyClass
{
  fun doSomething()
  {
    if (someTest)
    {
      // ...
    }
    else
    {
      // ...
    }
  }
}
```

**RIGHT:**

```kotlin
class MyClass {
  fun doSomething() {
    if (condition) { 
      . . .
    } else {
      . . .
    }
  }
}
```

Conditional statements are _always_ required to be enclosed with braces, irrespective of the number of lines required.

**WRONG:**

```kotlin
if (condition1)
  doSomething()
if (condition2) doSomethingElse()
```

**RIGHT:**

```kotlin
if (condition1) {
  doSomething()
}

if (condition2) {
  doSomethingElse()
}
```

## When Statements (open to discussion)
###### Recommended



Separate cases using commas if they should be handled the same way.

Always include the else case.

**WRONG:**

```kotlin
when (input) {
  1 -> doSomethingForCaseOneOrTwo()
  2 -> doSomethingForCaseOneOrTwo()
  3 -> doSomethingForCaseThree()
}
```

**RIGHT:**

```kotlin
when (input) {
  1, 2 -> doSomethingForCaseOneOrTwo()
  3 -> doSomethingForCaseThree()
  else -> logger.w("No case satisfied")
}
```

### Type Inference
###### Preferred

Type inference should be preferred where possible to explicitly declared types.

**WRONG:**

```kotlin
val something: MyType = MyType()
val meaningOfLife: Int = 42
```

**RIGHT:**

```kotlin
val something = MyType()
val meaningOfLife = 42
```

### Constants vs. Variables
###### Strongly recommended

Constants are defined using the `val` keyword, and variables with the `var` keyword. Always use `val` instead of `var` if the value of the variable will not change.

*Tip*: A good technique is to define everything using `val` and only change it to `var` if the compiler complains!


### Companion Objects
###### Strongly recommended

Companion objects should be declared at the _top_ of the class:

```kotlin
class CelerikAuthenticator {

  companion object {
    const val MAX_ALLOWABLE_RETRY_ATTEMPTS = 3
  }
  
  . . .
  
}  
```

### Nullable Types
###### Strongly recommended

Declare variables and function return types as nullable with `?` where a `null` value is acceptable.

Usage of the `!!` is only allowed in tests files.

When naming nullable variables and parameters, avoid naming them like `nullableString` / `maybeView` / `optValue` since their nullability is already in the type declaration.

When accessing a nullable value, use the safe call operator if the value is only accessed once or if there are many nullables in the chain:

```kotlin
editText?.setText("foo")
```

If there are several usages of this nullable value, consider the use of `?.apply` instead:

```kotlin
binding?.apply {
  viewModel = feedDetailViewModel
  lifecycleOwner = this@FeedDetailFragment
}
```

### Optional Types
###### Recommended

When naming optional variables and parameters, avoid naming them like `maybeView` / `optValue` since their optional type is already in its declaration.

## Language (open to discussion)
###### Preferred

Use `en-US` English spelling.

**WRONG:**

```kotlin
val colourName = "red"
```

**RIGHT:**

```kotlin
val colorName = "red"
```

## Trailing Commas
###### Recommended

The compiler now allows leaving a dangling comma after function, constructor, lambda parameters, and many other places where it was previously forbidden.

This is useful to make clearer the GIT diffs. Let's look at an example:

- Before:
```kotlin
class NoCommas(
  val foo: Int
)
```

- After adding `bar` property:
```kotlin
class NoCommas(
-  val foo: Int,
+  val foo: Int,
+  val bar: Int
)
```

Now, we're going to using trailing commas:

- Before:
```kotlin
class YesCommas(
  val foo: Int,
)
```

- After adding `bar` property with trailing comma:
```kotlin
class NoCommas(
   val foo: Int,
+  val bar: Int,
)
```

As we can see, using trailing commas we are taking care of our reviewers, since we are avoiding them some eye fatigue in the hour of reviewing our PR.