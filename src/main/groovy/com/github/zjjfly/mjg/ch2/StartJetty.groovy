package com.github.zjjfly.mjg.ch2

@Grab(group='org.eclipse.jetty', module='jetty-server', version='10.0.25')
@Grab(group='org.eclipse.jetty', module='jetty-servlet', version='10.0.25')
import groovy.servlet.GroovyServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
def startJetty() {
    def server = new Server()
    def connector = new ServerConnector(server)
    connector.port = 8080
    server.addConnector(connector)

    def context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.contextPath = '/'
    context.resourceBase = './'
    context.addServlet(new ServletHolder(new GroovyServlet()), '*.groovy')
    server.handler = context

    server.start()
    server.join()
}

startJetty()
