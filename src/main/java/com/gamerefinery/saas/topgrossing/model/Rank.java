package com.gamerefinery.saas.topgrossing.model;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Rank {
	private int rank;
	private long ts;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
}
