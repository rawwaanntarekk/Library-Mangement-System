package Interfaces;

import Models.Member;

public interface IMemberRepository {
    void addMember(Member member);
    void updateMember(Member member);
    Member getMember(String name);

}
