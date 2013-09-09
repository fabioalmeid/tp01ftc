package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.Actions;
import util.ParseGrammar;

public class AgenteMonitor extends Agent {

	private static final long serialVersionUID = 1L;

	private String currentAction;

	private int currentDegree, currentIndex;

	private ACLMessage ACLmsg;

	protected void setup() {

		addBehaviour(new CyclicBehaviour(this) {

			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				// blockingReceive();
				ACLmsg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

				if (ACLmsg != null) {

					if (ParseGrammar.validateSentence(ACLmsg.getContent().toString())) { // GRAMMAR validation
						System.out.println("ParseGramma  TRUE");

						if (ACLmsg.getContent().toString().contains(Actions.INICIAR_LEITURA_TEMPERATURA_MONITOR)) {
							addBehaviour(new InformTempBehaviour(myAgent,ACLmsg));
						}
						// OTHERS

					} else {
						System.out.println("FALSE");
					}

				}

			}

		});
	}

	private void sendMsgtoCentralizador(ACLMessage msg, String msgToSend) {
		ACLMessage replica = msg.createReply();
		replica.setPerformative(ACLMessage.INFORM);
//		currentNumber = defineNumber(currentNumber);
//
//		msgtoSend = "A " + action + "16:00: "+ Integer.toString(currentNumber);

		System.out.println("msg enviada vy monitor " + msgToSend );
		replica.setContent(msgToSend);
		send(replica);

	}

	private int randomNumber(int max) {
		return (int) (Math.random() * max);
	}
	
	private void generateTempMSG(ACLMessage ACLmsg,int previousDegree){
		String 	msgtoSend  ;
		int currentDegree = randomNumber(previousDegree);
		
		msgtoSend =  "A " + "TEMPERATURA"+ " as "+  getCurrentTime() + ": "+ Integer.toString(currentDegree) + "C    " + " |" + Integer.toString(currentDegree) ;  // DEFINIR MELHOR A ACTION(TEMPERATURA) E horario(random)
		System.out.println("msgtoSend  >>>>> "  +  msgtoSend);
		sendMsgtoCentralizador(ACLmsg, msgtoSend);
		
	}
	
	private String getCurrentTime(){
	
    	return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
 
	}

	private void generateDiseasesMSG(ACLMessage ACLmsg,int currentDegree){
		String 	msgtoSend  ;
		
		msgtoSend =  "A " + "Hemoglobina"+ " as "+  getCurrentTime() + ": "+ Integer.toString(randomNumber(currentDegree)) + "g/dL";  // DEFINIR MELHOR A ACTION(hemoglobina) E horario(random)
		sendMsgtoCentralizador(ACLmsg, msgtoSend);
		
	}

	
	// MONITOR informs to Centralizador a given temperature at each 2s
	class InformTempBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;

		private ACLMessage aclMessage;

		public InformTempBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("aclMessage********************************"+ aclMessage);
		}

		@Override
		protected void onTick() {

			generateTempMSG(aclMessage, 40);  // get CurrentDegree

		}
	}
	
/*	private String defineAction(ACLMessage ACLmsg, String msgReceived) {

		if (msgReceived.contains(Actions.INICIAR_LEITURA_TEMPERATURA_MONITOR)) {
			
			 generateTempMSG(ACLmsg, 40);  // DEFENI CURRENT DEGREE 

		} else if (msgReceived.contains(Actions.INICIAR_LEITURA_HEMOGRAMA_MONITOR)) {
			generateDiseasesMSG(ACLmsg, 14);  // DEFENI CURRENT DEGREE 
		}
		else if (msgReceived.contains(Actions.PARAR_LEITURA_TEMPERATURA_MONITOR)) {}  
		else if (msgReceived.contains(Actions.PARAR_LEITURA_HEMOGRAMA_MONITOR)) {}  /// OTHRES/// OTHRES

		return currentAction;

	}

	private int getIndex(String sentence) {
		return currentDegree;

	}*/

}
