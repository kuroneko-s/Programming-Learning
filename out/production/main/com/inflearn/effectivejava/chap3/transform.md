> [원본 링크](https://blogs.oracle.com/javamagazine/post/understanding-java-method-invocation-with-invokedynamic)

### invokedynamic를 사용한 Java 메서드 호출 이해

Java 7에서 추가된 invokedynamic 명령어는 런타임에 메서드 호출을 동적으로 해결할 수 있도록 합니다.        
[Java Magazine is pleased to republish this article from Ben Evans, published in 2017, about Java Virtual Machine internals. —Ed.]

이전 기사인 "Java 메서드 호출의 동작 원리 마스터하기"에서는 Java 8과 Java 9에서 사용되는 메서드 호출의 표준 형식에 해당하는 네 가지 메서드 호출 바이트코드를 설명했습니다. 이 네 가지는 invokevirtual, invokespecial, invokeinterface 및 invokestatic입니다.

이로써 다섯 번째 명령어인 invokedynamic가 어떻게 관련되는지에 대한 질문이 나옵니다. 간단히 말하면, Java 9를 기준으로 하여 Java 언어에서 invokedynamic을 직접 지원하지 않았습니다.

실제로 Java 7에서 런타임에 invokedynamic이 추가되었을 때, javac 컴파일러는 어떠한 상황에서도 새로운 바이트코드를 생성하지 않았습니다.

Java 8 이후로는 invokedynamic이 고급 플랫폼 기능을 제공하는 주요 구현 메커니즘으로 사용됩니다. 이 명령어의 사용 중에서 가장 명확하고 간단한 예는 람다 표현식의 구현입니다. 이 기사의 나머지 부분을 따라가려면 JVM이 메소드를 어떻게 호출하는지에 대한 기본적인 이해가 필요하거나 이 시리즈의 첫 번째 기사를 읽어야 할 것입니다.

### 람다식은 객체 참조

람다식이 어떻게 가능해지는지 살펴보기 전에, 람다식이 정확히 무엇인지에 대한 간단한 복습이 필요합니다. 자바에는 원시 타입(primitive types) (예: char 및 int)과 객체 참조(object references) 두 가지 유형의 값만 있습니다. 람다식은 분명히 원시 타입이 아니므로 객체 참조여야 합니다. 다음과 같은 람다식을 고려해 보십시오:


