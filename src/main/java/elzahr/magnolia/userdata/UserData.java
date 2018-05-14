package elzahr.magnolia.userdata;

import info.magnolia.context.MgnlContext;
import info.magnolia.jcr.node2bean.Node2BeanException;
import info.magnolia.jcr.node2bean.Node2BeanProcessor;
import info.magnolia.jcr.node2bean.TransformationState;
import info.magnolia.jcr.node2bean.impl.Node2BeanTransformerImpl;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.objectfactory.Components;
import info.magnolia.objectfactory.ObservedComponentFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserData implements Serializable {
    public static final String WORKSPACE_NAME = "users-public-data";
    String userid;
    Set<DataUnit> dataUnitSet;

    public UserData(String userid) {
        this.userid = userid;
        dataUnitSet = new HashSet<>();
    }

    public void saveData() throws RepositoryException {
        Session session = MgnlContext.getJCRSession(WORKSPACE_NAME);
        String userNodePath = "/" + userid;
        Node userNode = null;
        if(!session.nodeExists(userNodePath)) {
            userNode = NodeUtil.createPath(session.getRootNode(), userNodePath, NodeTypes.Content.NAME);
        } else {
            userNode = session.getNode(userNodePath);
        }
        for(DataUnit dataUnit: dataUnitSet) {
            Node dataNode = NodeUtil.createPath(userNode, dataUnit.getName(), NodeTypes.Content.NAME);
            PropertyUtil.setProperty(dataNode, "name", dataUnit.getName());
            PropertyUtil.setProperty(dataNode, "value", dataUnit.getValue());
            PropertyUtil.setProperty(dataNode, "expirationDate", dataUnit.getExpiration());
        }

        session.save();
    }

//    public static UserData getUserData(String userid) throws RepositoryException, Node2BeanException {
//        Session session = MgnlContext.getJCRSession(WORKSPACE_NAME);
//        String userNodePath = "/" + userid;
//
//        if(session.nodeExists(userNodePath)) {
//            Node userNode = session.getNode(userNodePath);
//            Node2BeanProcessor node2BeanProcessor = Components.getComponent(Node2BeanProcessor.class);
//            UserData userData = (UserData) node2BeanProcessor.toBean(userNode, UserData.class);
//            return userData;
//        }
//
//        return new UserData(userid);
//    }

    public static final class InstanceFactory extends ObservedComponentFactory<UserData> {
        public InstanceFactory() {
            super(WORKSPACE_NAME, "/" + MgnlContext.getUser().getName(), UserData.class);
        }

        @Override
        protected UserData transformNode(Node node) throws Node2BeanException, RepositoryException {
            return (UserData) Components.getComponent(Node2BeanProcessor.class).toBean(node, false, new Node2BeanTransformerImpl() {
                @Override
                public Object newBeanInstance(TransformationState state, Map properties, ComponentProvider componentProvider) {
                    return new UserData(MgnlContext.getUser().getName());
                }
            }, Components.getComponentProvider());
        }
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
