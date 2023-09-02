>[원본링크](https://blogs.oracle.com/javamagazine/post/understanding-java-method-invocation-with-invokedynamic)

### "invokedynamic"를 사용한 Java 메소드 호출에 대한 이해

#### Java 7에 추가된 "invokedynamic" 명령어는 런타임에서 메소드 호출을 동적으로 해결할 수 있게 합니다.

[Java Magazine는 2017년에 출판된 Ben Evans의 이 기사를 다시 게시하는 것을 기쁘게 생각합니다. — 편집]

이전 기사 "Java 메소드 호출 메커니즘 마스터하기"에서, 나는 Java 8과 Java 9에서 사용되는 메소드 호출의 표준 형태를 나타내는 네 가지 Java 메소드 호출 명령어에 대해 논의했습니다. 이 네 가지 명령어는 invokevirtual, invokespecial, invokeinterface, invokestatic이며, 이들은 Java 메소드 호출의 바이트 코드 표현입니다.

이로써 다섯 번째 명령어인 invokedynamic가 어떻게 관련되는지에 대한 질문이 나옵니다. 간단히 말하면, Java 9를 기준으로 하여 Java 언어에서 invokedynamic을 직접 지원하지 않았습니다.

실제로 Java 7에서 런타임에 invokedynamic이 추가되었을 때, javac 컴파일러는 어떠한 상황에서도 새로운 바이트코드를 생성하지 않았습니다.

Java 8부터는 invokedynamic가 고급 플랫폼 기능을 제공하기 위한 주요 구현 메커니즘으로 사용됩니다. 이 명령어의 사용 중에서 가장 명확하고 간단한 예는 람다 표현식의 구현입니다. 이 기사의 나머지 부분을 이해하기 위해서는 JVM이 메소드를 호출하는 방법에 대한 기본적인 이해가 필요하거나 이 시리즈의 첫 번째 기사를 읽어야 할 것입니다.

#### 람다식은 객체 참조이다.

람다식이 어떻게 활성화되는지 살펴보기 전에, 람다식이 정확히 무엇인지 간단히 상기시키는 것이 중요합니다. 자바에는 두 가지 유형의 값만 존재합니다: 기본 데이터 유형(primitive types) (예: char와 int)과 객체 참조(object references). 람다식은 명백히 기본 데이터 유형이 아니므로, 그것은 객체 참조여야 합니다. 다음과 같은 람다식을 고려하십시오:

```java
public class LambdaExample {
   private static final String HELLO = "Hello";
   public static void main(String[] args) throws Exception {
      Runnable r = () -> System.out.println(HELLO);
      Thread t = new Thread(r);
      t.start();
      t.join();
   }
}
```

람다 표현식은 Runnable 유형의 변수에 할당되었으므로 람다는 Runnable과 호환되는 유형을 가진 객체에 대한 참조로 평가됩니다.

본질적으로, 이 객체의 유형은 Object의 하위 클래스(subclass) 중 하나이며 하나의 추가 메소드를 정의한 것입니다(그리고 필드는 없음). 추가 메소드는 Runnable 인터페이스에서 기대하는 run() 메소드로 이해됩니다.

Java 8 이전에는 이러한 객체가 Runnable을 구현한 구체적인 익명 클래스의 인스턴스로만 나타났습니다. 실제로 Java 8 람다의 초기 프로토 타입에서는 내부 클래스가 구현 기술로 사용되었습니다.

JVM의 장기적인 미래 로드맵은 람다의 더 정교한 표현이 가능한 미래 버전을 포함할 수 있습니다. 표현을 명시적인 내부 클래스를 사용하도록 고정하는 것은 플랫폼의 미래 버전에서 다른 표현이 사용되는 것을 방해하므로 바람직하지 않습니다. 그래서 대신 Java 8 및 Java 9에서는 내부 클래스를 하드코딩하는 것보다 더 정교한 기술을 사용합니다. 이전 람다 예제의 바이트코드는 다음과 같습니다:

```bytecode
public static void main(java.lang.String[]) throws java.lang.Exception;
   Code:
      0: invokedynamic #2, 0 // InvokeDynamic
                                             // #0:run:()Ljava/lang/Runnable;
      5: astore_1
      6: new #3 // class java/lang/Thread
      9: dup
      10: aload_1
      11: invokespecial #4 // Method java/lang/Thread."<init>":
                                        // (Ljava/lang/Runnable;)V
      14: astore_2
      15: aload_2
      16: invokevirtual #5 // Method java/lang/Thread.start:()V
      19: aload_2
      20: invokevirtual #6 // Method java/lang/Thread.join:()V
      23: return
```
오프셋 0에서의 바이트코드는 invokedynamic를 통해 어떤 메소드가 호출되고, 그 호출의 반환 값이 스택에 배치되고 있는 것을 나타냅니다. 메소드 내의 나머지 바이트코드는 메소드의 나머지 부분을 직관적으로 나타내고 있습니다.

#### "invokedynamic"의 동작 방식

이 시점에서 "invokedynamic"의 성격에 대한 몇 가지 세부 사항과 이 명령어의 동작 방식에 대해 논의하겠습니다. "invokedynamic" 명령어를 포함하는 클래스가 클래스 로더에 의해 로드될 때, 메소드 호출의 대상은 미리 알려지지 않습니다. 이 설계는 JVM 바이트코드의 모든 다른 호출 지점과 다릅니다.

예를 들어, 이전 기사에서 논의한 invokestatic 및 invokespecial 지점의 경우 컴파일 시점에 구현 메소드 (호출 대상이라고 함)가 정확히 알려져 있습니다. 반면에 invokevirtual 및 invokeinterface의 경우 호출 대상은 런타임에 결정됩니다. 그러나 호출 대상 선택은 Java 언어의 상속 규칙과 타입 시스템의 제약 조건을 따릅니다. 결과적으로 적어도 일부 호출 대상 정보는 컴파일 시점에 알려져 있습니다.

그에 반해, invokedynamic는 해당 명령어가 호출될 때 실제로 호출될 메소드에 대해 훨씬 더 유연합니다. 이 유연성을 허용하기 위해 invokedynamic 명령어는 동적 호출을 포함하는 `클래스의 상수 풀`에 있는 특수한 속성을 참조합니다. 이 속성에는 호출의 동적 성격을 지원하기 위한 추가 정보, 즉 `부트스트랩 메소드`(bootstrap methods, BSMs)가 포함되어 있습니다.

BSM(부트스트랩 메소드)는 invokedynamic의 중요한 부분이며, 모든 invokedynamic 호출 지점은 해당 BSM에 대한 상수 풀 항목을 가지고 있습니다. BSM을 특정 invokedynamic 호출 지점에 연결하기 위해 Java 7부터 클래스 파일 형식에도 InvokeDynamic라고 불리는 새로운 항목 유형이 추가되었습니다.

invokedynamic 명령어의 호출 지점은 클래스 로딩 시간에 unlaced(결손)되었다고 말합니다. BSM은 실제로 호출할 메소드를 결정하기 위해 호출되며, 그 결과로 얻은 CallSite 객체는 호출 지점에 laced(정의)됩니다.

가장 간단한 경우인 ConstantCallSite의 경우, 조회(lookup)가 한 번 이루어진 후에는 다시 반복될 필요가 없습니다. 대신, 호출 지점의 대상은 추가 작업 없이 미래의 모든 호출에서 직접 호출됩니다. 이것은 호출 지점이 이제 안정적이며 따라서 JIT(Just-In-Time) 컴파일러와 같은 다른 JVM 서브시스템과 호환성이 있음을 의미합니다.

이 메커니즘이 효율적으로 작동하려면 JDK에 호출 지점, BSM(부트스트랩 메소드), 및 구현의 다른 부분을 나타낼 적절한 유형이 포함되어야 합니다. Java의 원래 코어 리플렉션(Reflection) 유형은 메소드와 타입을 나타낼 수 있습니다. 그러나 이 API는 Java 플랫폼의 초기 시절에 거슬러 올라가며, 이상적이지 않은 선택으로 만드는 여러 가지 측면을 가지고 있습니다.

예를 들어, 리플렉션은 컬렉션과 제네릭을 사용하기 전에 나왔습니다. 결과적으로 리플렉션 API에서는 메소드 시그니처를 Class[]로 나타냅니다. 이것은 번거로울 뿐만 아니라 오류가 발생하기 쉽고, Java의 배열 구문이 장황하다는 문제점이 있습니다. 이는 기본 타입을 수동으로 박싱(boxing)하고 언박싱(unboxing)하고 void 메소드의 가능성을 처리하는 필요로 인해 더 복잡해집니다.

#### 메소드 핸들로 구조적인 접근

프로그래머가 이러한 문제를 직접 다루도록 강요하는 대신, Java 7은 필요한 추상화를 나타내기 위해 `MethodHandles`라는 새로운 API를 소개했습니다. 이 API의 핵심은 java.lang.invoke 패키지 및 특히 MethodHandle 클래스입니다. 이 유형의 인스턴스는 메소드를 호출할 수 있도록 제공되며 직접 실행 가능합니다. 이들은 매개변수와 반환 유형에 따라 동적으로 형식이 지정되며, 동적인 사용 방식을 고려하여 가능한 한 많은 형식 안전성을 제공합니다. 이 API는 invokedynamic을 위해 필요하지만 단독으로 사용할 수도 있으며 이 경우 reflection에 대한 현대적이고 안전한 대안으로 간주될 수 있습니다.

메소드 핸들을 얻으려면 메소드를 조회 컨텍스트를 통해 조회해야 합니다. 일반적으로 컨텍스트를 얻는 방법은 정적 헬퍼 메소드인 MethodHandles.lookup()를 호출하는 것입니다. 이 메소드는 현재 실행 중인 메소드를 기반으로 조회 컨텍스트를 반환합니다. 이 컨텍스트에서는 findVirtual() 또는 findConstructor()와 같은 find*() 메소드 중 하나를 호출하여 메소드 핸들을 얻을 수 있습니다.

메소드 핸들과 리플렉션 간의 중요한 차이점 중 하나는 조회 컨텍스트가 조회 객체가 생성된 스코프에서 접근 가능한 메소드만 반환한다는 것입니다. 이를 우회하는 방법이 없으며, 리플렉션에 존재하는 setAccessible() 백도어와 같은 기능이 없습니다. 이것은 메소드 핸들을 보안 관리자와 함께 사용하는 경우를 포함하여 모든 상황에서 안전하게 사용할 수 있다는 의미입니다.

그러나 주의가 필요하며, 액세스 제어 검사가 메소드 조회 시점으로 이동되었습니다. 이것은 조회 컨텍스트가 조회에는 보이지만 메소드 핸들이 호출될 때 반드시 보이지 않을 수 있는 개인 메소드에 대한 참조를 제공할 수 있음을 의미합니다.

메소드 시그니처를 나타내는 문제를 해결하기 위해 MethodHandles API에는 MethodType 클래스도 포함되어 있습니다. 이 클래스는 매우 유용한 속성을 가진 간단한 불변 타입이며 다음과 같은 역할을 합니다:

- 메소드의 타입 시그니처를 나타냅니다.
- 반환 유형 다음에 인수 유형으로 구성됩니다.
- "리시버 타입"이나 메소드의 이름을 포함하지 않습니다.
- 핵심 리플렉션의 Class[] 문제를 해결하기 위해 설계되었습니다.

또한 이 클래스의 인스턴스는 불변합니다.

이 API를 사용하면 `메소드의 시그니처`를 MethodType의 인스턴스로 나타낼 수 있으며, 각 가능한 시그니처를 모델링하기 위한 새로운 유형을 생성할 필요가 없습니다. 새 인스턴스는 간단한 팩토리 메소드를 통해 생성됩니다. 예를 들면:
```java
// toString()
MethodType mtToString = MethodType.methodType(String.class);

// A setter method
MethodType mtSetter = MethodType.methodType(void.class, Object.class);

// compare() from Comparator<String>
MethodType mtStringComparator = MethodType.methodType(int.class, String.class, String.class);
```

한 번 시그니처 객체를 생성하면 (메소드 이름과 함께) 메소드 핸들을 조회하기 위해 사용할 수 있습니다. 다음 예제에서는 toString() 메소드에 대한 메소드 핸들을 얻는 방법을 보여줍니다:
```java
public MethodHandle getToStringHandle() {
      MethodHandle mh = null;
      MethodType mt = MethodType.methodType(String.class);
      MethodHandles.Lookup lk = MethodHandles.lookup();
      try {
             mh = lk.findVirtual(getClass(), "toString", mt);
      } catch (NoSuchMethodException | IllegalAccessException mhx) {
           throw new AssertionError().initCause(mhx);
      }
      return mh;
}
```
그런 다음 핸들은 리플렉티브 호출과 유사한 방식으로 호출할 수 있습니다. 인스턴스 메소드의 경우 수신자 객체를 제공해야 하며, 호출 코드는 `거친 예외(coarse-grained exception)`의 가능성을 다루어야 합니다.
```java
MethodHandle mh = getToStringMH();
try {
      mh.invoke(this, null);
} catch (Throwable e) {
      e.printStackTrace();
}
```
이제 BSM(부트스트랩 메소드)의 개념은 명확할 것입니다. 프로그램 제어가 처음으로 invokedynamic 호출 지점에 도달하면 관련된 BSM이 호출됩니다. BSM은 호출 지점에 실제로 바인딩될 메소드 핸들을 포함하는 호출 지점 객체를 반환합니다. 정적 형식과 함께 이 메커니즘이 올바르게 작동하려면 BSM은 올바른 메소드 시그니처의 메소드 핸들을 반환해야 합니다.

이전에 제시한 람다 표현식 예제로 돌아가보면, invokedynamic 명령어를 람다 표현식의 플랫폼 팩토리 메소드 호출로 생각할 수 있습니다. 실제로 람다의 본문은 람다가 정의된 클래스의 개인 정적 메소드로 변환되었습니다.
```bytecode
private static void lambda$main$0();
   Code:
      0: getstatic #7           // Field
                                       // java/lang/System.out:Ljava/io/PrintStream;
      3: ldc #9                   // String Hello
      5: invokevirtual #10 // Method
                                       // java/io/PrintStream.println:
                                       // (Ljava/lang/String;)V
      8: return
```
람다 팩토리는 Runnable을 구현한 어떤 유형의 인스턴스를 반환하며, 그 유형의 run() 메소드는 람다가 실행될 때이 개인 메소드로 다시 호출됩니다.

constant pool 내부를 확인하려면 javap -v를 사용하면 다음과 같은 항목이 표시됩니다:

```bytecode
#2 = InvokeDynamic #0:#40 //
#0:run:()Ljava/lang/Runnable;
```

클래스 파일의 BSM(부트스트랩 메소드) 섹션을 살펴보면 호출되는 팩토리를 확인할 수 있습니다.

```bytecode
BootstrapMethods:
0: #37 REF_invokeStatic
java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodH
andles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/i
nvoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodT
ype;)Ljava/lang/invoke/CallSite;
Method arguments:
#38 ()V
#39 REF_invokeStatic optjava/LambdaExample.lambda$main$0:()V
#38 ()V
```

이 출력은 java.lang.invoke 내 LambdaMetafactory 구현 클래스에 있는 metafactory()라는 정적 팩토리 메소드를 참조합니다. 이것은 람다가 생성될 경우 런타임에 링크 바이트코드를 생성할 BSM입니다. metafactory 코드는 정적 형식 안전성을 보장하기 위해 조회 객체와 메소드 유형을 가져오며, 람다 메소드 본문을 포함한 개인 정적 메소드를 가리키는 메소드 핸들도 가져옵니다. 람다 본문이 호출되면 람다 본문을 연결할 호출 지점을 반환합니다.

```java
public static CallSite metafactory(
            MethodHandles.Lookup caller,
            String invokedName,
            MethodType invokedType,
            MethodType samMethodType,
            MethodHandle implMethod,
            MethodType instantiatedMethodType)
                        throws LambdaConversionException {
            AbstractValidatingLambdaMetafactory mf;
            mf = new InnerClassLambdaMetafactory(
                        caller, invokedType,
                        invokedName, samMethodType,
                        implMethod, instantiatedMethodType,
                        false, EMPTY_CLASS_ARRAY, EMPTY_MT_ARRAY);
            mf.validateMetafactoryArgs();
            return mf.buildCallSite();
}
```

현재 구현은 각 람다마다 내부 클래스를 생성하지만 이러한 클래스는 동적으로 생성되며 디스크에 기록되지 않습니다. 이것은 Java의 미래 릴리스에서 구현 메커니즘이 변경될 수 있으며 기존의 람다도 새로운 메커니즘을 활용할 수 있음을 의미합니다.

Java 8 및 Java 9에서 InnerClassLambdaMetafactory 클래스를 기반으로 한 구현은 jdk.internal.org.objectweb.asm 패키지에 포함된 ASM 바이트코드 조작 라이브러리의 약간 수정된 버전을 사용합니다.

이 구현은 람다의 구현 유형을 나타내는 동적 클래스를 생성하면서 동시에 구현을 미래 지향적으로 하고 JIT(JIT 컴파일러) 친화적으로 유지합니다.

이는 한 번 조회되고 그 이후 변경될 수 없는 호출 지점을 활용하는 가장 간단한 경우를 사용합니다. 이러한 경우는 이전에 논의한 ConstantCallSite의 인스턴스로 나타내집니다. 더 복잡한 경우도 가능하며, 호출 지점이 변경될 수 있거나 volatile 변수와 유사한 의미론을 가질 수 있습니다. 이러한 경우를 처리하기는 어렵고 빠르게 복잡해질 수 있지만, 이러한 경우는 플랫폼에서 사용 가능한 가장 많은 동적 유연성을 제공합니다.

람다 표현식의 이전 예제는 invokedynamic 명령어가 정적 형식 시스템의 중요한 부분을 완화하고 유연한 런타임 디스패치를 가능하게 하는 것을 보여줍니다.

#### 결론

invokedynamic는 대부분의 개발자가 자주 접하지 않을 수도 있지만, Java 생태계는 그 추가를 통해 크게 진화했습니다. 미래의 Java 버전에서는 JVM 기술에서 더 많은 발전을 소개할 수 있으며, 이러한 기술 중 많은 것들은 invokedynamic의 등장과 그가 나타내는 메소드 실행의 재상상 없이는 불가능했을 것입니다.