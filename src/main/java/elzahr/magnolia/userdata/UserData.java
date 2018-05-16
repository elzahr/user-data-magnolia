package elzahr.magnolia.userdata;

import info.magnolia.module.publicuserregistration.UserProfile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserData extends UserProfile implements Serializable {

    private Map<String, DataUnit> dataUnitSet;

    public UserData() {
        dataUnitSet = new HashMap<>();
        dataUnitSet.put(UserProfile.PASSWORD, new DataUnit(getUsername()));
        dataUnitSet.put(UserProfile.FULLNAME, new DataUnit(getUsername()));
        dataUnitSet.put(UserProfile.EMAIL, new DataUnit(getUsername()));
    }

    //    public void saveData() throws RepositoryException {
//        Session session = MgnlContext.getJCRSession(WORKSPACE_NAME);
//        Node userNode = getUserNode(getUsername(), session);
//
//        for(DataUnit dataUnit: dataUnitSet.values()) {
//            Node dataNode = NodeUtil.createPath(userNode, dataUnit.getName(), NodeTypes.Content.NAME);
//            PropertyUtil.setProperty(dataNode, "name", dataUnit.getName());
//            PropertyUtil.setProperty(dataNode, "value", dataUnit.getDataValue());
//            PropertyUtil.setProperty(dataNode, "expirationDate", dataUnit.getExpiration());
//        }
//
//        session.save();
//    }

    public Map<String, DataUnit> getDataUnitSet() {
        return dataUnitSet;
    }

    public void setDataUnitSet(Map<String, DataUnit> dataUnitSet) {
        this.dataUnitSet = dataUnitSet;
    }

    @Override
    public String getPassword() {
        return dataUnitSet.get(UserProfile.PASSWORD).getDataValue();
    }

    @Override
    public String getEmail() {
        return dataUnitSet.get(UserProfile.EMAIL).getDataValue();
    }

    @Override
    public String getFullName() {
        return dataUnitSet.get(UserProfile.FULLNAME).getDataValue();
    }

    @Override
    public void setPassword(String password) {
        dataUnitSet.get(UserProfile.PASSWORD).setDataValue(password);
    }

    @Override
    public void setEmail(String email) {
        dataUnitSet.get(UserProfile.EMAIL).setDataValue(email);
    }

    @Override
    public void setFullName(String fullName) {
        dataUnitSet.get(UserProfile.FULLNAME).setDataValue(fullName);
    }
}
