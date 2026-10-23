package com.github.zjjfly.mjg.ch6

import groovy.json.JsonSlurper

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */

String url = 'https://api.chucknorris.io/jokes/random'
String jsonTxt = url.toURL().text
var json = new JsonSlurper().parseText(jsonTxt)
var joke = json?.value
assert joke
println joke
