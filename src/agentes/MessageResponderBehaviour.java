package agentes;

import jade.core.Agent;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MessageResponderBehaviour extends Behaviour {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate
            .MatchPerformative(ACLMessage.REQUEST);
    
    public MessageResponderBehaviour(Agent a) {
        super(a);
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.blockingReceive(mt);  /// pq blocingReceive
        if (msg != null) {
            System.out.println("Recebimensagem de " + msg.getSender());
            System.out.println("Conteudo : " + msg.getContent());
        }
    }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}