package theblueorb.dev.com.dependencyinjectionwithdagger2.dagger;

import java.util.ArrayList;

import theblueorb.dev.com.dependencyinjectionwithdagger2.models.Member;

public class MembersDataManager {

    private String status;
    private ArrayList<Member> members = new ArrayList<>();

    public MembersDataManager() {
        populateDummyList();
    }

    private void populateDummyList() {
        members.add(new Member("abc","abc"));
        members.add(new Member("divakar", "divakar"));
        members.add(new Member("test","test" ));

    }
    public boolean isAMember(Member inputCredentials) {
        for (Member i: members) {
            if(i.equals(inputCredentials))return true;

        }
        return false;
    }

}
