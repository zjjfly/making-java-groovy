package com.github.zjjfly.mjg.ch4

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import java.time.LocalDate

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */

//Java的POJO在Groovy中可以像POGO一样使用
//它会被添加一个基于map的构造器，但需要确保POJO中有一个无参数的构造方法
var buffy = new Person(name: 'Buffy')
//Groovy可以直接private字段，其实是调用了这个字段的getter方法
assert buffy.id == 0
assert buffy.name == 'Buffy'

var faith = new Person(name: 'Faith', id: 1)
assert faith.id == 1
assert faith.name == 'Faith'

//Groovy可以直接把map转成一个POJO类的对象，只要这个map中包含和这个类相同的字段
var willow = [name: 'Willow', id: 2] as Person
assert willow.getId() == 2
assert willow.getName() == 'Willow'

//Groovy支持操作符重载，可以方便地向集合中添加元素和查找元素
var slayers = [buffy, faith]
assert ['Buffy', 'Faith'] == slayers*.name
assert slayers.class == ArrayList
//Groovy的集合支持+操作符，它向集合中添加元素并返回一个新的集合，原集合不变
var characters = slayers + willow
assert ['Buffy', 'Faith', 'Willow'] == characters*.name

var doubles = characters.findAll { it.name =~ /([a-z])\1/ }
assert ['Buffy', 'Willow'] == doubles*.name

//每个Groovy的类包含了一个meta类，如果通过实例无法访问字段或方法，则会在这个meta类中去寻找
//这就是Groovy为JDK中的Java类添加额外的方法的实现原理
var u = 'username'
var p = 'password'
var encoded = "$u:$p".bytes.encodeBase64().toString()
println "$u:$p -> $encoded"
assert encoded == 'dXNlcm5hbWU6cGFzc3dvcmQ='
def (user, pass) = new String(encoded.decodeBase64()).split(':')
println "(user,pass) = ($user,$pass)"
assert user == u
assert pass == p
//Groovy增强了Java的时间操作
println 'Groundhog sees shadow --> 6 more weeks of Winter'
var groundHogDay = LocalDate.of(2013, 2, 2)
var firstDayOfSpring = LocalDate.of(2013, 3, 20)
//Groovy为array,string也添加了size方法，这样就和集合一致了
var days = firstDayOfSpring - groundHogDay
assert days == (firstDayOfSpring..groundHogDay).size() - 1
println """
There are ${(int) (days / 7)} weeks and ${days % 7} days between GroundHog Day
and the first day of Spring (March 20), so Spring
comes early if the groundhog sees his shadow.
"""

//Groovy可以进行AST转换，它使得实现代理模式非常简单
//在container类中，使用@Delegate注释需要被代理的实例，这些实例的方法会被AST转换放到container类中
SmartPhone sp = new SmartPhone()
assert 'dialing 555-1234' == sp.dial('555-1234')
assert 'taking picture' == sp.takePicture()
//AST转换的另一个实用的例子是@Immutable，它可以把一个一般的Groovy类转换成不可变类（即其中的字段无法被修改）
//但它也有一些限制，其中的自动需要是primitive类型或Date这样的常用类型，或者也是@Immutable注释的类
ImmutablePoint point = new ImmutablePoint(x: 1, y: 2)
try {
    point.x = 2
    assert false
} catch (ReadOnlyPropertyException e) {
    assert true
}
//@Immutable注释的类的字段如果是集合，也会保证其是不可变的
var lines = []
ImmutablePoint p1 = new ImmutablePoint(x: 0, y: 0)
ImmutablePoint p2 = new ImmutablePoint(x: 3, y: 0)
ImmutablePoint p3 = new ImmutablePoint(x: 0, y: 4)
lines << new ImmutableLine(start: p1, end: p2)
lines << new ImmutableLine(start: p2, end: p3)
lines << new ImmutableLine(start: p3, end: p1)
ImmutablePath path = new ImmutablePath(segments: lines)
println path.segments.class
//class java.util.Collections$UnmodifiableRandomAccessList
//为了支持Java使用Groovy的不可变类型，@Immutable也会为类添加一个all-argument构造器
ImmutablePoint ip = new ImmutablePoint(1, 2)

//@Singleton可以方便的实现单例模式
ip = ImmutablePointFactory.instance.newImmutablePoint(3, 4)

//Groovy解析和生成JSON格式的数据很方便
String url = 'https://api.chucknorris.io/jokes/random'
String jsonTxt = url.toURL().text
var json = new JsonSlurper().parseText(jsonTxt)
var joke = json?.value
println "joke = $joke"

var builder = new JsonBuilder()
var root = builder.people {
    person {
        firstName 'Guillaume'
        lastName 'Laforge'
        // Named arguments are valid values for objects too
        address(
                city: 'Paris',
                country: 'France',
                zip: 12345,
        )
        married true
        // a list of values
        conferences 'JavaOne', 'Gr8conf'
    }
}
println(builder.toPrettyString())
assert builder.toPrettyString() == """
{
    "people": {
        "person": {
            "firstName": "Guillaume",
            "lastName": "Laforge",
            "address": {
                "city": "Paris",
                "country": "France",
                "zip": 12345
            },
            "married": true,
            "conferences": [
                "JavaOne",
                "Gr8conf"
            ]
        }
    }
}
""".strip()

