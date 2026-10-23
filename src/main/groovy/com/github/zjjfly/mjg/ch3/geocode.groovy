package com.github.zjjfly.mjg.ch3


import groovy.xml.XmlSlurper

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
def base = 'https://nominatim.openstreetmap.org/search?'

String address = [street, city, state].collect {
    URLEncoder.encode(it, 'UTF-8')
}.join(',')

def url = base + [format: "xml", q: address].collect { k, v -> "$k=$v" }.join('&')
//Groovy处理XML相当方便，有XmlSlurper和XmlParser两个类可以用于解析XML，其中XmlSlurper比较高效，所以这里使用它
def response = new XmlSlurper().parse(url)
var place = response.place[0]
var attributes = place.attributes()
//需要把要传递出去的参数定义为全局变量，否则Java代码无法访问
lat = attributes.lat
lng = attributes.lon
