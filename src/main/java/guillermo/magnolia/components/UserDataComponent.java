package guillermo.magnolia.components;

import guillermo.magnolia.userdata.UserData;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Template(id = "challenge-module:components/userData", title = "User Data")
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
