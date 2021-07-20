package osmium.structure;

public class StructureConfig {

	public int cfg1, cfg2, cfg3;
	public StructureEnum type;

	public static StructureConfig getStructureConfiguration(StructureEnum type) {
		switch (type) {
		case BASTION:
			return new StructureConfig(30084232, 27, 23, StructureEnum.BASTION);
		case FORTRESS:
			return new StructureConfig(30084232, 27, 23, StructureEnum.FORTRESS);
		default:
			return null;
		}
	}

	private StructureConfig(int cfg1, int cfg2, int cfg3, StructureEnum type) {
		this.cfg1 = cfg1;
		this.cfg2 = cfg2;
		this.cfg3 = cfg3;
		this.type = type;
	}
}
