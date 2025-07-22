-----
### System 클래스
-----
1. System 클래스는 시스템과 관련된 기본 기능들을 제공
2. SystemMain (/lang/system)
```java
package lang.system;

import java.util.Arrays;

public class SystemMain {
    public static void main(String[] args) {
        // 현재 시간 (밀리초)를 가져옴
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis = " + currentTimeMillis);
        
        // 현재 시간 (나노초)를 가져옴
        long currentTimeNano = System.nanoTime();
        System.out.println("currentTimeNano = " + currentTimeNano);
        
        // 환경 변수를 읽음
        System.out.println("getEnv = " + System.getenv());
        
        // 시스템 속성을 읽음
        System.out.println("properties = " + System.getProperties());
        System.out.println("Java Version = " + System.getProperty("java.version"));
        
        // 배열을 고속으로 복사
        char[] originArray = {'h', 'e', 'l', 'l', 'o'};
        char[] copiedArray = new char[5];
        System.arraycopy(originArray, 0, copiedArray, 0, originArray.length);
        
        // 배열 출력
        System.out.println("copiedArray = " + copiedArray);
        System.out.println("Arrays.toString(copiedArray) = " + Arrays.toString(copiedArray));

        // 프로그램 종료
        System.exit(0);
    }
}
```
   - 실행 결과
