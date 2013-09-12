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
import agentes.UpdateAgentList;
import agentes.jade.Monitor.Afericao;
import agentes.jade.Monitor.GrammarParserMonitor;
import agentes.jade.Monitor.TarefaMonitor;

public class AgenteCentralizador extends Agent {
	private static final int INICIO_FEBRE = 38;
	private static final int INTERVALO_REQUISICAO = 10000;
	private final int AGENTSNUMBER = 2;
	
	private List<AID> monitor;
	private List<AID> atuador;
	
	private String monitorName, atuadorName;
	
	/*
	 * TODO LIST
	 * TODO Implementar metodo que transforma objeto em mensagem de texto string
	 * TODO Implementar geração automatica de mensagens randomicas a partir da gramatica
	 * TODO Implementar logica de decisao do centralizador
	 * TODO Implementar armazenamento e tabulacao dos dados no centralizador
	 * */
	
	public void setup() {
		System.out.println("Centralizador: Sou o agente monitor " + getAID().getLocalName() + " e estou pronto.");

		// create agent t1 on the same container of the creator agent
		AgentContainer container = (AgentContainer) getContainerController();

		for (int i = 0; i < AGENTSNUMBER; i++) {
			// create MONITOR
			monitorName = "monitor" + Integer.toString(i);
			CreatNewAgent(container, monitorName, "agentes.jade.Monitor.AgenteMonitor");

			// create ATUADOR
			atuadorName = "atuador" + Integer.toString(i);
			CreatNewAgent(container, atuadorName, "agentes.jade.Atuador.AgenteAtuador");
		}
		
		CreatNewAgent(container, "paciente", "agentes.jade.Paciente.AgentePaciente");

		// minute
		addBehaviour(new TickerBehaviour(this, INTERVALO_REQUISICAO) {
			protected void onTick() {
				// manda para algum monitor uma mensagem aleatoria
				int indexMsg = (int) (Math.random() * (AGENTSNUMBER-1));
				//sendMessageToAgent(GeradorAleatorioMsg.getRandomMessageCentralizador(), "monitor" + indexMsg);
				
				//sendMessageToAgent(GeradorAleatorioMsg.getRandomMessageCentralizador(), "atuador" + indexMsg);
				
				sendMessageToAgent("Liberar 8 Dipirona", "atuador" + indexMsg);
				
				monitor = UpdateAgentList.getAgentUpdatedList("monitor", myAgent);
//				System.out.print(getLocalName() + ": Achei os seguintes monitores:");
//				for (AID m : monitor) {
//					System.out.print(" | " + m.getLocalName());
//				}
//				System.out.println();
				
				atuador = UpdateAgentList.getAgentUpdatedList("atuador", myAgent);
//				System.out.print(getLocalName() + ": Achei os seguintes atuadores:");
//				for (AID a : atuador) {
//					System.out.print(" | " + a.getLocalName());
//				}
//				System.out.println();
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
						System.out.println(getLocalName() + ": Msg recebida do monitor " + msg.getSender().getLocalName() + " -->" + msg.getContent());
						// valida mensagem
						TarefaMonitor tm = new TarefaMonitor();
						try {
							tm = GrammarParserMonitor.getMonitorMessageObject(msg.getContent());
							ArrayList<ArrayList<Afericao>> listaAF = tm.getListaDeListaAfericoes();
							// exemplo de tratamento para temperatura
							for (ArrayList<Afericao> afericoes : listaAF) {
								for (Afericao af : afericoes) {
									if (af.getDado() instanceof EDados) { // veja gramatica para EDados
										if (af.getQuantidade1() > INICIO_FEBRE)
											System.out.print(getLocalName() + ": " + sender.getLocalName() + " esta com febre, devo aplicar remedio");
										else System.out.print(getLocalName() + ": " + sender.getLocalName() + " tem temperatura boa, nao precisa remedio");
									}
								}
							}
						} catch (Exception e){
							System.out.println(e.getMessage());
						}
					}
					else if (atuador.contains(sender))
						System.out.println(getLocalName() + ": Msg recebida do atuador " + msg.getSender().getLocalName() + " -->" + msg.getContent());
					else {
						System.out.println(getLocalName() + ": ERRO: Mensagem recebida de : " + sender.getLocalName() + "" + msg.getContent());
					}
					System.out.println("**********************");
					System.out.println();
				}

			}
		});
	}

	private AID CreatNewAgent(AgentContainer container, String agentName, String agentAdrress) {
		AID minhaID = null;
		try {
			System.out.println(getLocalName() + ": Agente " + agentName + " criado com sucesso");
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
		System.out.println(getLocalName() + ": Enviei mensagem para " + agentName + " : " + mensagem);
		send(message);
	}
}