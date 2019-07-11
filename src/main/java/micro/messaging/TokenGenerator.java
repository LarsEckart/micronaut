package micro.messaging;

import java.util.Random;
import javax.inject.Singleton;

@Singleton
class TokenGenerator {

    private Random random = new Random();

    String next() {
        StringBuilder strb = new StringBuilder();
        strb.append(random.nextInt(99));
        strb.append((char) (random.nextInt(26) + 'a'));
        strb.append((char) (random.nextInt(26) + 'a'));
        strb.append(random.nextInt(99));
        return strb.toString();
    }
}
