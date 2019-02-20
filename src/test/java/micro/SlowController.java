package micro;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("slow")
public class SlowController {

    @Get
    public String index() throws InterruptedException {
        Thread.sleep(10000);
        return "ok";
    }

}
