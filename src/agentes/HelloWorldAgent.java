//package teste;
//
//import jade.core.Agent;
//
//
//public class HelloWorldAgent extends Agent { 
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package agentes;

import jade.core.Agent;

public class HelloWorldAgent extends Agent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup()  {
        System.out.println("Hello World. I'm an agent.");
    }
}