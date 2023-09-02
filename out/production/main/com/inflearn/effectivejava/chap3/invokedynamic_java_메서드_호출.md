>[원본링크](https://blogs.oracle.com/javamagazine/post/understanding-java-method-invocation-with-invokedynamic)

### "invokedynamic"를 사용한 Java 메소드 호출에 대한 이해

#### Java 7에 추가된 "invokedynamic" 명령어는 런타임에서 메소드 호출을 동적으로 해결할 수 있게 합니다.

[Java Magazine는 2017년에 출판된 Ben Evans의 이 기사를 다시 게시하는 것을 기쁘게 생각합니다. — 편집]

이전 기사 "Java 메소드 호출 메커니즘 마스터하기"에서, 나는 Java 8과 Java 9에서 사용되는 메소드 호출의 표준 형태를 나타내는 네 가지 Java 메소드 호출 명령어에 대해 논의했습니다. 이 네 가지 명령어는 invokevirtual, invokespecial, invokeinterface, invokestatic이며, 이들은 Java 메소드 호출의 바이트 코드 표현입니다.



