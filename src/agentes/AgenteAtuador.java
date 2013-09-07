package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgenteAtuador extends Agent {

	private static final long serialVersionUID = 1L;

	protected void setup() {
		
		addBehaviour(new CyclicBehaviour(this) {

			private static final long serialVersionUID = 1L;

			@Override
			public void action() {

				ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
				if (msg != null) {
//					System.out.println("Recebi mensagem de " + msg.getSender());
//					System.out.println("Conteudo : " + msg.getContent());
					
					sendMsgtoCentralizador(msg);
				}
			}
		});
	}

	private void sendMsgtoCentralizador(ACLMessage msg) {
		ACLMessage replica = msg.createReply();
		replica.setPerformative(ACLMessage.INFORM);
		replica.setContent("Foi Aplicado a Droga no paciente");
		send(replica);

	}
}
