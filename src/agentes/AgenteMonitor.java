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

import agentes.jade.Centralizador.GrammarParserCentralizador;
import agentes.jade.Centralizador.TarefaCentralizador;

public class AgenteMonitor extends Agent {

	private static final long serialVersionUID = 1L;

	private ACLMessage ACLmsg;
	private Boolean isTempRunning, isHemoglobinaRunning, isBilirrubinaRunning, isBloodPressureRunning = false;
	private Behaviour checkTemperature, checkHemoglobina, checkBilirrubina, checkBloodPressure = null;

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
							System.out.print("Monitor: Iniciando a medicao");
							List<Object> dados = tc.getDados();
							for (Object o : dados) {
								if (o instanceof EData1) { // TEMPERATURE
									System.out.print(" Temperatura\n");
									if (!isTempRunning) {
										checkTemperature = new InformTempBehaviour(myAgent, ACLmsg);
										addBehaviour(checkTemperature);
										isTempRunning = true;
									} else
										System.out.println("Monitor " + getName() + " ja esta monitorando temperatura.");
								} else if (o instanceof EData2) { // HEMOGLOBINA
									System.out.print(" Hemoglobina\n");
									if (!isHemoglobinaRunning) {
										checkHemoglobina = new InformHemoglobinaBehaviour(myAgent, ACLmsg);
										addBehaviour(checkHemoglobina);
										isHemoglobinaRunning = true;
									} else
										System.out.println("Monitor " + getName() + " ja esta monitorando Hemoglobina.");
								} else if (o instanceof EData3) { // BILIRRUBINA
									System.out.print(" Bilirrubina\n");
									if (!isBilirrubinaRunning) {
										checkBilirrubina = new InformBilirrubinaBehaviour(myAgent, ACLmsg);
										addBehaviour(checkBilirrubina);
										isBilirrubinaRunning = true;
									} else
										System.out.println("Monitor " + getName() + " ja esta monitorando Bilirrubina.");
								} else if (o instanceof EData4) { // PRESSAO ARTERIAL
									System.out.print(" Pressao Arterial\n");
									if (!isBloodPressureRunning) {
										checkBloodPressure = new InformBilirrubinaBehaviour(myAgent, ACLmsg);
										addBehaviour(checkBloodPressure);
										isBloodPressureRunning = true;
									} else
										System.out.println("Monitor " + getName() + " ja esta monitorando Pressao Arterial.");
								}
							}
						} else if (tc.getAcao() instanceof ECollect2) {
							System.out.print("Monitor: Parando medicao");
							List<Object> dados = tc.getDados();
							for (Object o : dados) {
								if (o instanceof EData1) {
									System.out.print(" Temperatura\n");
									if (isTempRunning) {
										removeBehaviour(checkTemperature);
									} else
										System.out.println("\nMonitor " + getName() + " nao esta monitorando Temperatura.");
								} else if (o instanceof EData2) {
									System.out.print(" Hemoglobina\n");
									if (isHemoglobinaRunning) {
										removeBehaviour(checkHemoglobina);
									} else
										System.out.println("\nMonitor " + getName() + " nao esta monitorando Hemoglobina.");
								} else if (o instanceof EData3) {
									System.out.print(" bilirrubina\n");
									if (isBilirrubinaRunning) {
										removeBehaviour(checkBilirrubina);
									} else
										System.out.println("\nMonitor " + getName() + " nao esta monitorando Bilirrubina.");
								} else if (o instanceof EData4) {
									System.out.print(" Pressao Arterial\n");
									if (isBloodPressureRunning) {
										removeBehaviour(checkBloodPressure);
									} else
										System.out.println("\nMonitor "	+ getName() + " nao esta monitorando Pressao Arterial.");
								}
							}

						}/*
						 * else if (tc.getAcao() instanceof EApply1) {
						 * System.out.print("Liberando medicacao");
						 * List<Medicamento> med = tc.getMedicacao();
						 * ImprimeMedicacao(med); } else if (tc.getAcao()
						 * instanceof EApply2){
						 * System.out.print("Cessando liberacao da medicacao");
						 * List<Medicamento> med = tc.getMedicacao();
						 * ImprimeMedicacao(med); }
						 * System.out.println("\n-------------------");
						 */
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		});
	}

	
	// MONITOR informs to Centralizador a given temperature at each 2s
	class InformTempBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;
		private ACLMessage aclMessage;

		public InformTempBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("Monitor: aclMessage********************************" + aclMessage);
		}

		@Override
		protected void onTick() {
			int Min = 36;
			int Max = 41;
			int temperatura = Min + (int) (Math.random() * ((Max - Min) + 1));
			String resposta = "Temperatura de " + temperatura + " C as 19 h: 59 m";
			System.out.println("Monitor: msgtoSend para Centralizador  >>>>> " + resposta);
			sendMsgtoCentralizador(aclMessage, resposta);
		}
	}

	
	// MONITOR informs to Centralizador a given Hemoglobina at each 2s
	class InformHemoglobinaBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;
		private ACLMessage aclMessage;

		public InformHemoglobinaBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("Monitor: aclMessage********************************" + aclMessage);
		}

		@Override
		protected void onTick() {
			int Min = 36;
			int Max = 41;
			int unit = Min + (int) (Math.random() * ((Max - Min) + 1)); //REMOVE this random from all classes
			String resposta = "Hemoglobina  " + unit + " mg/dL as 19 h: 59 m";
			System.out.println("Monitor: msgtoSend para Centralizador  >>>>> "+ resposta);
			sendMsgtoCentralizador(aclMessage, resposta);
		}
	}

	
	// MONITOR informs to Centralizador a given Bilirrubina at each 2s
	class InformBilirrubinaBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;
		private ACLMessage aclMessage;

		public InformBilirrubinaBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("Monitor: aclMessage********************************" + aclMessage);
		}

		@Override
		protected void onTick() {
			int Min = 36;
			int Max = 41;
			int unit = Min + (int) (Math.random() * ((Max - Min) + 1)); // //REMOVE this random from all classes
			String resposta = "Bilirrubina  " + unit + " g/dL as 19 h: 59 m";
			System.out.println("Monitor: msgtoSend para Centralizador  >>>>> " + resposta);
			sendMsgtoCentralizador(aclMessage, resposta);
		}
	}

	
	// MONITOR informs to Centralizador a given PRESSAO ARTERIAL at each 2s
	class InformBloodPressureBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = 1L;
		private ACLMessage aclMessage;

		public InformBloodPressureBehaviour(Agent a, ACLMessage aclMessage) {
			super(a, 2000);
			this.aclMessage = aclMessage;
			System.out.println("Monitor: aclMessage********************************" + aclMessage);
		}

		@Override
		protected void onTick() {
			int Min = 36;
			int Max = 41;
			int unit = Min + (int) (Math.random() * ((Max - Min) + 1)); // ///REMOVE this random from all classes
			String resposta = "Pressao Arterial  " + unit + " mmHg as 19 h: 59 m";
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

	private int randomNumber(int max) {
		return (int) (Math.random() * max);
	}

	private String getCurrentTime() {

		return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance() .getTime());

	}
}
