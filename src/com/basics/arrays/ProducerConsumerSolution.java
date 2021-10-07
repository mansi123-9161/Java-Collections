package com.basics.arrays;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerSolution {
	public static void main(String[] args) {
		BlockingQueue<Integer> sharedq = new LinkedBlockingQueue<Integer>();
		
		Producer pr = new Producer(sharedq);
		Consumer co = new Consumer(sharedq);
		pr.start();
		co.start();
	}

}

class Producer extends Thread{
	BlockingQueue<Integer> sharedpr;

	public Producer(BlockingQueue<Integer> sharedpr) {
		super();
		this.sharedpr = sharedpr;
	}
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				System.out.println(getName() +" "+ i + " "+ "produeced");
				sharedpr.put(i);
				/* Thread.sleep(1000); */
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class Consumer extends Thread{
	BlockingQueue<Integer> sharedc;

	public Consumer(BlockingQueue<Integer> sharedc) {
		super();
		this.sharedc = sharedc;
	}
	
	public void run() {
		while(true) {
			try {
				Integer num = sharedc.take();
				Thread.sleep(2000);
				System.out.println(getName() +" "+ num + " "+ "consumed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
