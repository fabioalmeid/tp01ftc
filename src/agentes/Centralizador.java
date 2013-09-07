package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Centralizador extends Agent {

	private AgentController newAgent1 = null;
	private AgentController newAgent2 = null;

	private int monitorCont, atuadorCont;
	private String monitorName, atuadorName;

	private boolean startMeasure = true;
	
	private int AgentsNumber;

	public void setup() {
		// Printout a welcome message
		System.out.println("Eita porra , sou o agente " + getAID().getName()
				+ " e estou pronto.");

		// create agent t1 on the same container of the creator agent
		AgentContainer container = (AgentContainer) getContainerController(); 

//		monitorName = "monitor" + Integer.toString(++monitorCont);
//		CreatNewAgent(container, monitorName, "teste.AgenteMonitor");
//
//		atuadorName = "atuador" + Integer.toString(++atuadorCont);
//		CreatNewAgent(container, atuadorName, "teste.AgenteAtuador");
		
		AgentsNumber = 10;
		
		for (int i = 0; i < AgentsNumber; i++) {
			// create MONITOR			
			monitorName = "monitor" + Integer.toString(++monitorCont);
			CreatNewAgent(container, monitorName, "teste.AgenteMonitor");
			
			// create ATUADOR
			atuadorName = "atuador" + Integer.toString(++atuadorCont);
			CreatNewAgent(container, atuadorName, "teste.AgenteAtuador");
		}

		// minute
		addBehaviour(new TickerBehaviour(this, 4000) { // 60s
			protected void onTick() {

				// Atualiza a lista de agente centralizadores
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription sd = new ServiceDescription();
				sd.setType("centralizador");
				template.addServices(sd);
				try {
					DFAgentDescription[] result = DFService.search(myAgent,template); // ///DUVIDA EH PRECISO DEFINIR ISTO TODA
										// HR
				} catch (FIPAException fe) {
					fe.printStackTrace();
				}

			/*	if (startMeasure) {
//					sendMsgToMonitor("TEMPERATURA", 40);
					startMeasure = false;
				} else {
					MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
					ACLMessage msg = receive(mt);
					if (msg != null) {
						
						System.out.println();
						System.out.println("**********************");
						System.out.println("A msg 	recebida foi  -->" + msg.getContent() + " _____   BY " + msg.getSender().getLocalName());
						System.out.println("**********************");
						System.out.println();
					}*/
					for (int i = 1; i <= AgentsNumber; i++) {
						sendMsgToMonitor("TEMPERATURA", 40,"monitor"+i );
//						sendMsgToAtuador();	
						
					}
					
//				}

			}
		});
		
		addBehaviour(new CyclicBehaviour(this) {

			
			@Override
			public void action() {
				MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
				ACLMessage msg = receive(mt);
				if (msg != null) {
					
					
					System.out.println("A msg 	recebida foi  -->" + msg.getContent() + " _____   BY " + msg.getSender().getLocalName());
					System.out.println("**********************");
					System.out.println();
				}
				
			}});
	}

	private void CreatNewAgent(AgentContainer container, String agentName, String agentAdrress) {
		try {
			System.out.println("agentName  ++++" + agentName);
			container.createNewAgent(agentName, agentAdrress, null).start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("ERRO");
		}

	}

	private void defineAction() {

	}

	private void sendMsgToMonitor(String action, int currentValue, String agentName) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(new AID(agentName, AID.ISLOCALNAME));
		message.setContent(action + "_" + currentValue);
		System.out.println(agentName + " faz alguma coisa ai MONITTOR!!!");
		// System.out.println("To enviando uma mensagem...");
		send(message);
	}

	private void sendMsgToAtuador() {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(new AID(atuadorName, AID.ISLOCALNAME));
		message.setContent("faz alguma coisa ai ATUADOR!!!");

		System.out.println(atuadorName + "faz alguma coisa ai ATUADOR!!!");
		// System.out.println("To enviando uma mensagem...");
		send(message);
	}
}