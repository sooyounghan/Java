package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ASCII 인코딩 ==");
        test("A", US_ASCII, US_ASCII);
        test("A", US_ASCII, ISO_8859_1); // ASCII 확장 (LATIN-1)
        test("A", US_ASCII, EUC_KR); // ASCII 포함
        test("A", US_ASCII, MS949); // ASCII 포함
        test("A", US_ASCII, UTF_8); // ASCII 포함
        test("A", US_ASCII, UTF_16BE); // UTF_16 디코딩 실패

        System.out.println("== 한글 인코딩 - 기본 ==");
        test("가", US_ASCII, US_ASCII); // X (인코딩 결과 : 63인데, 이를 디코딩하면 값이 ?)
        test("가", ISO_8859_1, ISO_8859_1); // X
        test("가", EUC_KR, EUC_KR); // O
        test("가", MS949, MS949); // O
        test("가", UTF_8, UTF_8); // O
        test("가", UTF_16, UTF_16); // O

        System.out.println("== 한글 인코딩 - 복잡한 문자 ==");
        test("쀍", EUC_KR, EUC_KR); // X
        test("쀍", MS949, MS949); // EUC_KR 확장이므로 가능 O
        test("쀍", UTF_8, UTF_8); // O
        test("쀍", UTF_16BE, UTF_16BE); // O

        System.out.println("== 한글 인코딩 - 디코딩 다른 경우 ==");
        test("가", EUC_KR, MS949);
        test("쀍", MS949, EUC_KR); // 인코딩은 가능하나, 디코딩은 불가
        test("가", EUC_KR, UTF_8); // X
        test("가", MS949, UTF_8); // X
        test("가", UTF_8, MS949); // X

        System.out.println("== 영문 인코딩 - 디코딩 다른 경우 ==");
        test("A", EUC_KR, UTF_8); // O
        test("A", MS949, UTF_8); // O
        test("A", UTF_8, MS949); // O
        test("A", UTF_8, UTF_16BE); // X
    }

    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset);//

        String decoded = new String(encoded, decodingCharset);

        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n", text, encodingCharset, Arrays.toString(encoded), encoded.length, decodingCharset, decoded);
    }
}
