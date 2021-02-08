package com.vroong.newbee.Java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.aspectj.weaver.ast.Test;

/*
1. Inteface Default and Static Methods
    - 인터페이스에 static, default 메서드의 사용과 구현이 가능하다.

2. Method References : 람다
    - static 메서드 호출 = 클래스이름::메서드명 (ex. User::isRealUser)
    - 인스턴스 메서드 호출 = 인스턴스객체명::메서드명 (ex. user::isLegalName)
    - 특정 타입의 오브젝트의 메서드 호출 = 타입::메서드명 (ex. String::isEmpty)
    - 생성자 = 클래스이름::new (ex. User::new)

 3. Optional<T>
    - NPE 발생상황을 핸들링 도와준다.
    - Optiona.empty() : return empty object
    - Optional.of() : returns non-null value
    - Optional.ofNullable() : return empty object if the parameter is null -> NPE가 나서 try/catch 해주어야 할때 사용하면 용이.

      * both has the same return type
      - map() : 원하는 타입이 아닌, Optional을 리턴하는 경우 Optional.of(Optiona.of(T)) 이렇게 중첩되는 형식이된다.
      - flatMap() : Optional이 중첩되는경우 말 그대로 flat시켜준다.

4. Stream
   - Collection 인터페이스에 추가가되어서, 어떤 collection이든 모두 stream 화 할 수 있다.
   1. Stream Creation
      - Arrays.stream()
      - Stream.of()
      - Stream.empty()
      - Stream.builder() --> <T> 타입을 명시하지 않으면 Stream<Object> 로 생성되기 때문에 명시하는것이 좋다.
        ex. Stream.<T>builder().add(t).add(t)....build();
      - Stream.generate() --> result stream이 무한이 될 수 있음으로 사용시 limit정해주어야 한다.
        ex. Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10); --> "element"라는 스트링 값을 10개를 가진 Stream이 생겼다.
      - Stream.iterate()
        ex. Stream<Integer> streamIterated = Stream.iterate(40, n -> n+2).limit(20); --> 요소가 하나씩 생성될때 마다 이전 값을 사용한다. 40, 42, 44, 46, ...
      - 원시타입 Stream
        -- Stream은 원시 타입 int, long, double 을 이용해서 생성할 수 있게 해준다. 각각 IntStream, LongStream, DoubleStream
           ex. InteStream intStream = IntStream.range(1, 3);  -> InteStream.range(int startInclusive, int endExclusive)
           ex. LongStream longStrema = LongStream.rangeClosed(1, 3); -> LongStream.range(int startInclusive, int endInclusive)
           ex. DoubleStream doubleStream = new Random().doubles(3);

      - String 타입 Stream
         -- InteStream streamChars = "abc".chars(); ->  CharStream 인터페이스는 존재하지 않아서, 대신 IntStream 이 사용된다.
         -- Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

      - File Stream
         -- 파일을 읽어서 한줄한줄이 String 타입의 요소가 된다 .
         Path path = Paths.get("C:\\file.txt");
         Stream<String> streamOfStrings = Files.lines(path);
         Stream<String> streamWithCharset =
         Files.lines(path, Charset.forName("UTF-8"));


   2. multi-threading
      - parallelStream() (ex. prallelStream().forEach(elementk -> doSomething(element))

   3. Stream operation
      - intermediate operations (return Stream<T>)
        : distinct()
      - terminal operations (return a result of definite type)
        : count()
        : *** 한번 사용하고 나면 stream 재사용이 불가능하다. 스트림은 스트림이 담고 있는 요소들에 대해 여러가지 기능을 제공하는 역할이지, 요소를 담는 용도가 아니기 때문이다.
             ex.
                Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
                Optional<String> anyElement = stream.findAny();
                Optional<String> firstElement = stream.findFirst(); --> IllegalStateException 발생하나 RuntimeException이라서, compile시에 알지 못함.
                                                                        Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed

                만약에 위처럼 사용하고 싶다면 stream을 리스트로 변경한뒤 그 리스트를 다시 stream으로 변경해서 사용해야 한다.



      ex. long count = list.stream().distinct().count()

      3.1 iterating
        ex. boolean isContained = list.stream().anyMatch(element -> element.contains("a"));

      3.2 filtering
        ex. Stream<String> stream = list.stream().filter(element -> element.contains("a"));
        ex. Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1); -- 제일 첫번째 요소를 빼고 새로운 스트림을 만든다. (intermediate stream)

      3.3 Mapping
        : 하나의 스트림을 람다를 통해 다른 스트림으로 컨버트 한다.
        ex.
          List<String> uris = new ArrayList<>();
          uris.add("C:\\My.txt");
          Stream<Path> stream = uris.stream().map(uri -> Paths.get(uri));
        : 위 예제는 map을 사용하였는데 만일 스트림 안에 또 스트림이 있고 그 안에 있는 스트림의 값을 꺼내야 하는 경우
          List<Detail> details = new ArrayList();
          details.add(new Detail());
          Stream<String> stream = details.stream().flatMap(detail -> detail.getParts().stream());

      3.4 Matching
         - element를 validate하여 boolean 값을 반환하는 용도
         * anyMatch()
         * allMatch()
            - 대상 스트림이 비어있으면 항상 true를 리턴
         * noneMatch()
            - 대상 스트림이 비어있으면 항상 false를 리턴

      3.5 Reduction
        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced = integers.stream().reduce(23, (a, b) -> a + b); --> 23 + 1 + 1 + 1 (sum of all elements with some initial integer

      3.6 Collecting
          - collect() 로도 reduction이 가능하다.
          - stream -> Collection, stream -> map
          - List<String> resultList = list.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());


   4. Stream Pipeline
      - Stream에 대해서 조작을 하고 싶으면 다음 3가지가 필요하다. : source, intermediate operation(s), terminal operation
      - source에 대해 intermediate operation을 체인을 해서 사용할 수 있고, terminal operation은 한번만 사용가능.
           ex. List<String> list = Arrays.asList("abc1", "abc2", "abc3");
               long size = list.stream().skip(1).map(element -> element.substring(0, 3)).sorted().count();

   5. Intermediate Operation - Lazy Loading
     : termination Operation 이 없는 경우, Intermediate Operation은 수행되지 않는다. terminate Operation 이 있는 경우, 조건은 만족하여 종료되는 케이스의 경우 iterate을 하더라도 더이상 하지 않고 멈춘다.

   6. Order of Execution
     : intermediate Operation 으로 인해 stream source 의 길이가 줄어드는 경우는 다른 operation이 각 요소에 적용되기 전에 우선해야 적용해야 불필요한 작업을 줄일 수 있다.
       ex. skip(), filter(), distinct()

   7. Stream reduction (customize 하려면)
     - reduce(identity, accumulator, combiner) : 인자를 3개까지 가질 수 있다.
        - identity : 초기값 설정 가능 혹은 stream이 비어있거나, 인자를 넣어주지 않는경우 default 값이 들어감
        - accumulator : 요소를 모으는데 사용되는 로직을 나타냄. 해당 로직으로 항상 새로운 값이 생성되고 가장 마지막값이 반환되는 형식.
        - combiner : accumlator에 의해서 나온 값을 모으는 역할. Combiner is called only in a prallel mode to reduce results of accumulators from different threads.
              즉, prallelStream() 을 사용해야 작동을 한다.
              ex1) parallelStream 을 사용하지 않아 combiner가 작동하지 않는 경우
                  int reducedParams = Stream.of(1, 2, 3)
                                          .reduce(10,
                                                  (a, b) -> a + b, (10 + 1 + 2 + 3 = 16)
                                                  (a, b) -> {
                                                       log.info("combiner was called");
                                                       return a + b;
                                                    }
                                                 );
              ex2) parallelStream 를 사용하여 combiner 작동
                  int reducedPrallel = Arrays.asList(1,2,3).parallelStream()
                        .reduce(10,
                                (a,b) -> a+b, //(10+1 = 11; 10+2 = 12; 10+3 = 13;)
                                (a,b) -> {
                                      log.info("combiner was called");
                                      return a + b; //(11 + 12 + 13 = 36)
                                  }
                                );

     - collect()
        ex. List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
        ex. String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]");
           -> joining (delimiter, prefix, suffix) : 장점은 내가 리스트의 마지막에 다달았는지 체크를 직접하지 않아도 된다는 점이다.
           --> collect 와 함께 사용할 수 있는  것들
                  Collectors.joining(delimiter, prefix, suffix)

                  --- 하기는 map 기능을 같이 제공하기 때문에, map 을 호출하지 않아도 된다.
                  Collectors.averagingInt(Product::getPrice)
                  Collectors.summingInt(Product::getPrice)
                  Collectors.summarizingInt(Product::getPrice):IntSummaryStatistics
                     -> IntSummaryStatistics를 toString 하면 이런 형식이고, 가 값은 getCount(), getSum() 이런식으로 가져올 수 있다.
                          IntSummaryStatistics{count=5, sum=86, min=13, average=17,200000, max=23}”


                  Collectors.groupBy(Product::getPrice) : Map<Integer, List<Product>> -
                  Collectors.partitioningBy(element -> element.getPrice() > 15) : Map<Boolean, List<Product>> -> groups Product by their price. false 와 true  두개로 생긴다.
                  Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
                         ex. customer collector를 만들어야 하는 경우
                             Collector<Product, ?, LinkedList<Product>> toLinkedList = Collector.of(LinkedList::new, LinkedList::add,
                                                                                            (first, second) -> {
                                                                                               first.addAll(second);
                                                                                               return first;
                                                                                            });

                             LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);

      8. Parallel Streams
       : Collectors, array의 경우 parallelStream() 사용 가능하고, 다른 경우는 parallel() 사용.
       : isParallel() 을 호출하면 parallel stream 인지 확인 가
       : 단, parallel을 사용하는 경우는 작업시간이 비슷한 것들이때 사용하는 것이 좋다.
       : parallel 로 사용하지 않고, sequential 하게 사용하고 싶으면 sequential() 호출.
            IntStream intStreamSequential = intStreamParallel.sequential();
            boolean isParallel = intStreamSequential.isParallel();




 */
