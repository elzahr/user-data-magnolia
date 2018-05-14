package elzahr.magnolia.components;

import elzahr.magnolia.userdata.UserData;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = "user-data-magnolia:components/userData", title = "User Data")
@Controller
public class UserDataComponent {

    @Autowired
    UserData userData;

    @RequestMapping("/userData")
    public String render() {
        userData.getDataUnitSet();
        return "components/userData.ftl";
    }
}
