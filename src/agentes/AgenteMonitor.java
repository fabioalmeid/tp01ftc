package agentes;

import util.Actions;
import util.ParseGrammar;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgenteMonitor extends Agent {

	private static final long serialVersionUID = 1L;

	private String currentAction;

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
				// blockingReceive();
				ACLMessage ACLmsg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
				if (ACLmsg != null) {
					// System.out.println("Recebi mensagem de " +
					// msg.getSender());
					// System.out.println("Conteudo : " + msg.getContent());

					// if((msg.getContent().toString().contains("true"))){
					// currentDegree = 40;
					// }
					/*
					 * if((msg.getContent().toString().contains("TEMPERATURA"))
					 * ) // defineAction currentAction = "TEMPERATURA";
					 * currentIndex = msg.getContent().toString().indexOf('_');
					 * currentDegree =
					 * Integer.valueOf(msg.getContent().toString().substring(
					 * ++currentIndex, msg.getContent().toString().length()));
					 */

					if (ParseGrammar.validateSentence(ACLmsg.getContent().toString())) {
						System.out.println("ParseGramma  TRUE" );
						defineAction(ACLmsg,ACLmsg.getContent().toString());
//						sendMsgtoCentralizador(msg, currentAction, currentDegree);
					} else {
						System.out.println("FALSE");
					}

/*					System.out.println(ParseGrammar.validateSentence(msg.getContent().toString()));*/
//					System.out.println(msg.getContent().toString());
//					System.out.println(ParseGrammar.validateSentence(msg.getContent().toString()));
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

		replica.setContent(msgToSend);
		send(replica);

	}

	private int randomNumber(int max) {
		return (int) (Math.random() * max);
	}
	
	private void generateTempMSG(ACLMessage ACLmsg,int currentDegree){
		String 	msgtoSend  ;
		
		msgtoSend =  "A " + "TEMPERATURA"+ " as 16:00: "+ Integer.toString(randomNumber(currentDegree)) + "C";  // DEFINIR MELHOR A ACTION(TEMPERATURA) E horario(random)
		sendMsgtoCentralizador(ACLmsg, msgtoSend);
		
	}
	
	private void generateDiseasesMSG(ACLMessage ACLmsg,int currentDegree){
		String 	msgtoSend  ;
		
		msgtoSend =  "A " + "Hemoglobina"+ " as 16:00: "+ Integer.toString(randomNumber(currentDegree)) + "g/dL";  // DEFINIR MELHOR A ACTION(hemoglobina) E horario(random)
		sendMsgtoCentralizador(ACLmsg, msgtoSend);
		
	}

	private String defineAction(ACLMessage ACLmsg, String msgReceived) {

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

	}

}
