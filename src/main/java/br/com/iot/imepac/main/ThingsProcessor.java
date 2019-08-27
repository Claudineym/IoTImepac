/**
 * 
 */
package br.com.iot.imepac.main;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import twitter4j.Status;

/**
 * @author claudiney.viana
 *
 */
public class ThingsProcessor implements Processor  {
	@Override
    public void process(Exchange exchng) throws Exception {
        Status tweet = exchng.getIn().getBody(Status.class);
        
        String text = tweet.getText();
        System.out.println(text);
        // process text from tweet
        
        
    }
}
