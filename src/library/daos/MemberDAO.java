package library.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.entities.Member;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

public class MemberDAO implements IMemberDAO {
	
	private IMemberHelper memberHelper;
	private int nextMemberID;
	private Map<Integer, IMember> memberMap;

	public MemberDAO(IMemberHelper memberHelper) {
		if(memberHelper ==null) {
			throw new IllegalArgumentException("Bad parameter: memberHelper can not be null");
		}
		
		this.memberHelper =memberHelper;
		this.nextMemberID =0;
		this.memberMap =new HashMap<Integer, IMember>();
	}

	@Override
	public IMember addMember(String firstName, String lastName, String contactPhone, String emailAddress) {
		int memberID =++this.nextMemberID;
		IMember member =this.memberHelper.makeMember(firstName, lastName, contactPhone, emailAddress, memberID);
		this.memberMap.put(memberID, member);
		
		return member;
	}

	@Override
	public IMember getMemberByID(int id) {
		if(this.memberMap.containsKey(id)) {
			return this.memberMap.get(id);
		}
		
		return null;
	}

	@Override
	public List<IMember> listMembers() {
		return new ArrayList<IMember>(this.memberMap.values());
	}

	@Override
	public List<IMember> findMembersByLastName(String lastName) {
		List<IMember> memberList =new ArrayList<IMember>();
		
		for(IMember member : this.memberMap.values()) {
			if(member.getLastName().equals(lastName)) {
				memberList.add(member);
			}
		}
		
		return memberList;
	}

	@Override
	public List<IMember> findMembersByEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMember> findMembersByNames(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
