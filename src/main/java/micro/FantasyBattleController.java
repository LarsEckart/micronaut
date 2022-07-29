package micro;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller
class FantasyBattleController {

    private static final Logger log = LoggerFactory.getLogger(FantasyBattleController.class);

    @Inject
    Random rnd;

    @Get("/inventory")
    public HttpResponse<Inventory> get() {
        log.info("Received request to /");

        Item anyWeapon = new Item("any", rnd.nextInt(100), 17.0f);
        Item anyOffHand = new Item("any", 0, 0.5f);
        Item anyHelm = new Item("any", 0, 0.5f);
        Item anyChest = new Item("any", 0, 0.5f);
        Item anyFeet = new Item("any", 0, 0.5f);
        Inventory inventory = new Inventory(new Equipment(anyWeapon, anyOffHand, anyHelm, anyChest, anyFeet));
        return HttpResponse.ok(inventory);
    }

    static class Inventory{
        public Equipment equipment;

        public Inventory(Equipment equipment) {
            this.equipment = equipment;
        }
    }

    static class Equipment {

        public Item rightHand;
        public Item leftHand;
        public Item head;
        public Item chest;
        public Item feet;

        public Equipment(Item rightHand, Item leftHand, Item head, Item chest, Item feet) {
            this.rightHand = rightHand;
            this.leftHand = leftHand;
            this.head = head;
            this.chest = chest;
            this.feet = feet;
        }
    }

    static class Item {

        public String name;
        public int baseDamage;
        public float damageModifier;

        public Item(String name, int baseDamage, float damageModifier) {
            this.name = name;
            this.baseDamage = baseDamage;
            this.damageModifier = damageModifier;
        }
    }
}
