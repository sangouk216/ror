package kr.ror.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentProfilesService {
	   
protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String springProfilesActive;
    
    public String getSpringProfilesActive() {
        return springProfilesActive;
    }
    
    public void setSpringProfilesActive(String springProfilesActive) {
        this.springProfilesActive = springProfilesActive;
    }

    public String getProfiles() {
        String profiles = "local";
        
        if(springProfilesActive != null && "prod".equals(springProfilesActive)) {
            profiles = "prod";
            
            String projectPath = this.getClass().getResource("/").toString();
            if(projectPath != null && projectPath.indexOf("/home/uwl/app_cvl") > -1) {
                profiles = "prod2";
            }
        }
        
        return profiles;
    }
}