package com.evatlsong.archetype;

public class HelloWorld {

	public String sayHello()
	{
		return "Hello World\n";
	}
	
	public static void main(String[] args)
	{
		System.out.print( new HelloWorld().sayHello() );
	}
}
