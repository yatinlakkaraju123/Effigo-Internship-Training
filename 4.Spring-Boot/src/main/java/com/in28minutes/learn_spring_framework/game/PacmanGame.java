package com.in28minutes.learn_spring_framework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PacmanGame implements GamingConsole{
	public void up()
	{
		System.out.println("up");
	}
	public void down()
	{
		System.out.println("down");
	}
	public void left()
	{
		System.out.println("left");
	}
	public void right()
	{
		System.out.println("right");
	}
}
