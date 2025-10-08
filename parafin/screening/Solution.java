package screening;


import java.util.*;

/**
 *
 * @author :  bnira
 * Created :  10/8/2025
 * Problem :
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Enum for the different types of application flows
enum Flow {
    BANK_STATEMENT_UPLOAD,
    PHOTO_ID_UPLOAD,
    TAX_DOCUMENT_UPLOAD
}

// Enum for the type of loan application
enum ApplicationType {
    CASH_ADVANCE,
    TERM_LOAN
}

// Data model for a loan application
class Application {
    String applicationId;
    Double loanAmount;
    ApplicationType applicationType;

    public Application(String applicationId, Double loanAmount, ApplicationType applicationType) {
        this.applicationId = applicationId;
        this.loanAmount = loanAmount;
        this.applicationType = applicationType;
    }

    // Getters for the fields
    public Double getLoanAmount() {
        return loanAmount;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }
}

// Interface for a single rule to be processed by the engine
interface Rule {
    boolean isApplicable(Application application);
    Flow getFlow();
}

// Rule: All applications require a photo ID upload.
class PhotoUploadRule implements Rule {
    @Override
    public boolean isApplicable(Application application) {
        return true; // This rule applies to all applications
    }

    @Override
    public Flow getFlow() {
        return Flow.PHOTO_ID_UPLOAD;
    }
}

// Rule: Applications above $200,000 require a tax document upload.
class TaxDocumentUploadRule implements Rule {
    @Override
    public boolean isApplicable(Application application) {
        return application.getLoanAmount() > 200000;
    }

    @Override
    public Flow getFlow() {
        return Flow.TAX_DOCUMENT_UPLOAD;
    }
}

// Rule: Term loans require a bank statement upload.
class BankStatementUploadRule implements Rule {
    @Override
    public boolean isApplicable(Application application) {
        return application.getApplicationType() == ApplicationType.TERM_LOAN;
    }

    @Override
    public Flow getFlow() {
        return Flow.BANK_STATEMENT_UPLOAD;
    }
}

// The engine that processes an application against a list of rules
class RulesEngine {
    private List<Rule> rules;

    public RulesEngine() {
        this.rules = new ArrayList<>();
        // In a real application, rules might be loaded dynamically
        rules.add(new BankStatementUploadRule());
        rules.add(new TaxDocumentUploadRule());
        rules.add(new PhotoUploadRule());
    }

    public List<Flow> processRules(Application application) {
        List<Flow> flows = new ArrayList<>();
        for (Rule rule : rules) {
            if (rule.isApplicable(application)) {
                flows.add(rule.getFlow());
            }
        }
        return flows;
    }

    // This section reflects the discussion on dynamic rule creation
    // A placeholder for metadata that would define a new rule
    static class RuleMetaData {
        Double loanAmount;
        // Other fields like applicationType, location, etc. would be here
    }

    public void addFlow(RuleMetaData rulesMetaData) {
        Rule newRule = new Rule() {
            @Override
            public boolean isApplicable(Application application) {
                // Example of a dynamically created rule's logic
                if (rulesMetaData.loanAmount != null) {
                    return application.getLoanAmount() > rulesMetaData.loanAmount;
                }
                // ... other conditions would be checked here
                return false;
            }

            @Override
            public Flow getFlow() {
                // The specific flow for this dynamic rule would also be in the metadata
                return Flow.TAX_DOCUMENT_UPLOAD;
            }
        };
        this.rules.add(newRule);
    }
}

// Processor to handle new applications
class ApplicationProcessor {
    private Set<Application> applications = new HashSet<>();
    private RulesEngine rulesEngine = new RulesEngine();

    public List<Flow> createNewApplication(Application application) {
        boolean isNew = applications.add(application);
        if (isNew) {
            return rulesEngine.processRules(application);
        }
        // Could be caught and handled, returning empty for simplicity
        return new ArrayList<>();
    }
}

// Main class to run the solution
public class Solution {
    public static void main(String[] args) {
        ApplicationProcessor applicationProcessor = new ApplicationProcessor();

        // Example from problem: 150k, term loan application
        Application app1 = new Application("123", 150000.0, ApplicationType.TERM_LOAN);

        List<Flow> flows = applicationProcessor.createNewApplication(app1);

        System.out.println(flows);
    }
}
