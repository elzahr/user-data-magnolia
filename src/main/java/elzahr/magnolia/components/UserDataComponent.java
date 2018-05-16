package elzahr.magnolia.components;

import elzahr.magnolia.userdata.UserData;
import elzahr.magnolia.userdata.UserDataProvider;
import info.magnolia.jcr.node2bean.Node2BeanException;
import info.magnolia.module.blossom.annotation.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jcr.RepositoryException;

@Template(id = "user-data-magnolia:components/userData", title = "User Data")
@Controller
public class UserDataComponent {

    @Autowired
    UserDataProvider userDataProvider;

    @RequestMapping("/userData")
    public ModelAndView render() {
        ModelAndView view = new ModelAndView("components/userData.ftl");
        try {
            UserData userData = userDataProvider.getUserData();
            view.addObject("userData", userData);
        } catch (Node2BeanException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return view;
    }
}
