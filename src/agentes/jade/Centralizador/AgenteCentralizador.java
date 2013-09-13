package agentes.jade.Centralizador;

import gramatica.Monitor.Absyn.EDados;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

import java.util.List;

import util.GeradorAleatorioMsg;
import agentes.UpdateAgentList;
import agentes.jade.Monitor.Afericao;
import agentes.jade.Monitor.GrammarParserMonitor;
import agentes.jade.Monitor.TarefaMonitor;

public class AgenteCentralizador extends Agent {
	private static final int INICIO_FEBRE = 38;
	private static final int INTERVALO_REQUISICAO = 8000;
	private final int AGENTSNUMBER = 1;
	
	private List<AID> monitor;
	private List<AID> atuador;
	int teste =0;
	
	private String monitorName, atuadorName;
	
	/*
	 * TODO LIST
	 * TODO Implementar metodo que transforma objeto em mensagem de texto string
	 * TODO Implementar geração automatica de mensagens randomicas a partir da gramatica
	 * TODO Implementar logica de decisao do centralizador
	 * TODO Implementar armazenamento e tabulacao dos dados no centralizador
	 * TODO Centralizador precisa saber de tudo que está fazendo, exemplos:
	 * 1 - Estou aplicando remedios agora? quais? Não vou pedir para aplciar a mesma coisa se já estou aplicando
	 * 2 - Estou medindo alguma cosia agora? o que? Nao vou pedir para medir a mesma coisa se já estou medindo
	 * 
	 * */
	
	public void setup() {
		System.out.println(getLocalName() +  ": Sou o agente " + getAID().getLocalName() + " e estou pronto.");

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
				int indexMsg = (int) (Math.random() * (AGENTSNUMBER));
				// TODO @MARCO 1 - Remover o envio aleatório de mensagem. O centralizador deve de tempo em tempo mandar medir algo (temp ou pressao ou bilirrubina ou etc....)
				// TODO @MARCO 2 - Baseado na leitura dos dados decidir se para medicao ou se continua medicao e aplica remedio
				sendMessageToAgent(GeradorAleatorioMsg.getRandomMessageCentralizadorToMonitor(), "monitor" + indexMsg);
		
				sendMessageToAgent(GeradorAleatorioMsg.getRandomMessageCentralizadorToAtuador(), "atuador" + indexMsg);
				
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
							List<Afericao> afericoes = tm.getAfericoes();
							// exemplo de tratamento para temperatura
							for (Afericao af : afericoes) {
								if (af.getDado() instanceof EDados) { // veja gramatica para EDados
									// TODO @MARCO 3 - Implementar decisao de parar medicao de temp ou continuar medicao e aplicar remedio 									
									if (af.getQuantidade1() > INICIO_FEBRE)
										System.out.println(getLocalName() + ": " + sender.getLocalName() + " esta com febre, devo aplicar remedio.NAO IMPLEMENTADO\n");
									else System.out.println(getLocalName() + ": " + sender.getLocalName() + " tem temperatura boa, nao precisa remedio.NAO IMPLEMENTADO\n");
								}
								// TODO @MARCO 4 - Implementar decisao de parar medicao de hemoglob ou continuar medicao e aplicar remedio
								// TODO @MARCO 5 - Implementar decisao de parar medicao de bilirrubina ou continuar medicao e aplicar remedio
								// TODO @MARCO 6 - Implementar decisao de parar medicao de pressao art ou continuar medicao e aplicar remedio
								
							}
						} catch (Exception e){
							System.out.println(e.getMessage());
						}
					}
					else if (atuador.contains(sender))
						System.out.println(getLocalName() + ": Msg recebida do " + msg.getSender().getLocalName() + " -->" + msg.getContent());
					else {
						System.out.println(getLocalName() + ": ERRO: Mensagem recebida de : " + sender.getLocalName() + "" + msg.getContent());
					}
//					System.out.println("**********************");
//					System.out.println();
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