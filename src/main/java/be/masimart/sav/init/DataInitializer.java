package be.masimart.sav.init;

import be.masimart.sav.model.Action;
import be.masimart.sav.model.Cause;
import be.masimart.sav.model.StatusRemboursement;
import be.masimart.sav.repository.ActionRepository;
import be.masimart.sav.repository.CauseRepository;
import be.masimart.sav.repository.StatusRemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ActionRepository actionRepo;
    private final CauseRepository causeRepo;
    private final StatusRemboursementRepository statusRemboursementRepo;

    // Constructor Injection
    public DataInitializer(ActionRepository actionRepo,
                           CauseRepository causeRepo,
                           StatusRemboursementRepository statusRemboursementRepo) {
        this.actionRepo = actionRepo;
        this.causeRepo = causeRepo;
        this.statusRemboursementRepo = statusRemboursementRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert actions if they do not exist
        insertActionIfNotExist("CREATED", "Order has been created");
        insertActionIfNotExist("SHIPPED", "Order has been shipped");
        insertActionIfNotExist("DELIVERED", "Order has been delivered");
        // Add other actions as needed

        // Insert causes if they do not exist
        insertCauseIfNotExist("DAMAGED", "Product arrived damaged");
        insertCauseIfNotExist("WRONG_ITEM", "Wrong item was delivered");
        insertCauseIfNotExist("DEFECTIVE", "Product is defective");
        insertCauseIfNotExist("LATE_DELIVERY", "Delivery was too late");
        insertCauseIfNotExist("MISSING_PARTS", "Product has missing parts");

        // Insert reimbursement statuses if they do not exist
        insertStatusReimbursementIfNotExist("PENDING", "Reimbursement request is being processed");
        insertStatusReimbursementIfNotExist("APPROVED", "Reimbursement has been approved");
        insertStatusReimbursementIfNotExist("REJECTED", "Reimbursement has been rejected");
        insertStatusReimbursementIfNotExist("COMPLETED", "Reimbursement has been completed");
    }

    private void insertActionIfNotExist(String action, String description) {
        // Check if the action already exists, if not, insert it
        if (actionRepo.findByAction(action).isEmpty()) {
            actionRepo.save(new Action(action, description));
        }
    }

    private void insertCauseIfNotExist(String cause, String description) {
        // Check if the cause already exists, if not, insert it
        if (causeRepo.findByCause(cause).isEmpty()) {
            causeRepo.save(new Cause(cause, description));
        }
    }

    private void insertStatusReimbursementIfNotExist(String status, String description) {
        // Check if the status already exists, if not, insert it
        if (statusRemboursementRepo.findByStatus(status).isEmpty()) {
            statusRemboursementRepo.save(new StatusRemboursement(status, description));
        }
    }
}