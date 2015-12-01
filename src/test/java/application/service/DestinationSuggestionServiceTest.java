package application.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rellison on 12/1/15.
 */
public class DestinationSuggestionServiceTest {

    DestinationSuggestionService service;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setup(){
        service = new DestinationSuggestionService();
    }

    @Test
    public void testService(){

//        String result = service.getNextDestinationTesting();
//        log.info(result);
    }

}