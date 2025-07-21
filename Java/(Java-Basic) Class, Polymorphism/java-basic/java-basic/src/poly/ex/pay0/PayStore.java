package poly.ex.pay0;

public abstract class PayStore {
    // 결제 수단 추가 시 변하는 부분
    public static Pay findPay(String option) {
        if(option.equals("kakao")) {
            return new KakaoPay();
        } else if(option.equals("naver")) {
            return new NaverPay();
        } else {
            return new DefaultPay();
        }
    }
}
