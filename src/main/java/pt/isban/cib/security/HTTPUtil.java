package pt.isban.cib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pt.isban.cib.enums.PrivilegioEnum;

import javax.servlet.http.HttpServletRequest;

@Component
public class HTTPUtil {

    @Autowired
    private HttpServletRequest request;

    public boolean hasRequestToken() {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")){
            return true;
        }

        return false;
    }

    public String getRequestToken() {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")){
            return header.substring("Bearer ".length());
        }

        return null;

    }

    public boolean hasRequestPrivilegio(PrivilegioEnum privilegio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        if(userDetails != null){
            return userDetails.hasPrivilegio(privilegio);
        }
        return false;
    }
}
