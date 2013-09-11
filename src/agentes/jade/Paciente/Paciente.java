package agentes.jade.Paciente;

public class Paciente {
	private final static int MIN_TEMP = 35;
	private final static int MAX_TEMP = 40;
	
	private static Integer temperatura = 38;
	private static Boolean remedioTemp = false;
	
	private static Integer hemoglobina = 13;
	private static Boolean remedioHemoglobina = false;
	
	private static Integer bilirrubina = 3;
	private static Boolean remedioBilirrubuna = false;
	
	private static Integer pressao = 0;
	private static Boolean remedioPressao = false;
	
	public static Integer getPressao() {
		return pressao;
	}

	public static void setPressao(Integer _pressao) {
		pressao = _pressao;
	}
	
	public static Integer getHemoglobina() {
		return hemoglobina;
	}

	public static void setHemoglobina(Integer _hemoglobina) {
		hemoglobina = _hemoglobina;
	}

	public static Integer getBilirrubina() {
		return bilirrubina;
	}

	public static void setBilirrubina(Integer _bilirrubina) {
		bilirrubina = _bilirrubina;
	}
	
	public static Integer getTemperatura() {
		return temperatura;
	}
	
	public static void setTemperatura(Integer _temperatura) {
		if ( (_temperatura >= MIN_TEMP) && (_temperatura <= MAX_TEMP)) 
			temperatura = _temperatura;
	}

	public static Boolean getRemedioTemp() {
		return remedioTemp;
	}

	public static void setRemedioTemp(Boolean remedioTemp) {
		Paciente.remedioTemp = remedioTemp;
	}

	public static Boolean getRemedioHemoglobina() {
		return remedioHemoglobina;
	}

	public static void setRemedioHemoglobina(Boolean remedioHemoglobina) {
		Paciente.remedioHemoglobina = remedioHemoglobina;
	}

	public static Boolean getRemedioBilirrubuna() {
		return remedioBilirrubuna;
	}

	public static void setRemedioBilirrubuna(Boolean remedioBilirrubuna) {
		Paciente.remedioBilirrubuna = remedioBilirrubuna;
	}

	public static Boolean getRemedioPressao() {
		return remedioPressao;
	}

	public static void setRemedioPressao(Boolean remedioPressao) {
		Paciente.remedioPressao = remedioPressao;
	}
}