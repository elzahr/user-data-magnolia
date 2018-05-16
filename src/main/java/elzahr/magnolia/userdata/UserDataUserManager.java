package elzahr.magnolia.userdata;

import info.magnolia.cms.security.HierarchicalUserManager;

public class UserDataUserManager extends HierarchicalUserManager {
    public static final String WORKSPACE_NAME = "users-public-data";
    @Override
    protected String getRepositoryName() {
        return WORKSPACE_NAME;
    }
}
