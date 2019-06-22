package com.example.tg.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.gamerefinery.saas.topgrossing.model.Rank;

/**
 *Entity class for games collection 
 *
 *
 */
@Entity(value= "games", noClassnameStored = true)
//@JsonIgnoreProperties(value = { "appGenreDhistories", "appGenreDranks", "appGenreHistories", "appGenreRanks", "appGenres", "appGenreSdranks", "appGenreSranks",
//		"artist", "released", "rank", "rankMonth", "rankWeek", "rankYesterday", "drank", "drankMonth", "drankWeek", "drankYesterday",
//		"genredId", "genre", "subGenre", "subGenreId", "genreDataModified", "genreDownloadDataModified"})
public class Game {
	@JsonIgnore
	@Id
    ObjectId id;
	
	private Map<Integer, List<Rank>> appGenreDhistories;

	private Map<Integer, Integer> appGenreDranks;

	private Map<Integer, List<Rank>> appGenreHistories;
	
	private Map<Integer, Integer> appGenreRanks;
	private List<Integer> appGenres;
	//Seems appGenres are a list of reference to Genre????
//	@Reference 
//   List<AppGenre>
	
	@Embedded
	private Map<Integer, Integer> appGenreSdranks;
	
	@Embedded
	private Map<Integer, Integer> appGenreSranks;
//	
	@JsonView(GameView.TopView.class)
	private long appId;
	@JsonView(GameView.TopView.class)
	private String name;
	private String artist;
	private Date released;
	
	private int rank;
	private int rankMonth;
	private int rankWeek;
	private int rankYesterday;
	
	private int drank;
	private int drankMonth;
	private int drankWeek;
	private int drankYesterday;
	
	private int srank;
	private int sdrank;
	
	private long dataCreated;
	private long dataModified;
	private long downloadDataModified;

//	@JsonSerialize(using = DoubleSerializer.class)
//	@JsonDeserialize(using = com.example.tg.models.utils.DoubleDeSerializer.class)
//	private double price;
	private String market;
	private String link;
	private List<String> images;
	
	private int revenueAvg;
	private int revenueSum;
	
	private int downloadAvg;
	private int downloadSum;

	@Embedded
	private List<History> history;
	@Embedded
	private List<History> dhistory;
	@Embedded
	private List<History> historyWeek;
	@Embedded
	private List<History> dhistoryWeek;
	@Embedded
	private List<History> historyMonth;
	@Embedded
	private List<History> dhistoryMonth;
	@Embedded
	private List<History> srankHistory;
	@Embedded
	private List<History> sdrankHistory;
	
//	@JsonDeserialize(using = com.example.tg.models.utils.DoubleDeSerializer.class)
//	private double gpsChange;
//	@JsonDeserialize(using = com.example.tg.models.utils.DoubleDeSerializer.class)
//	private double gps;
	private String type;
	
	
	private int genredId;
	private String genre;
	private String subGenre;
	private int subGenreId;
	
	private Map<Integer, Long> genreDataModified;
	private Map<Integer, Long> genreDownloadDataModified;
	
	private String conventionalSubgenre;
	private String conventionalSubgenreId;
	private long createdAt;
	private String gameId;
	
	private long top10Entry;
	private long top10Exit;
	private long top20Entry;
	private long top20Exit;
	private long top50Entry;
	private long top50Exit;
	private long top100Entry;
	private long top100Exit;
	private long top200Entry;
	private long top200Exit;
	
	private boolean top200DropNotified;

	private boolean changesInFeatures;

	private long lastAnalysisAt;
	private long lastRankTrendSent;
	private long lastScoreAt;
	private boolean favorite;
	
	
	private long modifiedAt;

	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public long getAppId() {
		return appId;
	}


