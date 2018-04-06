package com.spsvn.studyspringcore;



import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

	@EventListener
	public void processEvent(DrawEvent event) {
		System.out.println(event.toString());
	}
	
	@EventListener
	public void processEventPoint(Point point) {
		System.out.println(point.getX());
	}
}