public class Java8 {

  public static void main(String[] args) throws IOException {

    Java8Interface java8Interface = new Java8Interface() {
      @Override
      public int test() {
        return 10;
      }

      @Override
      public String getDefault() {
        return "overriden default";
      }
    };

    System.out.println(java8Interface.test());
    System.out.println(java8Interface.getDefault());
    System.out.println(java8Interface.test());

//    Java8Interface java8Interface1 = () -> 0;


    Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
    streamGenerated.forEach(System.out::println);


    Stream<Integer> streamIterated = Stream.iterate(40, n -> n+2).limit(20);
    streamIterated.forEach(System.out::println);

    Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");
    streamOfString.forEach(System.out::println);

    Path path = Paths.get("/Users/heejeong.min/Documents/test");
    Stream<String> streamOfStrings = Files.lines(path);
    Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));

    streamOfStrings.forEach(System.out::println);
    streamWithCharset.forEach(System.out::println);

//    Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
//    Optional<String> anyElement = stream.findAny();
//    Optional<String> firstElement = stream.findFirst();

    List<String> list = Stream.of("a", "b", "c").filter(element -> element.contains("b")).collect(Collectors.toList());
    Optional<String> anyElement = list.stream().findAny();
    Optional<String> firstElement = list.stream().findFirst();

    System.out.println(anyElement.get() + " " + firstElement.get());

    Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);
    onceModifiedStream.forEach(System.out::println);

    Stream<String> twiceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1).map(element -> element.substring(0,3));
    twiceModifiedStream.forEach(System.out::println);


    List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
        new Product(14, "orange"), new Product(13, "lemon"),
        new Product(23, "bread"), new Product(13, "sugar"));

    Map<Boolean, List<Product>> mapPartioned = productList.stream()
        .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));

    System.out.println(mapPartioned.toString());

    Plus10 plus10 = new Plus10();
    System.out.println(plus10.apply(1));

    Function<Integer, Integer> plus10Lambda = (i) -> i + 10;
    System.out.println(plus10Lambda.apply(1));

    Function<Integer, Integer> multiply = (i) -> i*2;

    System.out.println(multiply.apply(1));

    /*
    compose 는 두개의 함수를 연결할 수 있다.
    multiply의 결과를 plus10의  입력값으로 사용. andThen은 그 반대
     */
    System.out.println(plus10.compose(multiply).apply(10)); // 30
    System.out.println(plus10.andThen(multiply).apply(10)); // 40

    //consumer
    Consumer<Integer> print = (i) -> System.out.println(i);
    print.accept(3);

    //predicate
    Predicate<String> startsPancho = (s) -> s.startsWith("pancho");
    System.out.println(startsPancho.test("panchoRider"));

    //익명클래스
    //로컬클래스
    //람다
    //에서 참조하는 로컬 변수 다른점 - 쉐도잉
   //변수캡쳐시 람다는 쉐도잉이 안되고 익명, 로컬 클래스는 된다. 익명,로컬 클래스는 스콥을 독자적으로 가져가지만 람다는 람다를 감싸고 있는 클래스, 메서의 스콥이 람다의 스코빙다.

    //effective final : 사실상 final인경우 final 생략이 가능하는것.

    //메서드 reference
    Supplier<Product> productSupplier = Product::new;
    BiFunction<Integer, String, Product> productBiFunction = Product::new;

    System.out.println(productBiFunction.apply(12, "testProduct").getPrice());

    //compare 인터페이스는 함수형으로 바뀜
    String[] dogs = {"pancho", "moboo", "bommie", "gary"};
    Arrays.sort(dogs, String::compareToIgnoreCase);
    System.out.println(Arrays.toString(dogs));

    //인터페이스를 구현할때 그 안에 모든 내용을 다 구현해야하는데, default로 인터페이스안에 구현해 놓으면 선택적 상속이 가능하다.
    Foo foo = new FooImpl("heejeong");
    System.out.println(foo.printFoo());
    System.out.println(foo.printAnother());


    //iterator
    //1. forEach()
    //2. spliterator

    Spliterator<String> spliterator = Arrays.asList(dogs).spliterator(); //consumer
    Spliterator<String> spliterator11 = spliterator.trySplit();
    while(spliterator.tryAdvance(System.out::println));

    while(spliterator11.tryAdvance(System.out::println));


    //removeIf


    //comparator
    Arrays.asList(dogs).sort(String::compareToIgnoreCase);
    Arrays.asList(dogs).sort(Comparator.reverseOrder());


    //병렬스트림
    List<String> listDogs = Arrays.asList(dogs);
    listDogs.parallelStream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

    System.out.println("@@@@@@@@@@@@");
    //멀티스레스를 만드는데 드는 비용, 컨텍스트 스위치하는 비용 등이 더 비싸서 오래걸리는 경우가 있다.
    //유용한 경우는 데이터가 정말 방대한 경우에 좋다.
    listDogs.parallelStream().map((s)-> {
      System.out.println(s + " " + Thread.currentThread().getName());
      return s.toUpperCase();
    }).collect(Collectors.toList());

    System.out.println("--------");

    listDogs.stream().map((s)-> {
      System.out.println(s + " " + Thread.currentThread().getName());
      return s.toUpperCase();
    }).collect(Collectors.toList());

    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(2, "spring data jpa", true));
    springClasses.add(new OnlineClass(3, "spring mvc", false));
    springClasses.add(new OnlineClass(4, "spring core", false));
    springClasses.add(new OnlineClass(5, "rest api development", false));

    System.out.println("스프링으로 시작한는 수업");
    springClasses.stream().filter((s) -> s.getTitle().startsWith("spring")).forEach(System.out::println);

    System.out.println("close 되지 않은 수업");
    springClasses.stream().filter((s) -> s.isClosed()).forEach(System.out::println);
    

  }

  static class Product{

    private int price;
    private String productName;

    public Product() {
    }
    public Product(int price, String productName) {
      this.price = price;
      this.productName = productName;
    }

    public int getPrice() {
      return price;
    }

  }

  static String getOutput(String input) {
    return input == null ? null : "output for " + input;
  }

  static Optional<String> getOutputOpt(String input) {
    return input == null ? Optional.empty() : Optional.of("output for " + input);
  }




}