```
currentTimeMillis = 1753165741775
currentTimeNano = 19550621094700

getEnv = {USERDOMAIN_ROAMINGPROFILE=DESKTOP-7IP75SV, PROCESSOR_LEVEL=25, SESSIONNAME=Console, ALLUSERSPROFILE=C:\ProgramData, PROCESSOR_ARCHITECTURE=AMD64, EFC_8612_1592913036=1, PSModulePath=C:\Program Files\WindowsPowerShell\Modules;C:\WINDOWS\system32\WindowsPowerShell\v1.0\Modules, EFC_8612_2283032206=1, SystemDrive=C:, USERNAME=young, ProgramFiles(x86)=C:\Program Files (x86), EFC_8612_2775293581=1, FPS_BROWSER_USER_PROFILE_STRING=Default, EFC_8612_1262719628=1, PATHEXT=.COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC;.PY;.PYW, DriverData=C:\Windows\System32\Drivers\DriverData, ProgramData=C:\ProgramData, ProgramW6432=C:\Program Files, HOMEPATH=\Users\young, PROCESSOR_IDENTIFIER=AMD64 Family 25 Model 80 Stepping 0, AuthenticAMD, ProgramFiles=C:\Program Files, PUBLIC=C:\Users\Public, windir=C:\WINDOWS, =::=::\, LOCALAPPDATA=C:\Users\young\AppData\Local, ChocolateyLastPathUpdate=133909933168311902, IntelliJ IDEA=C:\Program Files\JetBrains\IntelliJ IDEA 2025.1\bin;, USERDOMAIN=DESKTOP-7IP75SV, FPS_BROWSER_APP_PROFILE_STRING=Internet Explorer, LOGONSERVER=\\DESKTOP-7IP75SV, JAVA_HOME=C:\Program Files\Java\jdk-17, OneDrive=C:\Users\young\OneDrive, APPDATA=C:\Users\young\AppData\Roaming, ChocolateyInstall=C:\ProgramData\chocolatey, EFC_8612_3789132940=1, CommonProgramFiles=C:\Program Files\Common Files, Path=C:\Python313\Scripts\;C:\Python313\;C:\Program Files\Common Files\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Git\cmd;C:\Program Files\Bandizip\;C:\Program Files\Docker\Docker\resources\bin;C:\ProgramData\chocolatey\bin;C:\Program Files\Java\jdk-17\bin;C:\Program Files\nodejs\;C:\mingw64\bin;C:\Users\young\AppData\Local\Microsoft\WindowsApps;C:\Program Files\JetBrains\IntelliJ IDEA 2025.1\bin;;C:\Users\young\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\young\AppData\Roaming\npm, OS=Windows_NT, COMPUTERNAME=DESKTOP-7IP75SV, PROCESSOR_REVISION=5000, CommonProgramW6432=C:\Program Files\Common Files, ComSpec=C:\WINDOWS\system32\cmd.exe, SystemRoot=C:\WINDOWS, TEMP=C:\Users\young\AppData\Local\Temp, HOMEDRIVE=C:, USERPROFILE=C:\Users\young, TMP=C:\Users\young\AppData\Local\Temp, CommonProgramFiles(x86)=C:\Program Files (x86)\Common Files, NUMBER_OF_PROCESSORS=12}
properties = {java.specification.version=21, sun.cpu.isalist=amd64, sun.jnu.encoding=MS949, java.class.path=C:\Users\young\OneDrive\Desktop\java-mid1\out\production\java-mid1, java.vm.vendor=Eclipse Adoptium, sun.arch.data.model=64, user.variant=, java.vendor.url=https://adoptium.net/, java.vm.specification.version=21, os.name=Windows 11, sun.java.launcher=SUN_STANDARD, user.country=KR, sun.boot.library.path=C:\Users\young\.jdks\temurin-21.0.6\bin, sun.java.command=lang.system.SystemMain, jdk.debug=release, sun.cpu.endian=little, user.home=C:\Users\young, user.language=ko, sun.stderr.encoding=UTF-8, java.specification.vendor=Oracle Corporation, java.version.date=2025-01-21, java.home=C:\Users\young\.jdks\temurin-21.0.6, file.separator=\, java.vm.compressedOopsMode=Zero based, sun.stdout.encoding=UTF-8, line.separator=
, java.vm.specification.vendor=Oracle Corporation, java.specification.name=Java Platform API Specification, user.script=, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, java.runtime.version=21.0.6+7-LTS, user.name=young, stdout.encoding=UTF-8, path.separator=;, os.version=10.0, java.runtime.name=OpenJDK Runtime Environment, file.encoding=UTF-8, java.vm.name=OpenJDK 64-Bit Server VM, java.vendor.version=Temurin-21.0.6+7, java.vendor.url.bug=https://github.com/adoptium/adoptium-support/issues, java.io.tmpdir=C:\Users\young\AppData\Local\Temp\, java.version=21.0.6, user.dir=C:\Users\young\OneDrive\Desktop\java-mid1, os.arch=amd64, java.vm.specification.name=Java Virtual Machine Specification, sun.os.patch.level=, native.encoding=MS949, java.library.path=C:\Users\young\.jdks\temurin-21.0.6\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\Python313\Scripts\;C:\Python313\;C:\Program Files\Common Files\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Git\cmd;C:\Program Files\Bandizip\;C:\Program Files\Docker\Docker\resources\bin;C:\ProgramData\chocolatey\bin;C:\Program Files\Java\jdk-17\bin;C:\Program Files\nodejs\;C:\mingw64\bin;C:\Users\young\AppData\Local\Microsoft\WindowsApps;C:\Program Files\JetBrains\IntelliJ IDEA 2025.1\bin;;C:\Users\young\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\young\AppData\Roaming\npm;., java.vm.info=mixed mode, sharing, stderr.encoding=UTF-8, java.vendor=Eclipse Adoptium, java.vm.version=21.0.6+7-LTS, sun.io.unicode.encoding=UnicodeLittle, java.class.version=65.0}

Java Version = 21.0.6

copiedArray = [C@506e1b77
Arrays.toString(copiedArray) = [h, e, l, l, o]
```

   - 표준 입력, 출력, 오류 스트림 : System.in, System.out, System.err은 각각 표준 입력, 표준 출력, 표준 오류 스트림을 나타냄
   - 시간 측정 : System.currentTimeMillis()와 System.nanoTime()은 현재 시간을 밀리초 또는 나노초 단위로 제공
   - 환경 변수 : System.getenv() 메서드를 사용하여 OS에서 설정한 환경 변수의 값을 얻을 수 있음
   - 시스템 속성: System.getProperties()를 사용해 현재 시스템 속성을 얻거나 System.getProperty(String key)로 특정 속성을 얻을 수 있음
     + 시스템 속성은 자바에서 사용하는 설정 값
   - 시스템 종료 : System.exit(int status) 메서드는 프로그램을 종료하고, OS에 프로그램 종료의 상태 코드를 전달
     + 상태 코드 0 : 정상 종료
     + 상태 코드 0이 아님 : 오류나 예외적인 종료
   - 배열 고속 복사 : System.arraycopy는 시스템 레벨에서 최적화된 메모리 복사 연산을 사용
     + 직접 반복문을 사용해서 배열을 복사할 때 보다 수 배 이상 빠른 성능을 제공
