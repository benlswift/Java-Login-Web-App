package jsf.authentication;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;

public class InMemoryIdentityStore implements IdentityStore {
    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
        if (usernamePasswordCredential.getCaller().equals("admin") &&
                usernamePasswordCredential.getPasswordAsString().equals("admin")){
            HashSet<String> roles = new HashSet<>();
            roles.add("ADMIN");
            return new CredentialValidationResult("admin", roles);
        }
        else if (usernamePasswordCredential.getCaller().equals("user1") &&
                usernamePasswordCredential.getPasswordAsString().equals("password1")) {
            HashSet<String> roles = new HashSet<>();
            roles.add("USER");
            return new CredentialValidationResult("user1", roles);
        }
        else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
