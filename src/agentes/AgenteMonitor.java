package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgenteMonitor extends Agent {

	private static final long serialVersionUID = 1L;
	
	private String msgtoSend, currentAction;
	
	private int currentDegree, currentIndex;

	protected void setup() {
		/*
		 * System.out.println(getLocalName() + " set up"); addBehaviour(new
		 * MessageResponderBehaviour(this));
		 */

		addBehaviour(new CyclicBehaviour(this) {

			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
//				blockingReceive();
				ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
				if (msg != null) {
//					System.out.println("Recebi mensagem de " + msg.getSender());
//					System.out.println("Conteudo : " + msg.getContent());
					
//					if((msg.getContent().toString().contains("true"))){
//						currentDegree = 40;
//					}
						
						if((msg.getContent().toString().contains("TEMPERATURA")) )  // deifineAction
							currentAction = "TEMPERATURA";
							currentIndex = msg.getContent().toString().indexOf('_');
							currentDegree = Integer.valueOf(msg.getContent().toString().substring(
												++currentIndex, msg.getContent().toString().length()));
					
						sendMsgtoCentralizador(msg, currentAction, currentDegree);
					} 
								
					
				}
			
		});
	}
		
	private void sendMsgtoCentralizador(ACLMessage msg, String action, int currentNumber) {
		ACLMessage replica = msg.createReply();
		replica.setPerformative(ACLMessage.INFORM);
		currentNumber = defineNumber(currentNumber);
//		msgtoSend = "A " +action+ " �s 16:00: " + Integer.toString(currentNumber) + Integer.toString(currentNumber).length();
		
		msgtoSend = "A " +action+ " �s 16:00: " + Integer.toString(currentNumber);
		
		replica.setContent(msgtoSend);
		send(replica);

	}
	
	private int defineNumber(int max){
		return (int) (Math.random()* max);
	}
	
	private String defineAction(){
		return currentAction;
		
	}
	
	private int getIndex(String sentence){
		return currentDegree;
		
	}

}
