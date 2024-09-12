package Interfaces;

import Models.Member;

public interface IMemberRepository {
    void addMember(Member member);
    void updateMember(Member member);
    void deleteMember(String name);
    Member getMember(String name);

}
