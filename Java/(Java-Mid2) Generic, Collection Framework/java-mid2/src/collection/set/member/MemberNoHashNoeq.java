package collection.set.member;

public class MemberNoHashNoeq {
    private String id;

    public MemberNoHashNoeq(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MemberNoHashNoeq{" +
                "id='" + id + '\'' +
                '}';
    }
}
