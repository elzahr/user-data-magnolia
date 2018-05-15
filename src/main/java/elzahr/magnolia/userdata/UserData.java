package elzahr.magnolia.userdata;

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
import java.io.Serializable;
import java.util.Set;

public class UserData implements Serializable {
    public static final String WORKSPACE_NAME = "users-public-data";
    private String userid;
    private Set<DataUnit> dataUnitSet;

    public void saveData() throws RepositoryException {
        Session session = MgnlContext.getJCRSession(WORKSPACE_NAME);
        Node userNode = getUserNode(userid, session);

        for(DataUnit dataUnit: dataUnitSet) {
            Node dataNode = NodeUtil.createPath(userNode, dataUnit.getName(), NodeTypes.Content.NAME);
            PropertyUtil.setProperty(dataNode, "name", dataUnit.getName());
            PropertyUtil.setProperty(dataNode, "value", dataUnit.getValue());
            PropertyUtil.setProperty(dataNode, "expirationDate", dataUnit.getExpiration());
        }

        session.save();
    }

    public static UserData getUserData(String userid) throws RepositoryException, Node2BeanException {
        Session session = MgnlContext.getJCRSession(WORKSPACE_NAME);
        Node userNode = getUserNode(userid, session);
        Node2BeanProcessor node2BeanProcessor = Components.getComponent(Node2BeanProcessor.class);
        UserData userData = (UserData) node2BeanProcessor.toBean(userNode);
        return userData;
    }

    private static Node getUserNode(String userid, Session session) throws RepositoryException {
        String userNodePath = "/" + userid;
        Node userNode;
        if(!session.nodeExists(userNodePath)) {
            userNode = NodeUtil.createPath(session.getRootNode(), userNodePath, NodeTypes.Content.NAME);
            PropertyUtil.setProperty(userNode, "class", UserData.class.getName());
            PropertyUtil.setProperty(userNode, "userid", userid);
            session.save();
        } else {
            userNode = session.getNode(userNodePath);
        }
        return userNode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Set<DataUnit> getDataUnitSet() {
        return dataUnitSet;
    }

    public void setDataUnitSet(Set<DataUnit> dataUnitSet) {
        this.dataUnitSet = dataUnitSet;
    }
}
