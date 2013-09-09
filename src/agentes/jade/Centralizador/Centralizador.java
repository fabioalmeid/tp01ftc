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

import agentes.jade.Monitor.Afericao;
import agentes.jade.Monitor.GrammarParserMonitor;
import agentes.jade.Monitor.TarefaMonitor;

import util.Actions;
import util.GeradorAleatorioMsg;

public class Centralizador extends Agent {
	private static final int INICIO_FEBRE = 38;
	private static final int INTERVALO_REQUISICAO = 6000;
	private final int AGENTSNUMBER = 2;
	
	private String monitorName, atuadorName;
	
	// Lista de todos os monitores e atuadores
	private List<AID> monitores = new ArrayList<AID>();
	private List<AID> atuadores = new ArrayList<AID>();

	public void setup() {
		System.out.println("Sou o agente monitor " + getAID().getName() + " e estou pronto.");

		// create agent t1 on the same container of the creator agent
		AgentContainer container = (AgentContainer) getContainerController();

		for (int i = 0; i < AGENTSNUMBER; i++) {
			// create MONITOR
			monitorName = "monitor" + Integer.toString(i);
			monitores.add(CreatNewAgent(container, monitorName, "agentes.AgenteMonitor"));

			// create ATUADOR
			atuadorName = "atuador" + Integer.toString(i);
			atuadores.add(CreatNewAgent(container, atuadorName, "agentes.AgenteAtuador"));
		}

		// minute
		addBehaviour(new TickerBehaviour(this, INTERVALO_REQUISICAO) {
			protected void onTick() {
				// manda para algum monitor uma mensagem aleatoria
				int indexMsg = (int) (Math.random() * (AGENTSNUMBER-1));
				sendMsgToMonitor(GeradorAleatorioMsg.getRandomMessageCentralizador(), "monitor" + indexMsg);
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
					if (monitores.contains(sender)) {
						System.out.println("Msg recebida do monitor " + msg.getSender().getLocalName() + " -->" + msg.getContent());
						// valida mensagem
						TarefaMonitor tm = new TarefaMonitor();
						try {
							tm = GrammarParserMonitor.getMonitorMessageObject(msg.getContent());
							List<Afericao> listaAF = tm.getAfericoes();
							// exemplo de tratamento para temperatura
							for (Afericao af : listaAF) {
								if (af.getDado() instanceof EDados) { // veja gramatica para EDados
									if (af.getQuantidade1() > INICIO_FEBRE)
										System.out.print("Esta com febre, devo aplicar remedio");										
								}
							}
						} catch (Exception e){
							System.out.println(e.getMessage());
						}
					}
					else if (atuadores.contains(sender))
						System.out.println("Msg recebida do atuador " + msg.getSender().getLocalName() + " -->" + msg.getContent());
					else System.out.println("ERRO: Mensagem recebida do desconhecido");

//					defineAction(Integer.valueOf(msg.getContent().substring(
//							index, msg.getContent().toString().length())), msg
//							.getSender().getLocalName());

					System.out.println("**********************");
					System.out.println();
				}

			}
		});
	}

//	@SuppressWarnings("null")
//	private void defineAction(int CurrentTemp, String agentName){
//		switch (CurrentTemp) {
//		case 33:
//			System.out.println("**************************");
//			System.out.println("3333333");
//			System.out.println("**************************");
//			sendMsgToMonitor(Actions.PARAR_LEITURA_TEMPERATURA_MONITOR, (Integer) null, agentName );
//			break;
//
//		default:
//			break;
//		}		
//	}
	private AID CreatNewAgent(AgentContainer container, String agentName, String agentAdrress) {
		AID minhaID = null;
		try {
			System.out.println("agentName  ++++" + agentName);
			container.createNewAgent(agentName, agentAdrress, null).start();

			// Regista o novo agente no servico de paginas amarelas
			DFAgentDescription dfd = new DFAgentDescription();
			minhaID = getAID();
			dfd.setName(minhaID);
			ServiceDescription sd = new ServiceDescription();
			sd.setType(agentAdrress);
			sd.setName(agentName);
			dfd.addServices(sd);
			DFService.register(this, dfd);

		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		return minhaID;
	}

	private String defineMonitorAction(int switchCaseLenght) {
		int indexMsg = (int) (Math.random() * switchCaseLenght);
		String msgSelected = null;
		switch (indexMsg) {
		case 0:
			msgSelected = Actions.INICIAR_LEITURA_TEMPERATURA_MONITOR;
			break;
		case 1:
			msgSelected = Actions.INICIAR_LEITURA_TEMPERATURA_MONITOR;
//			msgSelected = Actions.INICIAR_LEITURA_HEMOGRAMA_MONITOR; //////// just test, RETURN to default
			break;

		}
		return msgSelected;

	}

	private void sendMsgToMonitor(String action, String agentName) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(new AID(agentName, AID.ISLOCALNAME));
		String currentMsg = defineMonitorAction(2);
//		message.setContent( currentMsg + "_" + currentValue);
		message.setContent(currentMsg);
		System.out.println(agentName + " " + currentMsg);
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