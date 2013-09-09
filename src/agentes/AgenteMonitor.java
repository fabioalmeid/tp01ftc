package agentes;

import gramatica.Centralizador.Absyn.ECollect1;
import gramatica.Centralizador.Absyn.ECollect2;
import gramatica.Centralizador.Absyn.EData1;
import gramatica.Centralizador.Absyn.EData2;
import gramatica.Centralizador.Absyn.EData3;
import gramatica.Centralizador.Absyn.EData4;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import agentes.jade.Centralizador.GrammarParserCentralizador;
import agentes.jade.Centralizador.TarefaCentralizador;

public class AgenteMonitor extends Agent {

	private static final long serialVersionUID = 1L;

	private ACLMessage ACLmsg;
	private Boolean isTempRunning = false;
	private Behaviour medeTemperatura = null; 

	protected void setup() {
		
		// Regista o monitor no servico de paginas amarelas
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("monitor");
		sd.setName(getName());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				// blockingReceive();
				ACLmsg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

				if (ACLmsg != null) {
					String mensagem = ACLmsg.getContent().toString();
					try { 
						TarefaCentralizador tc = GrammarParserCentralizador.getCentralizadorMessageObject(mensagem);
						if (tc.getAcao() instanceof ECollect1) { // veja na gramatica o que significa ECollect1
							System.out.print("Monitor: Iniciando a medicacao");
							List<Object> dados = tc.getDados();
							for (Object o : dados){
								if (o instanceof EData1) {
									System.out.print(" Temperatura\n");
									if (!isTempRunning) {
										addBehaviour(new InformTempBehaviour(myAgent,ACLmsg));
										isTempRunning = true;
									} else System.out.println("Monitor " + getName() + " ja esta monitorando temperatura.");
								}
								else if (o instanceof EData2)
									System.out.print(" Hemoglobina\n");
								else if (o instanceof EData3)
									System.out.print(" bilirrubina\n");
								else if (o instanceof EData4)
									System.out.print(" Pressao Arterial\n");
							}
						}
						else if (tc.getAcao() instanceof ECollect2) {
							System.out.print("Monitor: Parando medicao");
							List<Object> dados = tc.getDados();
							for (Object o : dados){
								if (o instanceof EData1) {
									System.out.print(" Temperatura\n");
									if (isTempRunning) {
										removeBehaviour(medeTemperatura);
									} else System.out.println("\nMonitor " + getName() + " nao esta monitorando temperatura.");
								}
								else if (o instanceof EData2)
									System.out.print(" Hemoglobina\n");
								else if (o instanceof EData3)
									System.out.print(" bilirrubina\n");
								else if (o instanceof EData4)
									System.out.print(" Pressao Arterial\n");
							}
							
						}/*
						else if (tc.getAcao() instanceof EApply1) {
							System.out.print("Liberando medicacao");
							List<Medicamento> med = tc.getMedicacao();
							ImprimeMedicacao(med);
						}
						else if (tc.getAcao() instanceof EApply2){
							System.out.print("Cessando liberacao da medicacao");
							List<Medicamento> med = tc.getMedicacao();
							ImprimeMedicacao(med);
							}
						System.out.println("\n-------------------");
						*/	
					} catch (Exception e){
						System.out.println(e.getMessage());
					}
//					if (ParseGrammar.validateSentence(ACLmsg.getContent().toString())) { // GRAMMAR validation
//						System.out.println("ParseGramma  TRUE");
//
//						if (ACLmsg.getContent().toString().contains(Actions.INICIAR_LEITURA_TEMPERATURA_MONITOR)) {
//							addBehaviour(new InformTempBehaviour(myAgent,ACLmsg));
//						}
//						// OTHERS
//					} else {
//						System.out.println("FALSE");
//					}
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

		//System.out.println("msg enviada vy monitor " + msgToSend );
		replica.setContent(msgToSend);
		send(replica);

	}

	private int randomNumber(int max) {
		return (int) (Math.random() * max);
	}
	
//	private void generateTempMSG(ACLMessage ACLmsg,int previousDegree){
//		String 	msgtoSend  ;
//		int currentDegree = randomNumber(previousDegree);
//		
//		msgtoSend =  "A " + "TEMPERATURA"+ " as "+  getCurrentTime() + ": "+ Integer.toString(currentDegree) + "C    " + " |" + Integer.toString(currentDegree) ;  // DEFINIR MELHOR A ACTION(TEMPERATURA) E horario(random)
//		System.out.println("msgtoSend  >>>>> "  +  msgtoSend);
//		sendMsgtoCentralizador(ACLmsg, msgtoSend);
//		
//	}
	
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
			System.out.println("Monitor: aclMessage********************************"+ aclMessage);
		}

		@Override
		protected void onTick() {
			//generateTempMSG(aclMessage, 40);  // get CurrentDegree
//			int currentDegree = randomNumber(previousDegree);
//			
//			msgtoSend =  "A " + "TEMPERATURA"+ " as "+  getCurrentTime() + ": "+ Integer.toString(currentDegree) + "C    " + " |" + Integer.toString(currentDegree) ;  // DEFINIR MELHOR A ACTION(TEMPERATURA) E horario(random)
//			System.out.println("msgtoSend  >>>>> "  +  msgtoSend);
//			sendMsgtoCentralizador(ACLmsg, msgtoSend);
			Random rn = new Random();
			int Min = 36;
			int Max = 41;
			int temperatura = Min + (int)(Math.random() * ((Max - Min) + 1));
			String resposta = "Temperatura de " + temperatura + " C as 19 h: 59 m";
			System.out.println("Monitor: msgtoSend para Centralizador  >>>>> "  +  resposta);
			sendMsgtoCentralizador(aclMessage, resposta);
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
