package com.codeaches.activmq.embedded;

import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.filter.DestinationMapEntry;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.AuthorizationEntry;
import org.apache.activemq.security.AuthorizationPlugin;
import org.apache.activemq.security.DefaultAuthorizationMap;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsEmbeddedBrokerConfig {

  @Value("${activemq.broker.url}")
  String brokerUrl;

  @Value("${activemq.queue.name}")
  String queue;

  @Bean
  BrokerService broker() throws Exception {

    BrokerService broker = new BrokerService();
    broker.setPersistent(false);
    broker.setUseJmx(true);
    broker.addConnector(brokerUrl);

    broker.setPlugins(authPlugins().toArray(new BrokerPlugin[authPlugins().size()]));

    return broker;
  }

  @Bean
  List<BrokerPlugin> authPlugins() throws Exception {

    List<BrokerPlugin> plugins = new ArrayList<>();
    List<AuthenticationUser> users = new ArrayList<>();

    SimpleAuthenticationPlugin simpleAuthPlugin = new SimpleAuthenticationPlugin();

    AuthenticationUser user = new AuthenticationUser();
    user.setUsername("sa1");
    user.setPassword("manager");
    user.setGroups("producers,consumers,admins");

    users.add(user);
    simpleAuthPlugin.setUsers(users);
    plugins.add(simpleAuthPlugin);

    AuthorizationPlugin authPlugin = new AuthorizationPlugin();
    DefaultAuthorizationMap authorizationMap = new DefaultAuthorizationMap();

    AuthorizationEntry entry1 = new AuthorizationEntry();
    entry1.setAdmin("admins");
    entry1.setRead("consumers");
    entry1.setWrite("producers");
    entry1.setQueue(queue);

    AuthorizationEntry entry2 = new AuthorizationEntry();
    entry2.setAdmin("admins");
    entry2.setRead("consumers");
    entry2.setWrite("producers");
    entry2.setTopic("ActiveMQ.Advisory.>");

    @SuppressWarnings("rawtypes")
    List<DestinationMapEntry> entries = new ArrayList<>();
    entries.add(entry1);
    entries.add(entry2);
    authorizationMap.setAuthorizationEntries(entries);

    authPlugin.setMap(authorizationMap);
    plugins.add(authPlugin);

    return plugins;
  }
}
