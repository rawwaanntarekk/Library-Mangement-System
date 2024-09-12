package Repositories;

import Interfaces.IMemberRepository;
import Models.Member;

import java.util.ArrayList;

public class MemberRepository implements IMemberRepository {
    ArrayList<Member> members = new ArrayList<>();
    @Override
    public void addMember(Member member) {
        members.add(member);

    }

    @Override
    public void updateMember(Member member) {

        Member member1 = getMember(member.getName());
        if (member1 != null) {
            member1.setContactInformation(member.getContactInformation());
        }

    }



    @Override
    public Member getMember(String name) {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;

    }
}
