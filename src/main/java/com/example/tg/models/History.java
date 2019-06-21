package com.example.tg.models;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class History {
	private int rank;;
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
