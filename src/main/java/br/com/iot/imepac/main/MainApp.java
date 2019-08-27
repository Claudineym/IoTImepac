/**
 * 
 */
package br.com.iot.imepac.main;

import org.apache.camel.main.Main;
import org.apache.camel.main.MainListenerSupport;
import org.apache.camel.main.MainSupport;

/**
 * @author claudiney.viana
 *
 */
public class MainApp {
	
	/**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
       Main main = new Main(); 
       main.bind("foo", new MyBean());
       main.addRouteBuilder(new ThingsRouteBuilder());
       
       main.run();
    }

    public static class MyBean {
        public void callMe() {
            System.out.println("MyBean.callMe method has been called");
        }
    }
    
    public static class Events extends MainListenerSupport {
    	 
        @Override
        public void afterStart(MainSupport main) {
            System.out.println("MainExample with Camel is now started!");
        }
 
        @Override
        public void beforeStop(MainSupport main) {
            System.out.println("MainExample with Camel is now being stopped!");
        }
    }
}
