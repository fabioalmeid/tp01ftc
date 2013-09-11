package agentes.jade.Paciente;

import gramatica.Centralizador.Absyn.EApply1;
import gramatica.Centralizador.Absyn.EApply2;
import gramatica.Centralizador.Absyn.ECollect1;
import gramatica.Centralizador.Absyn.EData1;
import gramatica.Centralizador.Absyn.EData2;
import gramatica.Centralizador.Absyn.EData3;
import gramatica.Centralizador.Absyn.EData4;
import gramatica.Centralizador.Absyn.ERemedy1;
import gramatica.Centralizador.Absyn.ERemedy2;
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

import java.util.ArrayList;
import java.util.List;

import agentes.jade.Centralizador.GrammarParserCentralizador;
import agentes.jade.Centralizador.Medicamento;
import agentes.jade.Centralizador.TarefaCentralizador;

public class AgentePaciente extends Agent {
	private static final long INTERVALO_ATUALIZACAO = 10000;
	private static final int MAX_TICKS_UNCHANGED = 6;
	private List<AID> monitor;
	private List<AID> atuador;
	
	protected void setup() {
		// Regista o paciente no servico de paginas amarelas
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("paciente");
		sd.setName(getName());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		// atualiza lista de monitores e atuadores
		addBehaviour(new UpdateAgentsTempBehaviour(this, INTERVALO_ATUALIZACAO));
		
		// ouve requisicoes dos atuadores e monitores
		addBehaviour(new ListenBehaviour());
		
		// adiciona comportamento de mudanca de temperatura
		addBehaviour(new UpdateTemperatureBehaviour(this, INTERVALO_ATUALIZACAO));
		
		// adiciona comportamento de mudanca de hemoglobina		
		addBehaviour(new UpdateHemoglobinaBehaviour(this, INTERVALO_ATUALIZACAO));
		
		// adiciona comportamento de mudanca de bilirrubina
		addBehaviour(new UpdateBilirrubinaBehaviour(this, INTERVALO_ATUALIZACAO));
		
	}
	
	class ListenBehaviour extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage ACLmsg = receive(MessageTemplate
					.MatchPerformative(ACLMessage.REQUEST));

