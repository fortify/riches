package com.fortify.samples.riches.oper;

import com.fortify.samples.riches.AdminSupport;
import com.fortify.samples.riches.model.Profile;
import com.fortify.samples.riches.model.ProfileService;
import java.util.List;
import org.apache.struts2.ServletActionContext;

// Referenced classes of package com.fortify.samples.riches:
//            AdminSupport

public class BrowseAccount extends AdminSupport
{

    private List<Profile> profiles;

    public BrowseAccount()
    {
    }

    @SuppressWarnings("unchecked")
    public String execute() throws Exception
    {
        javax.servlet.http.HttpServletRequest request = ServletActionContext.getRequest();
        profiles = (List<Profile>) ProfileService.getAllProfiles();
        Profile profile = new Profile();
        profile.setUsername("[All Accounts]");
        profiles.add(0, profile);
        profile = new Profile();
        profile.setUsername("Select a customer");
        profiles.add(0, profile);
        super.execute();
        return "success";
    }

    public List<Profile> getProfiles()
    {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles)
    {
        this.profiles = profiles;
    }
}
