package org.apache.catalina.startup;

import java.io.IOException;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.handler.Handler;
import org.apache.coyote.handler.LoginHandler;
import org.apache.coyote.handler.LoginPageHandler;
import org.apache.coyote.handler.RegisterHandler;
import org.apache.coyote.handler.RegisterPageHandler;
import org.apache.coyote.handler.StaticFileHandler;
import org.apache.coyote.handler.WelcomePageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tomcat {

  private static final Logger log = LoggerFactory.getLogger(Tomcat.class);

  public void start() {
    var connector = new Connector(getContext());
    connector.start();

    try {
      // make the application wait until we press any key.
      System.in.read();
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    } finally {
      log.info("web server stop.");
      connector.stop();
    }
  }

  public List<Handler> getContext() {
    // TODO : 나중에 DI 부분에서 할 것 같다,,
    return List.of(
        new LoginHandler(),
        new StaticFileHandler(),
        new LoginPageHandler(),
        new WelcomePageHandler(),
        new RegisterHandler(),
        new RegisterPageHandler()
    );
  }
}
