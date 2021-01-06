package com.example.dataManage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class LambdaTest {
	@Test
	public void t1() {
       // new Thread(() -> System.out.println("Hello world !")).start();  
        //Runnable race2 = () -> System.out.println("Hello world !");          
        
        // 直接调用 run 方法(没开新线程哦!)  
       // race2.run();
	}
	@Test
	public void sortTest() {
		String[] players = {"zhansgan", "lisi", "wangwu", "zhaoliu",  "wangmazi"};  

		// 1.1 使用匿名内部类根据 surname 排序 players  
        Arrays.sort(players, new Comparator<String>() {  
            @Override  
            public int compare(String s1, String s2) {  
                return (s1.compareTo(s2));  
            }  
        });  
        // 1.2 使用 lambda 排序,根据 surname  
        Arrays.sort(players, (String s1, String s2) ->  s1.compareTo(s2));  
    }
	@Test
	public void forEachTest() {
		  List<String> list = Arrays.asList("1", "2", "3");
		  list.sort((String s1, String s2) ->  s1.compareTo(s2));  
          list.forEach(value -> System.out.println(value));
          list.forEach(System.out::println);
	}
	@Test
	public void t2() {
		 Apple apple1 = new Apple("红富士", "Red", 280);
	        Apple apple2 = new Apple("黄元帅", "Yello", 470);
	        Apple apple3 = new Apple("红将军", "Red", 320);
	        Apple apple4 = new Apple("国光", "Green", 300);


	        List<Apple> appleList = Arrays.asList(apple1, apple2, apple3, apple4);

	        //lambda 表达式形式
	        appleList.sort((Apple a1, Apple a2) -> {
	            return new Double(a1.getWeight() - a2.getWeight()).intValue();
	        });

	        //这里是类方法引用
	        appleList.sort(Apple::compareByWeight);

	        appleList.forEach(apple -> System.out.println(apple));
	}
	@Test
	public void t3() {
		Consumer<Integer>  c = (Integer x) -> { System.out.println(x); };

		BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " : " + y);

	}
	
}
class Apple {
    private String category;
    private String color;
    private double weight;

    public Apple(String category, String color, double weight) {
        this.category = category;
        this.color = color;
        this.weight = weight;
    }
//这里和上面静态方式唯一区别就是这个参数就一个，需要实例对象调这个方法
    public int compareByWeight(Apple other) {
        double diff = 1;
        return new Double(diff).intValue();
    }
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

    //getter setter toString
}