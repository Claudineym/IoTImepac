/**
 * 
 */
package br.com.iot.imepac.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;

import twitter4j.Status;

/**
 * @author claudiney.viana
 *
 */
public class ThingsRouteBuilder extends RouteBuilder{
	/**
     * Let's configure the Camel routing rules using Java code...
	 * @throws UnsupportedEncodingException 
     */
	@Override
    public void configure() throws UnsupportedEncodingException {
        initTwitter();
        
       /* from("timer:foo?delay=2000")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Invoked timer at " + new Date());
            }
        })
        .bean("foo");*/

        // receive commands (mentions of @ThingsTwitter)
        // delay in 60 seconds
       /* from("twitter://timeline/mentions?type=polling&delay=1")
                .process(new ThingsProcessor());*/
       /* from("twitter://streaming/filter?type=event&keywords=#IOTIMEPAC")
        .process( e -> processarTweet((Message) e.getIn().getBody(Status.class)));*/
        
        /*fromF("twitter://search?type=polling&delay=%s&keywords=%s", "100", "#IOTIMEPAC")
        .to("log:tweet")
        // and push tweets to all web socket subscribers on camel-tweet
        .to("websocket:camel-tweet?sendToAll=true");*/
        
        String twitter = "twitter://streaming/filter?type=event&keywords="
				+ URLEncoder.encode("Faustao", "utf8");
      
		//from(twitter).filter(body().isInstanceOf(Status.class));
       /* from("twitter:tweets?type=SEARCH&search=#justjava").transform(
				body().convertToString()).to("bean:tap");*/
        
        fromF("twitter://search?type=event&keywords=%s", "#IOTIMEPAC")
        .process( e -> processarTweet((Message) e.getIn().getBody(Status.class)))
        .to("log:tweet");
    }
    
    public void processarTweet(Message in){
    	Status status = in.getBody(Status.class);
    	String text = status.getText();
    	System.out.println("==> "+text);
    }

    private void initTwitter() {
    	    	
        TwitterComponent tc = new TwitterComponent();
        getContext().addComponent("twitter", tc);
        

        Properties p = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/twitter.properties")) {
            p.load(is);
        } catch (IOException i) {
        }

        tc.setAccessToken(p.getProperty("accessToken"));
        tc.setAccessTokenSecret(p.getProperty("accessTokenSecret"));
        tc.setConsumerKey(p.getProperty("consumerKey"));
        tc.setConsumerSecret(p.getProperty("consumerSecret"));
        /*tc.setHttpProxyHost("httpProxyHost");
        tc.setHttpProxyPort(8003);
        tc.setHttpProxyUser("httpProxyUser");
        tc.setHttpProxyPassword("httpProxyPassword");*/
    }
}
