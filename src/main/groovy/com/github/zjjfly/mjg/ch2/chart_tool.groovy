package com.github.zjjfly.mjg.ch2

import groovy.swing.SwingBuilder

import javax.swing.*
import javax.swing.WindowConstants as WC
import java.awt.BorderLayout as BL
/**
 * 一个展示Groovy各方面特性的例子
 *
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */

//由于Google Chart Tool Image API已经不再可用，改用QuickChart API
//URL的例子：https://quickchart.io/chart?c={type:%27pie%27,data:{labels:[%27Hello%27,%27World%27],datasets:[{data:[60,40]}]}}

String base = 'https://quickchart.io/chart?'

def format(Object o) {
    if (o instanceof String) {
        return "'$o'"
    }
    if (o instanceof List) {
        //closure和join
        return "[${o.collect { format(it) }.join(",")}]"
    }
    if (o instanceof Map) {
        def formattedKv = o.collect { k, v -> "$k:${format(v)}" }.join(",")
        return "{${formattedKv}}"
    }
    return o.toString()
}
//Map和List字面量
def params = [type: 'pie', data: [labels: ['Hello', 'World'], datasets: [[data: [60, 40]]]]]
//插入字符串
String qs = "c=${format(params)}"
assert qs.contains("{type:'pie',data:{labels:['Hello','World'],datasets:[{data:[60,40]}]}")
String url = "${base}${qs}"
println "url = $url"
def imageFile = new File("pie.webp")
//Groovy为现有类型添加了很多便利方法，如字符串可以调用toURL方法转换为URL类型，URL类型又添加了如text和bytes方法直接获取内容
//还可以使用重载的操作符<<写文件
imageFile << url.toURL().bytes

//使用SwingBuilder来构建Swing Widget
def sb = new SwingBuilder()
sb.edt {
    frame(title: 'Hello, World!', visible: true, pack: true,
            defaultCloseOperation: WC.EXIT_ON_CLOSE) {
        label(icon: new ImageIcon(url.toURL()),
                constraints: BL.CENTER)
    }
}
