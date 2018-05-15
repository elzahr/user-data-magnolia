package elzahr.magnolia.userdata;

import info.magnolia.context.MgnlContext;
import info.magnolia.jcr.node2bean.Node2BeanException;

import javax.jcr.RepositoryException;

public class UserDataProvider {
    public UserData getUserData() throws Node2BeanException, RepositoryException {
        return UserData.getUserData(MgnlContext.getUser().getName());
    }
}
