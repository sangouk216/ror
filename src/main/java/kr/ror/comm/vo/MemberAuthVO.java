package kr.ror.comm.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberAuthVO implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String memNo;
	private String memId;
	private String memPw;
	private String memNm;
	private String corpDishUrl;

	private Set<GrantedAuthority> authorities;
	private List<String> authorityList;

    public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getCorpDishUrl() {
		return corpDishUrl;
	}
	public void setCorpDishUrl(String corpDishUrl) {
		this.corpDishUrl = corpDishUrl;
	}
	
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = new HashSet<GrantedAuthority>(authorities);
	}
	
	public List<String> getAuthorityList() {
		return authorityList;
	}
	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	

	@Override
	public String getPassword() {
		return this.memPw;
	}

	@Override
	public String getUsername() {
		return this.memId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
