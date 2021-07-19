package osmium.random;

public class Random {
	
	private long originalSeed;
	private long seed;
	
	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed, boolean force) {
		this.seed = seed;
		if(force)
			this.originalSeed = seed;
	}
	
	public void reset() {
		seed = originalSeed;
	}
	
	public Random(long seed) {
		this.originalSeed = seed;
		
		setSeed(seed,false);
	}
	
	public Random() {
		this.originalSeed = 0;
		
		setSeed(0,false);
	}

	public int nextInt(final int n, boolean affectSeed) {
		int bits, val;
		final int m = n - 1;
		
		long savedSeed = seed;

		if ((m & n) == 0) {
			long x = n * (long) next(31);
			return (int) ((long) x >> 31);
		}

		do {
			bits = next(31);
			val = bits % n;
		} while (bits - val + m < 0);

		if(!affectSeed)
			setSeed(savedSeed,false);
		
		return val;
	}

	private int next(final int bits) {
		seed = (seed * Long.parseLong("5deece66d", 16) + 0xb) & ((1L << 48) - 1);
		return (int) ((long) seed >> (48 - bits));
	}
}
