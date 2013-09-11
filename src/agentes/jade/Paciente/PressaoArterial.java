package agentes.jade.Paciente;

public class PressaoArterial {
	private static final int MIN_PRESSAO_DIAS = 50;
	private static final int MIN_PRESSAO_SIST = 90;
	
	private static final int MAX_PRESSAO_DIAS = 130;
	private static final int MAX_PRESSAO_SIST = 200;
	
	
	private int Diast = 60;
	private int Sist = 100;
	
	public int getDiast() {
		return Diast;
	}
	public int getRandomDiast() {
		int Min = MIN_PRESSAO_DIAS, Max = MAX_PRESSAO_DIAS;
		Diast = Min + (int)(Math.random() * ((Max - Min) + 1));
		return Diast;
	}
	public int getDiastBoa() {
		int Min = 60, Max = 90;
		Diast = Min + (int)(Math.random() * ((Max - Min) + 1));
		return Diast;
	}
	public void setDiast(int diast) {
		Diast = diast;
	}
	public int getSist() {
		return Sist;
	}
	public int getRandomSist() {
		int Min = MIN_PRESSAO_SIST, Max = MAX_PRESSAO_SIST;
		Sist = Min + (int)(Math.random() * ((Max - Min) + 1));
		return Sist;
	}
	public int getSistBoa() {
		int Min = 100, Max = 140;
		Sist = Min + (int)(Math.random() * ((Max - Min) + 1));
		return Sist;
	}
	public void setSist(int sist) {
		Sist = sist;
	}
	
	

}
