package elzahr.magnolia.userdata;

import info.magnolia.context.MgnlContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class DataUnit {

    private static final Logger log = LoggerFactory.getLogger(DataUnit.class);

    String owner;
    String name;
    String value;
    Date expiration;

    public DataUnit(String owner) {
        this.owner = owner;
    }

    public void setDataValue(String value) {
        String currentUser = MgnlContext.getUser().getName();
        if(!owner.equalsIgnoreCase(currentUser))
            log.error("User " + currentUser + " is not allowed to change this.");
        else {
            this.value = value;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);
            expiration = calendar.getTime();
        }
    }

    public String getDataValue() {
        if(new Date().after(expiration))
            return null;
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
