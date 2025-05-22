package generic.ex4;

import generic.animal.Animal;

public class ComplexBox<T extends Animal> {
    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    public <T> T printAndReturn(T t) {
        System.out.println("animal.className = " + animal.getClass().getName());
        System.out.println("t.getClass().getName() = " + t.getClass().getName());
        // t.getName(); // 호출 불가 : 메서드는 <T> 타입 (<T extends Animal이 아님)
        return t;
    }
}
