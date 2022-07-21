package kr.ror.common.service.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import kr.ror.comm.vo.MemberAuthVO;

public class AuthCareUserServiceImpl extends JdbcDaoImpl {

	@Override
    public MemberAuthVO loadUserByUsername(String id) throws UsernameNotFoundException {
		List<UserDetails> list = loadUsersByUsername(id);
		
		if(list.isEmpty()) {
            logger.debug("Query returned no results for member '" + id + "'");
            UsernameNotFoundException ue = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{id}, "member Id {0} not found"));
            throw ue;
        }
        return (MemberAuthVO)list.get(0);
    }
 
    @Override
    protected List<UserDetails> loadUsersByUsername(String id) {
    	return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] {id}, new RowMapper<UserDetails>() {
            public MemberAuthVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            	MemberAuthVO vo = new MemberAuthVO();
            	vo.setMemNo(rs.getString(1));
            	vo.setMemId(rs.getString(2));
            	vo.setMemPw(rs.getString(3));
            	vo.setMemNm(rs.getString(4));
            	vo.setAuthorities(AuthorityUtils.NO_AUTHORITIES);
                return vo;
            }
        });
    }
 
    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String memNo) {
        return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {memNo}, new RowMapper<GrantedAuthority>() {
            public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = getRolePrefix() + rs.getString(1);
                return new SimpleGrantedAuthority(roleName);
            }
        });
    }
    
    protected List<String> loadUserAuthoritiesString(String memNo) {
        return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {memNo}, new RowMapper<String>() {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = getRolePrefix() + rs.getString(1);
                return roleName;
            }
        });
    }
}
