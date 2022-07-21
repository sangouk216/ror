package kr.ror.common.service.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import kr.ror.comm.vo.UserAuthVO;

public class AuthUserServiceImpl extends JdbcDaoImpl {

	@Override
    public UserAuthVO loadUserByUsername(String userId) throws UsernameNotFoundException {
		List<UserDetails> userInfoList = loadUsersByUsername(userId);
		
		if(userInfoList.isEmpty()) {
            logger.debug("Query returned no results for user '" + userId + "'");
             
            UsernameNotFoundException ue = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{userId}, "userId {0} not found"));
            throw ue;
        }
 
        UserAuthVO user = (UserAuthVO)userInfoList.get(0); // contains no GrantedAuthority[]
 
        Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
 
        if (getEnableAuthorities()) {
            dbAuthsSet.addAll(loadUserAuthorities(user.getUserCd()));
            
            List<String> userAuthList = new ArrayList<String>();
            userAuthList.addAll(loadUserAuthoritiesString(user.getUserCd()));
            
            user.setAuthorityList(userAuthList);
        }
 
        if (getEnableGroups()) {
            dbAuthsSet.addAll(loadGroupAuthorities(user.getUserCd()));
        }
 
        List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
        user.setAuthorities(dbAuths);
 
        if (dbAuths.isEmpty()) {
            logger.debug("User '" + userId + "' has no authorities and will be treated as 'not found'");
            //UsernameNotFoundException ue = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] {userId}, "User {0} has no GrantedAuthority"));
            //throw ue;
        }
        return user;
    }
 
	
    @Override
    protected List<UserDetails> loadUsersByUsername(String userId) {
    	return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] {userId}, new RowMapper<UserDetails>() {
            public UserAuthVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            	UserAuthVO user = new UserAuthVO();
            	user.setUserCd(rs.getString(1));
            	user.setUserId(rs.getString(2));
            	user.setPwd(rs.getString(3));
            	user.setUserNm(rs.getString(4));
            	user.setAuthorities(AuthorityUtils.NO_AUTHORITIES);
                return user;
            }
 
        });
    }
 
    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String userCd) {
        // TODO Auto-generated method stub
        return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {userCd}, new RowMapper<GrantedAuthority>() {
            public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = getRolePrefix() + rs.getString(1);
 
                return new SimpleGrantedAuthority(roleName);
            }
        });
    }
    
    protected List<String> loadUserAuthoritiesString(String userCd) {
        return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {userCd}, new RowMapper<String>() {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = getRolePrefix() + rs.getString(1);
 
                return roleName;
            }
        });
    }

}