	public void setAppId(long appId) {
		this.appId = appId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public Date getReleased() {
		return released;
	}


	public void setReleased(Date released) {
		this.released = released;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public int getRankMonth() {
		return rankMonth;
	}


	public void setRankMonth(int rankMonth) {
		this.rankMonth = rankMonth;
	}


	public int getRankWeek() {
		return rankWeek;
	}


	public void setRankWeek(int rankWeek) {
		this.rankWeek = rankWeek;
	}


	public int getRankYesterday() {
		return rankYesterday;
	}


	public void setRankYesterday(int rankYesterday) {
		this.rankYesterday = rankYesterday;
	}


	public int getDrank() {
		return drank;
	}


	public void setDrank(int drank) {
		this.drank = drank;
	}


	public int getDrankMonth() {
		return drankMonth;
	}


	public void setDrankMonth(int drankMonth) {
		this.drankMonth = drankMonth;
	}


	public int getDrankWeek() {
		return drankWeek;
	}


	public void setDrankWeek(int drankWeek) {
		this.drankWeek = drankWeek;
	}


	public int getDrankYesterday() {
		return drankYesterday;
	}


	public void setDrankYesterday(int drankYesterday) {
		this.drankYesterday = drankYesterday;
	}


	public int getSrank() {
		return srank;
	}


	public void setSrank(int srank) {
		this.srank = srank;
	}


	public int getSdrank() {
		return sdrank;
	}


	public void setSdrank(int sdrank) {
		this.sdrank = sdrank;
	}


	public long getDataCreated() {
		return dataCreated;
	}


	public void setDataCreated(long dataCreated) {
		this.dataCreated = dataCreated;
	}


	public long getDataModified() {
		return dataModified;
	}


	public void setDataModified(long dataModified) {
		this.dataModified = dataModified;
	}


	public long getDownloadDataModified() {
		return downloadDataModified;
	}


	public void setDownloadDataModified(long downloadDataModified) {
		this.downloadDataModified = downloadDataModified;
	}


//	public double getPrice() {
//		return price;
//	}
//
//
//	public void setPrice(double price) {
//		this.price = price;
//	}


	public String getMarket() {
		return market;
	}


	public void setMarket(String market) {
		this.market = market;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public int getRevenueAvg() {
		return revenueAvg;
	}


	public void setRevenueAvg(int revenueAvg) {
		this.revenueAvg = revenueAvg;
	}


	public int getRevenueSum() {
		return revenueSum;
	}


	public void setRevenueSum(int revenueSum) {
		this.revenueSum = revenueSum;
	}


	public int getDownloadAvg() {
		return downloadAvg;
	}


	public void setDownloadAvg(int downloadAvg) {
		this.downloadAvg = downloadAvg;
	}


	public int getDownloadSum() {
		return downloadSum;
	}


	public void setDownloadSum(int downloadSum) {
		this.downloadSum = downloadSum;
	}

	
//	public List<AppGenre> getAppGenres() {
//		return appGenres;
//	}
//
//
//	public void setAppGenres(List<AppGenre> appGenres) {
//		this.appGenres = appGenres;
//	}
	
	
	public List<History> getHistory() {
		return history;
	}


	

	public Map<Integer, List<Rank>> getAppGenreHistories() {
		return appGenreHistories;
	}


	public void setAppGenreHistories(Map<Integer, List<Rank>> appGenreHistories) {
		this.appGenreHistories = appGenreHistories;
	}


	public Map<Integer, Integer> getAppGenreRanks() {
		return appGenreRanks;
	}


	public void setAppGenreRanks(Map<Integer, Integer> appGenreRanks) {
		this.appGenreRanks = appGenreRanks;
	}


	public List<Integer> getAppGenres() {
		return appGenres;
	}


	public void setAppGenres(List<Integer> appGenres) {
		this.appGenres = appGenres;
	}


	public void setHistory(List<History> history) {
		this.history = history;
	}


	public List<History> getDhistory() {
		return dhistory;
	}


	public void setDhistory(List<History> dhistory) {
		this.dhistory = dhistory;
	}


	public List<History> getHistoryWeek() {
		return historyWeek;
	}


	public void setHistoryWeek(List<History> historyWeek) {
		this.historyWeek = historyWeek;
	}


	public List<History> getDhistoryWeek() {
		return dhistoryWeek;
	}


	public void setDhistoryWeek(List<History> dhistoryWeek) {
		this.dhistoryWeek = dhistoryWeek;
	}


	public List<History> getHistoryMonth() {
		return historyMonth;
	}


	public void setHistoryMonth(List<History> historyMonth) {
		this.historyMonth = historyMonth;
	}


	public List<History> getDhistoryMonth() {
		return dhistoryMonth;
	}


	public void setDhistoryMonth(List<History> dhistoryMonth) {
		this.dhistoryMonth = dhistoryMonth;
	}


	public List<History> getSrankHistory() {
		return srankHistory;
	}


	public void setSrankHistory(List<History> srankHistory) {
		this.srankHistory = srankHistory;
	}


	public List<History> getSdrankHistory() {
		return sdrankHistory;
	}


	public void setSdrankHistory(List<History> sdrankHistory) {
		this.sdrankHistory = sdrankHistory;
	}


//	public double getGpsChange() {
//		return gpsChange;
//	}
//
//
//	public void setGpsChange(double gpsChange) {
//		this.gpsChange = gpsChange;
//	}
//
//
//	public double getGps() {
//		return gps;
//	}
//
//
//	public void setGps(double gps) {
//		this.gps = gps;
//	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getGenredId() {
		return genredId;
	}


	public void setGenredId(int genredId) {
		this.genredId = genredId;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getSubGenre() {
		return subGenre;
	}


	public void setSubGenre(String subGenre) {
		this.subGenre = subGenre;
	}


	public int getSubGenreId() {
		return subGenreId;
	}


	public void setSubGenreId(int subGenreId) {
		this.subGenreId = subGenreId;
	}


	public String getConventionalSubgenre() {
		return conventionalSubgenre;
	}


	public void setConventionalSubgenre(String conventionalSubgenre) {
		this.conventionalSubgenre = conventionalSubgenre;
	}


	public String getConventionalSubgenreId() {
		return conventionalSubgenreId;
	}


	public void setConventionalSubgenreId(String conventionalSubgenreId) {
		this.conventionalSubgenreId = conventionalSubgenreId;
	}


	public long getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}


	public String getGameId() {
		return gameId;
	}


	public void setGameId(String gameId) {
		this.gameId = gameId;
	}


	public long getTop10Entry() {
		return top10Entry;
	}


	public void setTop10Entry(long top10Entry) {
		this.top10Entry = top10Entry;
	}


	public long getTop10Exit() {
		return top10Exit;
	}


	public void setTop10Exit(long top10Exit) {
		this.top10Exit = top10Exit;
	}


	public long getTop20Entry() {
		return top20Entry;
	}


	public void setTop20Entry(long top20Entry) {
		this.top20Entry = top20Entry;
	}


	public long getTop20Exit() {
		return top20Exit;
	}


	public void setTop20Exit(long top20Exit) {
		this.top20Exit = top20Exit;
	}


	public long getTop50Entry() {
		return top50Entry;
	}


	public void setTop50Entry(long top50Entry) {
		this.top50Entry = top50Entry;
	}


	public long getTop50Exit() {
		return top50Exit;
	}


	public void setTop50Exit(long top50Exit) {
		this.top50Exit = top50Exit;
	}


	public long getTop100Entry() {
		return top100Entry;
	}


	public void setTop100Entry(long top100Entry) {
		this.top100Entry = top100Entry;
	}


	public long getTop100Exit() {
		return top100Exit;
	}


	public void setTop100Exit(long top100Exit) {
		this.top100Exit = top100Exit;
	}


	public long getTop200Entry() {
		return top200Entry;
	}


	public void setTop200Entry(long top200Entry) {
		this.top200Entry = top200Entry;
	}


	public long getTop200Exit() {
		return top200Exit;
	}


	public void setTop200Exit(long top200Exit) {
		this.top200Exit = top200Exit;
	}


	public boolean isTop200DropNotified() {
		return top200DropNotified;
	}


	public void setTop200DropNotified(boolean top200DropNotified) {
		this.top200DropNotified = top200DropNotified;
	}


	public boolean isChangesInFeatures() {
		return changesInFeatures;
	}


	public void setChangesInFeatures(boolean changesInFeatures) {
		this.changesInFeatures = changesInFeatures;
	}


	public long getLastAnalysisAt() {
		return lastAnalysisAt;
	}


	public void setLastAnalysisAt(long lastAnalysisAt) {
		this.lastAnalysisAt = lastAnalysisAt;
	}


	public long getLastRankTrendSent() {
		return lastRankTrendSent;
	}


	public void setLastRankTrendSent(long lastRankTrendSent) {
		this.lastRankTrendSent = lastRankTrendSent;
	}


	public long getLastScoreAt() {
		return lastScoreAt;
	}


	public void setLastScoreAt(long lastScoreAt) {
		this.lastScoreAt = lastScoreAt;
	}


	public boolean isFavorite() {
		return favorite;
	}


	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}


	public long getModifiedAt() {
		return modifiedAt;
	}


	public void setModifiedAt(long modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	public Map<Integer, List<Rank>> getAppGenreDhistories() {
		return appGenreDhistories;
	}


	public void setAppGenreDhistories(Map<Integer, List<Rank>> appGenreDhistories) {
		this.appGenreDhistories = appGenreDhistories;
	}


	public Map<Integer, Integer> getAppGenreDranks() {
		return appGenreDranks;
	}


	public void setAppGenreDranks(Map<Integer, Integer> appGenreDranks) {
		this.appGenreDranks = appGenreDranks;
	}


	public Map<Integer, Integer> getAppGenreSdranks() {
		return appGenreSdranks;
	}


	public void setAppGenreSdranks(Map<Integer, Integer> appGenreSdranks) {
		this.appGenreSdranks = appGenreSdranks;
	}


	public Map<Integer, Integer> getAppGenreSranks() {
		return appGenreSranks;
	}


	public void setAppGenreSranks(Map<Integer, Integer> appGenreSranks) {
		this.appGenreSranks = appGenreSranks;
	}


	public Map<Integer, Long> getGenreDataModified() {
		return genreDataModified;
	}


	public void setGenreDataModified(Map<Integer, Long> genreDataModified) {
		this.genreDataModified = genreDataModified;
	}


	public Map<Integer, Long> getGenreDownloadDataModified() {
		return genreDownloadDataModified;
	}


	public void setGenreDownloadDataModified(Map<Integer, Long> genreDownloadDataModified) {
		this.genreDownloadDataModified = genreDownloadDataModified;
	}
	
	
	
}
