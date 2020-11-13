package jsf.authentication;

import jsf.datastore.UserRepo;

import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;

public class InMemoryIdentityStore implements IdentityStore {

    @Inject
    UserRepo userRepo;
    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
        for (int i =0; i<userRepo.getUsers().size();i++){
            if (usernamePasswordCredential.getCaller().equals(userRepo.getUsers().get(i).getUsername())
                    && (usernamePasswordCredential.getPasswordAsString().equals(userRepo.getUsers().get(i).getPassword()))) {
                if (userRepo.getUsers().get(i).getAccessLevel() == "ADMIN"){
                    HashSet<String> roles = new HashSet<>();
                    roles.add("ADMIN");
                    return new CredentialValidationResult(userRepo.getUsers().get(i).getUsername(), roles);
                }
                else if(userRepo.getUsers().get(i).getAccessLevel() ==  "USER"){
                    HashSet<String> roles = new HashSet<>();
                    roles.add("USER");
                    return new CredentialValidationResult(userRepo.getUsers().get(i).getUsername(), roles);
                }

            }
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;

    }
}
