package elzahr.magnolia.userdata;

import info.magnolia.cms.security.User;
import info.magnolia.context.MgnlContext;
import info.magnolia.jcr.node2bean.Node2BeanException;
import info.magnolia.jcr.node2bean.Node2BeanProcessor;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.objectfactory.Components;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

public class UserDataProvider {
    public UserData getUserData() throws Node2BeanException, RepositoryException {
        User user = MgnlContext.getUser();
        Node userNode = getUserNode(user);
        Node2BeanProcessor node2BeanProcessor = Components.getComponent(Node2BeanProcessor.class);
        UserData userData = (UserData) node2BeanProcessor.toBean(userNode);
        return userData;
    }

    private Node getUserNode(User user) throws RepositoryException {
        Session session = MgnlContext.getJCRSession(UserDataUserManager.WORKSPACE_NAME);
        Node userNode = null;
        String userNodePath = "/public-protected/" + user.getName();
        userNode = NodeUtil.getNodeByIdentifier(UserDataUserManager.WORKSPACE_NAME, user.getIdentifier());
        if(userNode == null && session.nodeExists(userNodePath)) {
            userNode = session.getNode(userNodePath);
        }
        return userNode;
    }

}
