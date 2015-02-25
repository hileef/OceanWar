package ships;

public abstract class CuriousExtension implements Extension {

	private Inspector radar;
	
	public CuriousExtension(Inspector i) {
		radar = i;
	}
	
	protected Inspector radar() {
		return radar;
	}
	
}
