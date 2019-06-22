<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top Grossing Data</title>
</head>
<body>

<p>
	<ul>
	<li> Read top 10, 20, 50, 100, 200 games from given market, i.e. fi, it, es</li>
	<li><select id="topSelect">
		  <option value="10" selected="selected">Top 10</option>
		  <option value="20">Top 20</option>
		  <option value="50">Top 50</option>
		  <option value="100">Top 100</option>
		  <option value="200">Top 200</option>
		</select>
		
		<select id="market1">
		  <option value="fi" selected="selected">Finland</option>
		  <option value="es">Spain</option>
		  <option value="it">Italian</option>
		</select>
		<button type="button" onclick="getTopGames()">Request top games</button>
	</li>
	</ul>

</p>
<p>
	<ul>
	<li> List all games that have entered to top 10, 20, 50 between now and given amount of days. Listing is done per market, i.e. fi, it, es </li>
	<li>
	Days:<input type="text" name="inputJson2" id="inputJson2" style="width: 200px" value="10"/>
		<select id="topSelect2">
		  <option value="10" selected="selected">Top 10</option>
		  <option value="20">Top 20</option>
		  <option value="50">Top 50</option>
		</select>
		<select id="market2">
		  <option value="fi" selected="selected">Finland</option>
		  <option value="es">Spain</option>
		  <option value="it">Italian</option>
		</select>
		<button type="button" onclick="getTopGamesForDays()">Request top games</button>
	</li>
	</ul>

</p>
<p>
	<ul>
	<li> Mark given game as an favorite</li>
	<li>
	gameId: <input type="text" name="inputJson3" id="inputJson3" value="565c92d99b75634154f21824" style="width: 200px"/>
	<select id="market4">
		  <option value="fi" selected="selected">Finland</option>
		  <option value="es">Spain</option>
		  <option value="it">Italian</option>
		</select>
	<input type="checkbox" name="checkFavorite" id="checkFavorite" checked="checked"/>
		<button type="button" onclick="markFavorite()">Mark Favorite</button>
	</li>
	</ul>

</p>
<p>
	<ul>
	<li>Fetch rank history of give game from given market in three different ranges; daily, weekly and monthly </li>
	gameId: <input type="text" name="inputJson4" id="inputJson4" value="565c92d99b75634154f21824" style="width: 200px"/>
	<select id="topSelect3">
		  <option value="daily" selected="selected">Daily</option>
		  <option value="weekly">Weekly</option>
		  <option value="monthly">Monthly</option>
		</select>
		<select id="market3">
		  <option value="fi" selected="selected">Finland</option>
		  <option value="es">Spain</option>
		  <option value="it">Italian</option>
		</select>
		<button type="button" onclick="getRankHistory()">Request History</button>
	</ul>

</p>
<p id="demo"></p>

<script>
function getTopGames() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
      document.getElementById("demo").innerHTML = this.responseText;
  };
  xhttp.open("PUT", "rest/games/topHistory/" + document.getElementById('topSelect').value, false);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(JSON.stringify({market: document.getElementById('market1').value}));
}

function getTopGamesForDays() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	      document.getElementById("demo").innerHTML = this.responseText;
	  };
	  xhttp.open("PUT", "rest/games/topGamesForDays/" + document.getElementById('topSelect2').value + "/" + document.getElementById("inputJson2").value, false);
	  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");	 
	  xhttp.send(JSON.stringify({market: document.getElementById('market2').value}));
	}

function markFavorite() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	      document.getElementById("demo").innerHTML = this.responseText;
	  };
	  xhttp.open("PUT", "rest/games/favorite", false);
	  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	  var jsonStr = document.getElementById("inputJson3").value;
	  var checked = document.getElementById("checkFavorite").checked;
	  xhttp.send(JSON.stringify({gameId: document.getElementById('inputJson3').value, market: document.getElementById('market4').value, favorite: checked}));
	}

function getRankHistory() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	      document.getElementById("demo").innerHTML = this.responseText;
	  };
	  xhttp.open("PUT", "rest/games/rankHistory/" + document.getElementById('topSelect3').value, false);
	  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	  xhttp.send(JSON.stringify({gameId: document.getElementById('inputJson4').value, market: document.getElementById('market3').value}));
	}
/*function findTopGames()) {
    $.ajax({
        type: 'PUT',
        url: "rest/games/topGames/",
        dataType: "json",
        success: function(data){
            
        }
    });
}*/
</script>
</body>
</html>