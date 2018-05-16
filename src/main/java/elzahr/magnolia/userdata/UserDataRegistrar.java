package elzahr.magnolia.userdata;

import info.magnolia.cms.security.User;
import info.magnolia.module.publicuserregistration.DefaultUserRegistrar;
import info.magnolia.module.publicuserregistration.PublicUserRegistrationConfig;
import info.magnolia.module.publicuserregistration.UserProfile;

public class UserDataRegistrar extends DefaultUserRegistrar {

    @Override
    public User registerUser(UserProfile userProfile, PublicUserRegistrationConfig config) {
        User user = super.registerUser(userProfile, config);
        System.out.println("Test UserDataRegistrar");
        return user;
    }
}
