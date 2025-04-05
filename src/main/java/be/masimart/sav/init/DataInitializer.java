package be.masimart.sav.init;

import be.masimart.sav.model.Action;
import be.masimart.sav.repository.ActionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ActionRepository actionRepo;

    // Constructor Injection
    public DataInitializer(ActionRepository actionRepo) {
        this.actionRepo = actionRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert actions if they do not exist
        insertActionIfNotExist("CREATED", "Order has been created");
        insertActionIfNotExist("SHIPPED", "Order has been shipped");
        insertActionIfNotExist("DELIVERED", "Order has been delivered");
        // Add other actions as needed
    }

    private void insertActionIfNotExist(String action, String description) {
        // Check if the action already exists, if not, insert it
        if (actionRepo.findByAction(action).isEmpty()) {
            actionRepo.save(new Action(action, description));
        }
    }
}
