package poly.car1;

/**
 * 다형성을 활용한 런타임 변경 (런타임 : 애플리케이션 실행 도중 변경 가능)
 */
public class CarMain1 {
    public static void main(String[] args) {
        Driver driver = new Driver();

        // 차량 선택
        Car k3Car = new K3Car();
        driver.setCar(k3Car);
        driver.drive();

        // 차량 변경 (K3 -> Model3)
        Car model3Car = new Model3Car();
        driver.setCar(model3Car);
        driver.drive();
    }
}
