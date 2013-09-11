package agentes;

import gramatica.Centralizador.Absyn.ECollect1;
import gramatica.Centralizador.Absyn.ECollect2;
import gramatica.Centralizador.Absyn.EData1;
import gramatica.Centralizador.Absyn.EData2;
import gramatica.Centralizador.Absyn.EData3;
import gramatica.Centralizador.Absyn.EData4;

import java.util.List;

import agentes.AgenteMonitor.InformBilirrubinaBehaviour;
import agentes.AgenteMonitor.InformHemoglobinaBehaviour;
import agentes.AgenteMonitor.InformTempBehaviour;
import agentes.jade.Centralizador.GrammarParserCentralizador;
import agentes.jade.Centralizador.TarefaCentralizador;
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

public class AgenteAtuador extends Agent {

	private static final long serialVersionUID = 1L;

	private ACLMessage ACLmsg;
	private Boolean isDipironaRunning, isParacetamolRunning = false;
	private Behaviour checkDipirona, checkParacetamol = null;

	protected void setup() {

		// Regista o monitor no servico de paginas amarelas
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("atuador");
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
							System.out.print("Atuador: Liberando Remedio");
							List<Object> dados = tc.getDados();
							for (Object o : dados) {
								if (o.equals("")) { // **********************************CHANGE***********************************
									System.out.print(" Dipirona\n");
									if (!isDipironaRunning) {
										checkDipirona = new InformDipironaBehaviour(myAgent, ACLmsg);
										addBehaviour(checkDipirona);
										isDipironaRunning = true;
									} else
										System.out.println("Monitor " + getName() + " ja esta liberando Dipirona.");
								} else if (o.equals("")) { // **********************************CHANGE***********************************
									System.out.print(" Paracetamol\n");
									if (!isParacetamolRunning) {
										checkParacetamol = new InformParacetamolBehaviour(myAgent, ACLmsg);
										addBehaviour(checkParacetamol);
										isParacetamolRunning = true;
									} else
										System.out.println("Monitor " + getName() + " ja esta monitorando Paracetamol.");
								}
							}
						} else if (tc.getAcao() instanceof ECollect2) {
							System.out.print("Monitor: Parando medicao");
							List<Object> dados = tc.getDados();
							for (Object o : dados) {
								if (o instanceof EData1) { // **********************************CHANGE***********************************
									System.out.print(" Dipirona\n");
									if (isDipironaRunning) {
										removeBehaviour(checkDipirona);
									} else
										System.out.println("\nMonitor "	+ getName() + " nao esta monitorando Dipirona.");
								} else if (o instanceof EData2) {// **********************************CHANGE***********************************
									System.out.print(" Hemoglobina\n");
									if (isParacetamolRunning) {
										removeBehaviour(checkParacetamol);
									} else
										System.out.println("\nMonitor " + getName()+ " nao esta monitorando Paracetamol.");
								}
							}

						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		});
	}

	
	// MONITOR informs to Centralizador a given DIPIRONA at each 2s
	class InformDipironaBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;
		private ACLMessage aclMessage;

		public InformDipironaBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("Monitor: aclMessage********************************" + aclMessage);
		}

		@Override
		protected void onTick() {
			// int Min = 36;
			// int Max = 41;
			// int temperatura = Min + (int) (Math.random() * ((Max - Min) +
			// 1));
			String resposta = "Liberando Dipirona";
			System.out.println("Monitor: msgtoSend para Centralizador  >>>>> "+ resposta);
			sendMsgtoCentralizador(aclMessage, resposta);
		}

	}

	// MONITOR informs to Centralizador a given PARACETAMOL at each 2s
	class InformParacetamolBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;
		private ACLMessage aclMessage;

		public InformParacetamolBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("Monitor: aclMessage********************************"+ aclMessage);
		}

		@Override
		protected void onTick() {
			// int Min = 36;
			// int Max = 41;
			// int temperatura = Min + (int) (Math.random() * ((Max - Min) +
			// 1));
			String resposta = "Liberando Paracetamol";
			System.out.println("Monitor: msgtoSend para Centralizador  >>>>> " + resposta);
			sendMsgtoCentralizador(aclMessage, resposta);
		}

	}

	private void sendMsgtoCentralizador(ACLMessage msg, String msgToSend) {
		ACLMessage replica = msg.createReply();
		replica.setPerformative(ACLMessage.INFORM);
		replica.setContent(msgToSend);
		send(replica);

	}
}