			if (ACLmsg != null) {
				String mensagem = ACLmsg.getContent().toString();
				AID sender = ACLmsg.getSender();
				String resposta = "";

				// se o sender for monitor
				if (monitor.contains(sender)) {

					try {
						TarefaCentralizador tc = GrammarParserCentralizador.getCentralizadorMessageObject(mensagem);
						if (tc.getAcao() instanceof ECollect1) { // ECollect1. Coletar ::= "Iniciar Medicao";
							List<Object> dados = tc.getDados();
							for (Object o : dados) {
								if (o instanceof EData1)
									resposta = resposta	+ String.valueOf(Paciente.getTemperatura()) + ";";
								else if (o instanceof EData2)
									resposta = resposta + String.valueOf(Paciente.getHemoglobina()) + ";";
								else if (o instanceof EData3)
									resposta = resposta + String.valueOf(Paciente.getBilirrubina()) + ";";
								else if (o instanceof EData4)
									resposta = resposta + String.valueOf(Paciente.getPressao()) + ";";
							}
						} else if (tc.getAcao() instanceof EApply1) { // EApply1. Aplicar ::= "Liberar"; 
							List<Medicamento> med = tc.getMedicacao();
							for (Medicamento m : med) {
								if (m.remedio instanceof ERemedy1) {
									// dipirona sara temperatura e hemoglobina
									Paciente.setRemedioTemp(true);
									Paciente.setRemedioHemoglobina(true);
									resposta = "Dipirona aplicada";
								} else if (m.remedio instanceof ERemedy2) {
									// paracetamol vai curar bilirrubina e pressao
									Paciente.setRemedioBilirrubuna(true);
									Paciente.setRemedioPressao(true);
									resposta = "Paracetamol aplicado";
								}
							}

						} else if (tc.getAcao() instanceof EApply2) { // EApply2. Aplicar ::= "Cessar Liberacao";
							System.out.print("Cessando liberacao da medicacao");
							List<Medicamento> med = tc.getMedicacao();
							for (Medicamento m : med) {
								if (m.remedio instanceof ERemedy1) {
									// dipirona sara temperatura e hemoglobina
									Paciente.setRemedioTemp(false);
									Paciente.setRemedioHemoglobina(false);
								} else if (m.remedio instanceof ERemedy2) {
									// paracetamol vai curar bilirrubina e pressao
									Paciente.setRemedioBilirrubuna(false);
									Paciente.setRemedioPressao(false);
								}
							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else if (atuador.contains(sender)) {
					System.out.println("AINDA NAO IMPLEMENTADO");
				}

				ACLMessage replica = ACLmsg.createReply();
				replica.setPerformative(ACLMessage.INFORM);
				replica.setContent(resposta);
				send(replica);
			}
		}		
	}
	
	class UpdateTemperatureBehaviour extends TickerBehaviour {
		int ticks = 0;
		
		public UpdateTemperatureBehaviour(Agent a, long period) {
			super(a, period);
		}
		
		private Integer randomTemperature(){
			int Min = 36, Max = 41;
			return Min + (int)(Math.random() * ((Max - Min) + 1));
		}

		@Override
		protected void onTick() {
			if (Paciente.getRemedioTemp()) { // tem remedio para temperatura
				// decrementa temperatura a cada ticks
				Paciente.setTemperatura(Paciente.getTemperatura()-1);
				ticks = 0;				
			} else {
				ticks++;
				if (ticks >= MAX_TICKS_UNCHANGED) {
					Paciente.setTemperatura(randomTemperature());
					ticks = 0;
				}
			}
			
		}
	}
	
	class UpdateHemoglobinaBehaviour extends TickerBehaviour {
		int ticks = 0;
		
		public UpdateHemoglobinaBehaviour(Agent a, long period) {
			super(a, period);
		}
		
		private Integer randomHemoglobina(){
			// 13,5 - 18 bom
			// abaixo 12.5 anemia, acima de 18 doente
			int Min = 9, Max = 23;
			return Min + (int)(Math.random() * ((Max - Min) + 1));
		}

		@Override
		protected void onTick() {
			if (Paciente.getRemedioHemoglobina()) { // tem remedio para temperatura
				// decrementa temperatura a cada ticks
				Paciente.setHemoglobina(Paciente.getHemoglobinaBoa());
				ticks = 0;				
			} else {
				ticks++;
				if (ticks >= MAX_TICKS_UNCHANGED) {
					Paciente.setHemoglobina(randomHemoglobina());
					ticks = 0;
				}
			}
			
		}
	}
	
	class UpdateBilirrubinaBehaviour extends TickerBehaviour {
		int ticks = 0;
		
		public UpdateBilirrubinaBehaviour(Agent a, long period) {
			super(a, period);
		}
		
		private Integer randomBilirrubina(){
			// 0 - 10 bom
			// acima de 10 doente
			int Min = 0, Max = 14;
			return Min + (int)(Math.random() * ((Max - Min) + 1));
		}

		@Override
		protected void onTick() {
			if (Paciente.getRemedioBilirrubuna()) { // tem remedio para temperatura
				// decrementa temperatura a cada ticks
				Paciente.setBilirrubina(Paciente.getBilirrubina()-1);
				ticks = 0;				
			} else {
				ticks++;
				if (ticks >= MAX_TICKS_UNCHANGED) {
					Paciente.setHemoglobina(randomBilirrubina());
					ticks = 0;
				}
			}
			
		}
	}
	
	// classe que atualiza os registos dos monitores e atuadores
	class UpdateAgentsTempBehaviour extends TickerBehaviour {

		public UpdateAgentsTempBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			// atualiza de tempos em tempos lista de agentes
			// Atualiza lista de monitores
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("monitor");
			template.addServices(sd);
			try {
				DFAgentDescription[] result = DFService.search(myAgent,template);
				monitor = new ArrayList<AID>();
				for (int i = 0; i < result.length; ++i)
					monitor.add(result[i].getName());
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}
			
			// Atualiza lista de atuadores
//			DFAgentDescription template2 = new DFAgentDescription();
//			ServiceDescription sd2 = new ServiceDescription();
//			sd2.setType("atuador");
//			template2.addServices(sd2);
//			try {
//				DFAgentDescription[] result2 = DFService.search(myAgent, template2); 
//				System.out.println("Achei os seguintes atuadores:");
//				atuador = new ArrayList<AID>();
//				for (int i = 0; i < result2.length; ++i) {
//					atuador.add(result2[i].getName());
//					System.out.println(atuador.get(i).getName());
//				}
//			}
//			catch (FIPAException fe) {
//				fe.printStackTrace();
//			}
		}
	}
}
