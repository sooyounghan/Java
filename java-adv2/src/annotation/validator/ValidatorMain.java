package annotation.validator;

import util.MyLogger;

import static util.MyLogger.*;

public class ValidatorMain {
    public static void main(String[] args) {
        User user = new User("user1", 0);
        Team team = new Team("", 0);

        try {
            log("== user 검증 ==");
            validatorUser(user);
        } catch (Exception e) {
            log(e);
        }

        try {
            log("== team 검증 ==");
            validatorTeam(team);
        } catch (Exception e) {
            log(e);
        }
    }

    private static void validatorUser(User user) {
        if(user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException("이름이 비었습니다.");
        }

        if(user.getAge() < 1 || user.getAge() > 100) {
            throw new RuntimeException("나이는 1과 100사이어야 합니다.");
        }
    }

    private static void validatorTeam(Team team) {
        if(team.getName() == null || team.getName().isEmpty()) {
            throw new RuntimeException("이름이 비었습니다.");
        }

        if(team.getMemberCount() < 1 || team.getMemberCount() > 100) {
            throw new RuntimeException("회원 수는 1과 100사이어야 합니다.");
        }

    }

}
