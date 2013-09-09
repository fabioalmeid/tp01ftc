package agentes.jade.Centralizador;

import gramatica.Monitor.Absyn.EDados;
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
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.List;

import util.GeradorAleatorioMsg;
import agentes.jade.Monitor.Afericao;
import agentes.jade.Monitor.GrammarParserMonitor;
import agentes.jade.Monitor.TarefaMonitor;

public class Centralizador extends Agent {
	private static final int INICIO_FEBRE = 38;
	private static final int INTERVALO_REQUISICAO = 6000;
	private final int AGENTSNUMBER = 2;
	
	private List<AID> monitor;
	private List<AID> atuador;
	
	private String monitorName, atuadorName;
	
	public void setup() {
		System.out.println("Centralizador: Sou o agente monitor " + getAID().getName() + " e estou pronto.");

		// create agent t1 on the same container of the creator agent
		AgentContainer container = (AgentContainer) getContainerController();

		for (int i = 0; i < AGENTSNUMBER; i++) {
			// create MONITOR
			monitorName = "monitor" + Integer.toString(i);
			CreatNewAgent(container, monitorName, "agentes.AgenteMonitor");

			// create ATUADOR
			atuadorName = "atuador" + Integer.toString(i);
			CreatNewAgent(container, atuadorName, "agentes.AgenteAtuador");
		}

		// minute
		addBehaviour(new TickerBehaviour(this, INTERVALO_REQUISICAO) {
			protected void onTick() {
				// manda para algum monitor uma mensagem aleatoria
				int indexMsg = (int) (Math.random() * (AGENTSNUMBER-1));
				sendMessageToAgent(GeradorAleatorioMsg.getRandomMessageCentralizador(), "monitor" + indexMsg);
				
				//atualiza de tempos em tempos lista de agentes
				// Atualiza lista de monitores
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription sd = new ServiceDescription();
				sd.setType("monitor");
				template.addServices(sd);
				try {
					DFAgentDescription[] result = DFService.search(myAgent, template); 
					System.out.print("Centralizador: Achei os seguintes monitores:");
					monitor = new ArrayList<AID>(); 
					for (int i = 0; i < result.length; ++i) {
						monitor.add(result[i].getName());
						System.out.print(" | " + monitor.get(i).getName());
					}
					System.out.println();
				}
				catch (FIPAException fe) {
					fe.printStackTrace();
				}
				
				// Atualiza lista de atuadores
//				DFAgentDescription template2 = new DFAgentDescription();
//				ServiceDescription sd2 = new ServiceDescription();
//				sd2.setType("atuador");
//				template2.addServices(sd2);
//				try {
//					DFAgentDescription[] result2 = DFService.search(myAgent, template2); 
//					System.out.println("Achei os seguintes atuadores:");
//					atuador = new ArrayList<AID>();
//					for (int i = 0; i < result2.length; ++i) {
//						atuador.add(result2[i].getName());
//						System.out.println(atuador.get(i).getName());
//					}
//				}
//				catch (FIPAException fe) {
//					fe.printStackTrace();
//				}
			}
		});
		
		addBehaviour(new CyclicBehaviour(this) {
			@Override
			public void action() {
				MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
				ACLMessage msg = receive(mt);

				if (msg != null) {
					// quem enviou a mensagem?
					AID sender = msg.getSender();

					// se o sender for monitor
					if (monitor.contains(sender)) {
						System.out.println("Centralizador: Msg recebida do monitor " + msg.getSender().getLocalName() + " -->" + msg.getContent());
						// valida mensagem
						TarefaMonitor tm = new TarefaMonitor();
						try {
							tm = GrammarParserMonitor.getMonitorMessageObject(msg.getContent());
							List<Afericao> listaAF = tm.getAfericoes();
							// exemplo de tratamento para temperatura
							for (Afericao af : listaAF) {
								if (af.getDado() instanceof EDados) { // veja gramatica para EDados
									if (af.getQuantidade1() > INICIO_FEBRE)
										System.out.print("Centralizador: " + sender.getName() + " esta com febre, devo aplicar remedio");
									else System.out.print("Centralizador: " + sender.getName() + " tem temperatura boa, nao precisa remedio");
								}
							}
						} catch (Exception e){
							System.out.println(e.getMessage());
						}
					}
					else if (atuador.contains(sender))
						System.out.println("Centralizador: Msg recebida do atuador " + msg.getSender().getLocalName() + " -->" + msg.getContent());
					else System.out.println("Centralizador: ERRO: Mensagem recebida do desconhecido");
					System.out.println("**********************");
					System.out.println();
				}

			}
		});
	}

	private AID CreatNewAgent(AgentContainer container, String agentName, String agentAdrress) {
		AID minhaID = null;
		try {
			System.out.println("agentName  ++++" + agentName);
			container.createNewAgent(agentName, agentAdrress, null).start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return minhaID;
	}

	private void sendMessageToAgent(String mensagem, String agentName) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(new AID(agentName, AID.ISLOCALNAME));
		message.setContent(mensagem);
		System.out.println("Centralizador: Enviei mensagem para " + agentName + " : " + mensagem);
		send(message);
	}
}