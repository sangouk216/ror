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

import kr.ror.comm.vo.MemberAuthVO;
import kr.ror.comm.vo.UserAuthVO;

public class CorpMemServiceImpl extends JdbcDaoImpl {

	@Override
	public MemberAuthVO loadUserByUsername(String memId) throws UsernameNotFoundException {
		
		String memCd = memId.split(":")[0];
		String corpNo = memId.split(":")[1];
		
		List<UserDetails> userInfoList = loadUsersByUsername(memCd, corpNo);
		

		if(userInfoList.isEmpty()) {
			logger.debug("Query returned no results for member '" + memCd + "'");
            UsernameNotFoundException ue = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{memCd, corpNo}, "member Id {0} not found"));
            throw ue;
        }
		
		MemberAuthVO user = (MemberAuthVO)userInfoList.get(0);
		/*
		 * Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		 * 
		 * if(getEnableAuthorities()) {
		 * dbAuthsSet.addAll(loadUserAuthorities(member.getMemNo()));
		 * 
		 * List<String> memberAuthList = new ArrayList<String>();
		 * memberAuthList.addAll(loadUserAuthoritiesString(member.getMemNo()));
		 * 
		 * member.setAuthorityList(memberAuthList);
		 * 
		 * }
		 * 
		 * if(getEnableGroups()) {
		 * dbAuthsSet.addAll(loadGroupAuthorities(member.getMemNo())); }
		 * 
		 * List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
		 * member.setAuthorities(dbAuths);
		 * 
		 * if(dbAuths.isEmpty()) { logger.debug("member '"
		 * +memId+"' has no authorities and will be treated as 'not found'"); }
		 */
		
        return user;
	}

	@Override
	protected List<UserDetails> loadUsersByUsername(String memId) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { memId }, new RowMapper<UserDetails>() {
			public MemberAuthVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberAuthVO user = new MemberAuthVO();
				user.setMemNo(rs.getString(1));
				user.setMemId(rs.getString(2));
				user.setMemPw(rs.getString(3));
				user.setMemNm(rs.getString(4));
				user.setAuthorities(AuthorityUtils.NO_AUTHORITIES);
				return user;
			}

		});
	}
	
	
	protected List<UserDetails> loadUsersByUsername(String memCd, String corpNo) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { memCd, corpNo }, new RowMapper<UserDetails>() {
			public MemberAuthVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberAuthVO user = new MemberAuthVO();
				user.setMemNo(rs.getString(1));
				user.setMemId(rs.getString(2));
				user.setMemPw(rs.getString(3));
				user.setMemNm(rs.getString(4));
				user.setAuthorities(AuthorityUtils.NO_AUTHORITIES);
				return user;
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
		return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] { memNo },
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = getRolePrefix() + rs.getString(1);

						return roleName;
					}
				});
	}

}
