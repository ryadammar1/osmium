package osmium.structure;

import osmium.exception.InvalidStructureTypeException;

public class Structure {

	public int cfg1, cfg2, cfg3;
	public StructureEnum type;

	public static Structure getStructure(StructureEnum type) throws InvalidStructureTypeException {
		switch (type) {
		case BASTION:
			return new Structure(30084232, 27, 23, StructureEnum.BASTION);
		case FORTRESS:
			return new Structure(30084232, 27, 23, StructureEnum.FORTRESS);
		default:
			throw new InvalidStructureTypeException();
		}
	}

	private Structure(int cfg1, int cfg2, int cfg3, StructureEnum type) {
		this.cfg1 = cfg1;
		this.cfg2 = cfg2;
		this.cfg3 = cfg3;
		this.type = type;
	}
}
